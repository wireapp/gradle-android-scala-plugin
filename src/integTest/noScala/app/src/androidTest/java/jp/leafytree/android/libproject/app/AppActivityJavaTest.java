package jp.leafytree.android.libproject.app;

import android.app.Activity;
import android.widget.TextView;
import jp.leafytree.android.libproject.AppActivity;
import jp.leafytree.android.libproject.R;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppActivityJavaTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Test
    public void test1() {
        Assert.assertTrue(true);
    }

    @Test
    public void test2() {
        Assert.assertEquals("Lib1Java",
                ((TextView) activityTestRule.getActivity().findViewById(R.id.message_text_view)).getText());
    }
}
