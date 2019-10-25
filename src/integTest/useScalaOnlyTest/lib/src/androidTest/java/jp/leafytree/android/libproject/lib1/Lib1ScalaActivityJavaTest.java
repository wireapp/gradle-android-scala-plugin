package jp.leafytree.android.libproject.lib1;

import android.view.ViewGroup;
import android.widget.TextView;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Lib1ScalaActivityJavaTest {

    @Rule
    public ActivityTestRule<Lib1JavaActivity> activityTestRule = new ActivityTestRule<>(Lib1JavaActivity.class);

    @Test
    public void test1() {
        Assert.assertTrue(true);
    }

    @Test
    public void test2() {
        Assert.assertEquals("Lib1Java",
                ((TextView)((ViewGroup) activityTestRule.getActivity()
                        .findViewById(android.R.id.content)).getChildAt(0)).getText()
        );
    }
}
