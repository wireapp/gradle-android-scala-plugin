package jp.leafytree.android.hello

import android.widget.TextView
import junit.framework.Assert
import scala.collection.concurrent.TrieMap

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.{Rule, Test}
import org.junit.runner.RunWith

@RunWith(classOf[AndroidJUnit4])
class HelloActivityTest {

  private lazy val activityTestRule = new ActivityTestRule[HelloActivity](classOf[HelloActivity])

  @Rule
  def getActivityTestRule(): ActivityTestRule[HelloActivity] = activityTestRule

  @Test
  def testSimpleAssertion() {
    Assert.assertTrue(true)
  }

  @Test
  def testSimpleActivityAssertion() {
    Assert.assertEquals(new HelloJava().say + "\n" + new HelloScala().say, activityTestRule.getActivity.findViewById(R.id.scala_text_view).asInstanceOf[TextView].getText)
  }

  @Test
  def testCallScalaLibraryClassOfNotUsedByMainApp() {
    val map = new TrieMap[String, String]
    map.put("x", new HelloJava().say + "\n" + new HelloScala().say)
    Assert.assertEquals(map("x"), activityTestRule.getActivity.findViewById(R.id.scala_text_view).asInstanceOf[TextView].getText)
  }
}
