package jp.leafytree.android.hello

import android.widget.TextView
import junit.framework.Assert
import scala.collection.concurrent.TrieMap

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.{Before, Rule, Test}
import org.junit.runner.RunWith

@RunWith(classOf[AndroidJUnit4])
class HelloScalaActivityTest {

  var flavor: String = _

  private lazy val activityTestRule = new ActivityTestRule[HelloScalaActivity](classOf[HelloScalaActivity])

  @Rule
  def getActivityTestRule(): ActivityTestRule[HelloScalaActivity] = activityTestRule

  @Before
  def setUp() {
    flavor = InstrumentationRegistry.getInstrumentation.getTargetContext.getPackageName.replaceFirst(".*\\.", "")
  }

  @Test
  def testSimpleAssertion() {
    Assert.assertTrue(true)
  }

  @Test
  def testSimpleActivityAssertion() {
    Assert.assertEquals(f"${flavor}Java${flavor}Scala",
      activityTestRule.getActivity.findViewById(R.id.scala_text_view).asInstanceOf[TextView].getText)
  }

  @Test
  def testCallScalaLibraryClassOfNotUsedByMainApp() {
    val map = new TrieMap[String, String]
    map.put("x", f"${flavor}Java${flavor}Scala")
    Assert.assertEquals(map("x"),
      activityTestRule.getActivity.findViewById(R.id.scala_text_view).asInstanceOf[TextView].getText)
  }
}
