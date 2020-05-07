/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.leafytree.gradle

import com.google.common.io.ByteStreams
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class AndroidScalaPluginIntegrationTestTask extends DefaultTask {

    static def GRADLE_VERSION = "gradleVersion"
    static def SCALA_VERSION = "scalaVersion"
    static def ANDROID_GRADLE_PLUGIN_VERSION = "androidGradlePluginVersion"
    static def ANDROID_PLUGIN_COMPILE_SDK_VERSION = "androidPluginCompileSdkVersion"
    static def ANDROID_BUILD_TOOLS_VERSION = "androidBuildToolsVersion"
    static def MIN_SDK_VERSION = "minSdkVersion"
    static def TARGET_SDK_VERSION = "targetSdkVersion"

    static def buildParameters = [
        (GRADLE_VERSION)                    : "5.6.4",
        (SCALA_VERSION)                     : "2.11.7",
        (ANDROID_GRADLE_PLUGIN_VERSION)     : "3.6.0",
        (ANDROID_PLUGIN_COMPILE_SDK_VERSION): "android-28",
        (ANDROID_BUILD_TOOLS_VERSION)       : "28.0.3",
        (MIN_SDK_VERSION)                   : "21",
        (TARGET_SDK_VERSION)                : "28"
    ]

    @TaskAction
    def run() {
        ["app", "appAndLib", "apt", "largeAppAndLib", "lib", "noScala", "simpleFlavor", "useScalaOnlyTest"].each { projectName ->
            def gradleArgs = ["clean", "test", "connectedCheck", "uninstallAll"]
            def gradleVersion = buildParameters[GRADLE_VERSION]
            def gradleWrapperProperties = getGradleWrapperProperties(gradleVersion)
            def gradleProperties = getGradleProperties(buildParameters)
            println "Test $projectName $gradleArgs GRADLE_VERSION:$gradleVersion $gradleProperties"
            runProject(projectName, gradleArgs, gradleWrapperProperties, gradleProperties)
        }
    }

    static def getGradleWrapperProperties(gradleVersion) {
        def gradleWrapperProperties = new Properties()
        gradleWrapperProperties.putAll([
            distributionBase: "GRADLE_USER_HOME",
            distributionPath: "wrapper/dists",
            zipStoreBase    : "GRADLE_USER_HOME",
            zipStorePath    : "wrapper/dists",
            distributionUrl : "https://services.gradle.org/distributions/gradle-" + gradleVersion + "-bin.zip",
        ])
        gradleWrapperProperties
    }

    def getGradleProperties(Map<String, String> buildParams) {
        // def snaphotRepositoryUrl = "http://saturday06.github.io/gradle-android-scala-plugin/repository/snapshot"
        def snaphotRepositoryUrl = [project.buildFile.parentFile.absolutePath, "gh-pages", "repository", "snapshot"].join(File.separator)
        def gradleProperties = new Properties()
        gradleProperties.putAll([
            "org.gradle.jvmargs"          : "-Xmx2048m -XX:MaxPermSize=2048m -XX:+HeapDumpOnOutOfMemoryError",
            snaphotRepositoryUrl          : snaphotRepositoryUrl,
            scalaLibraryVersion           : buildParams[SCALA_VERSION],
            scalaDependencyVersion        : buildParams[SCALA_VERSION].split("\\.").take(2).join("."),
            androidScalaPluginVersion     : "2.0.0-SNAPSHOT",
            androidPluginVersion          : buildParams[ANDROID_GRADLE_PLUGIN_VERSION],
            androidPluginCompileSdkVersion: buildParams[ANDROID_PLUGIN_COMPILE_SDK_VERSION],
            androidPluginBuildToolsVersion: buildParams[ANDROID_BUILD_TOOLS_VERSION],
            androidPluginMinSdkVersion    : buildParams[MIN_SDK_VERSION],
            androidPluginTargetSdkVersion : buildParams[TARGET_SDK_VERSION],
            androidPluginIncremental      : "false",
            androidPluginPreDexLibraries  : "false",
            androidPluginJumboMode        : "false",
        ])
        gradleProperties
    }

    def runProject(projectName, tasks, gradleWrapperProperties, gradleProperties) {
        def baseDir = new File([project.buildFile.parentFile.absolutePath, "src", "integTest"].join(File.separator))
        def projectDir = new File([baseDir.absolutePath, projectName].join(File.separator))
        new File(projectDir, ["gradle", "wrapper", "gradle-wrapper.properties"].join(File.separator)).withWriter {
            gradleWrapperProperties.store(it, getClass().getName())
        }
        new File(projectDir, "gradle.properties").withWriter {
            gradleProperties.store(it, getClass().getName())
        }
        def gradleWrapper = new GradleWrapper(projectDir)
        def args = ["--no-daemon", "--stacktrace", "-Pcom.android.build.threadPoolSize=5"] + tasks
        println "gradlew $args"
        def process = gradleWrapper.execute(args)
        [Thread.start { ByteStreams.copy(process.in, System.out) },
         Thread.start { ByteStreams.copy(process.err, System.err) }].each { it.join() }
        process.waitFor()
        // process.waitForProcessOutput(System.out, System.err)
        if (process.exitValue() != 0) {
            throw new IOException("process.exitValue != 0 but ${process.exitValue()}")
        }
    }
}
