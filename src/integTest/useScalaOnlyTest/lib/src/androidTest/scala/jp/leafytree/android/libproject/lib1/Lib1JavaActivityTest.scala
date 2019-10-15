package jp.leafytree.android.libproject.lib1

import android.view.ViewGroup
import android.widget.TextView
import junit.framework.Assert
import scala.collection.concurrent.TrieMap

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.{Rule, Test}
import org.junit.runner.RunWith

@RunWith(classOf[AndroidJUnit4])
class Lib1JavaActivityTest {

  private lazy val activityTestRule = new ActivityTestRule[Lib1JavaActivity](classOf[Lib1JavaActivity])

  @Rule
  def getActivityTestRule(): ActivityTestRule[Lib1JavaActivity] = activityTestRule

  @Test
  def test1() {
    Assert.assertTrue(true)
  }

  @Test
  def test2() {
    Assert.assertEquals("Lib1Java", activityTestRule.getActivity
      .findViewById(android.R.id.content).asInstanceOf[ViewGroup].getChildAt(0).asInstanceOf[TextView].getText)
  }

  @Test
  def test3() {
    val map = TrieMap[String, String]()
    map.put("1", "Lib1Java")
    map.put("2", new Lib1Java().getName)
    Assert.assertEquals(map("1"), map("2"))
  }
}
