package jp.leafytree.android.libproject.lib1;

import junit.framework.TestCase;
import junit.framework.Assert;

public class Lib1JavaTest extends TestCase {
    public void test1() {
        Assert.assertEquals("Lib1Java", new Lib1Java().getName());
    }
}
