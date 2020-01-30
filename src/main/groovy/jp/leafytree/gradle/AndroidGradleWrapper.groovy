/**
 * code from kotlin plugin  org.jetbrains.kotlin.gradle.plugin.android.AndroidGradleWrapper
 */
class AndroidGradleWrapper {
    /**
     * Get directories with java sources
     * @param variantData
     * @return
     */
    static List<File> getJavaSources(Object variantData){
        def result = new LinkedHashSet<File>()

        // user sources
        for (provider in variantData.variantConfiguration.sortedSourceProviders) {
            result.addAll(provider.java.srcDirs())
        }

        // generated sources
        def javaSources = variantData.getJavaSources() //("getJavaSources")
        if (javaSources instanceof  Object[]) {
            result += javaSources
        } else if (javaSources instanceof  List<?>) {
            def fileTrees = javaSources
            result += fileTrees.collect  { it.dir }
        } else {
            if (variantData.scope.generateRClassTask != null) {
                result += variantData.scope.rClassSourceOutputDir
            }

            if (variantData.scope.generateBuildConfigTask != null) {
                result += variantData.scope.buildConfigSourceOutputDir
            }

            if (variantData.scope.aidlCompileTask != null) {
                result += variantData.scope.aidlSourceOutputDir
            }

            if (variantData.scope.globalScope.extension.dataBinding.isEnabled) {
                result += variantData.scope.classOutputForDataBinding
            }

            if (!variantData.variantConfiguration.renderscriptNdkModeEnabled && variantData.scope.renderscriptCompileTask != null) {
                result += variantData.scope.renderscriptSourceOutputDir
            }
        }

        def extraSources = variantData.getExtraGeneratedSourceFolders()
        if (extraSources != null) {
            result += extraSources
        }

        return result.toList()
    }
}