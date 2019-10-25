package jp.leafytree.android.libproject.app;

import android.widget.TextView;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import jp.leafytree.android.libproject.R;
import jp.leafytree.android.libproject.lib1.Lib1ScalaActivity;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Lib1ScalaActivityJavaTest {

    @Rule
    public ActivityTestRule<Lib1ScalaActivity> activityTestRule = new ActivityTestRule<>(Lib1ScalaActivity.class);

    @Test
    public void test1() {
        Assert.assertTrue(true);
    }

    @Test
    public void test2() {
        Assert.assertEquals("Lib1Java", ((TextView) activityTestRule.getActivity().findViewById(R.id.scala_text_view)).getText());
    }
}
