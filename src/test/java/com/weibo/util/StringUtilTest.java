package com.weibo.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * StringUtilTest
 * Created by Sean on 16/3/10.
 */
public class StringUtilTest {
    @Test
    public void testToMD5() {
        String s = "The quick brown fox jumps over the lazy dog";
        String md5 = "9e107d9d372bb6826bd81d3542a419d6";
        Assert.assertTrue(md5.equals(StringUtil.toMD5(s)));
    }
}
