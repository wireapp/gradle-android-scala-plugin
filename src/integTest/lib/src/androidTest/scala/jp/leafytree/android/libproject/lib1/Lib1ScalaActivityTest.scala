package jp.leafytree.android.libproject.lib1

import android.widget.TextView
import scala.collection.concurrent.TrieMap
import junit.framework.Assert

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.{Rule, Test}
import org.junit.runner.RunWith

@RunWith(classOf[AndroidJUnit4])
class Lib1ScalaActivityTest {

  private lazy val activityTestRule = new ActivityTestRule[Lib1ScalaActivity](classOf[Lib1ScalaActivity])

  @Rule
  def getActivityTestRule(): ActivityTestRule[Lib1ScalaActivity] = activityTestRule

  @Test
  def testSimpleAssertion {
    Assert.assertTrue(true)
  }

  @Test
  def testSimpleActivityAssertion {
    Assert.assertEquals("Lib1Java", activityTestRule.getActivity.findViewById(R.id.scala_text_view).asInstanceOf[TextView].getText)
  }

  @Test
  def testCallScalaLibraryClassOfNotUsedByMainApp {
    val map = new TrieMap[String, String]
    map.put("x", "Lib1Java")
    Assert.assertEquals(map("x"), activityTestRule.getActivity.findViewById(R.id.scala_text_view).asInstanceOf[TextView].getText)
  }
}
