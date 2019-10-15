package jp.leafytree.android.libproject.lib1

import android.widget.TextView
import junit.framework.Assert
import scala.io.Source
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
    Assert.assertEquals(Source.fromString("x").toList(0), 'x')
  }
}
