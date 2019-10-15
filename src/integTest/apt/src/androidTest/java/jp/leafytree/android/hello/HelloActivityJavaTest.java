package jp.leafytree.android.hello;

import android.widget.TextView;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import scala.collection.concurrent.TrieMap;

@RunWith(AndroidJUnit4.class)
public class HelloActivityJavaTest {

    @Rule
    public ActivityTestRule<HelloActivity_> activityTestRule = new ActivityTestRule<>(HelloActivity_.class);

    @Test
    public void testSimpleAssertion() {
        Assert.assertTrue(true);
    }

    @Test
    public void testSimpleActivityAssertion() {
        Assert.assertEquals(new HelloJava().say() + "\n" + new HelloScala().say(),
                ((TextView) activityTestRule.getActivity().findViewById(R.id.scala_text_view)).getText());
    }

    @Test
    public void testCallScalaLibraryClassOfNotUsedByMainApp() {
        TrieMap<String, String> map = new TrieMap<String, String>();
        map.put("x", new HelloJava().say() + "\n" + new HelloScala().say());
        Assert.assertEquals(map.apply("x"),
                ((TextView) activityTestRule.getActivity().findViewById(R.id.scala_text_view)).getText());
    }
}
