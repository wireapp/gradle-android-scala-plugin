package jp.leafytree.android.hello;

import android.widget.TextView;
import scala.collection.concurrent.TrieMap;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HelloJavaActivityTest {
    String flavor;

    @Rule
    public ActivityTestRule<HelloJavaActivity> activityTestRule = new ActivityTestRule<>(HelloJavaActivity.class);

    @Before
    public void setUp() {
        flavor = InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageName().replaceFirst(".*\\.", "");
    }

    @Test
    public void testSimpleAssertion() {
        Assert.assertTrue(true);
    }

    @Test
    public void testSimpleActivityAssertion() {
        Assert.assertEquals(flavor + "Java" + flavor + "Scala",
                ((TextView) activityTestRule.getActivity().findViewById(R.id.scala_text_view)).getText());
    }

    @Test
    public void testCallScalaLibraryClassOfNotUsedByMainApp() {
        TrieMap<String, String> map = new TrieMap<String, String>();
        map.put("x", flavor + "Java" + flavor + "Scala");
        Assert.assertEquals(map.apply("x"),
                ((TextView) activityTestRule.getActivity().findViewById(R.id.scala_text_view)).getText());
    }
}