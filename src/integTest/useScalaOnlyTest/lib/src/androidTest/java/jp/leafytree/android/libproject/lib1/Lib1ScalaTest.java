package jp.leafytree.android.libproject.lib1;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Lib1ScalaTest extends TestCase {
    public void test1() {
        Assert.assertEquals("Lib1Java", new Lib1Java().getName());
    }
}
