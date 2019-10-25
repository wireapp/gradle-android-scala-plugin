package jp.leafytree.android.libproject.app

import android.widget.TextView
import jp.leafytree.android.libproject.R
import junit.framework.Assert
import jp.leafytree.android.libproject.lib1.{Lib1Java, Lib1ScalaActivity}
import scala.collection.concurrent.TrieMap

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
  def test1() {
    Assert.assertTrue(true)
  }

  @Test
  def test2() {
    Assert.assertEquals("Lib1Java", activityTestRule.getActivity.findViewById(R.id.scala_text_view).asInstanceOf[TextView].getText)
  }

  @Test
  def test3() {
    val map = TrieMap[String, String]()
    map.put("1", "Lib1Java")
    map.put("2", new Lib1Java().getName)
    Assert.assertEquals(map("1"), map("2"))
  }
}
