package com.kinglong.mango.common.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by chenjinlong on 16/1/14.
 */
public class StringUtilTest {
    @Test
    public void camel2SplitTest() {
        String camelStr = "aaaBbbCcc";
        String splitStr = "aaa_bbb_ccc";
        assertTrue(StringUtil.camel2Split(camelStr).equals(splitStr));
        assertTrue(StringUtil.split2Camel(splitStr).equals(camelStr));

        camelStr = "AaaBbbCcc";
        splitStr = "aaa_bbb_ccc";
        assertTrue(StringUtil.camel2Split(camelStr).equals(splitStr));

        camelStr = "AAaBbbCcc";
        splitStr = "a_aa_bbb_ccc";
        assertTrue(StringUtil.camel2Split(camelStr).equals(splitStr));
        camelStr = "aAaBbbCcc";
        assertTrue(StringUtil.split2Camel(splitStr).equals(camelStr));

        camelStr = "aaaBbbCCC";
        splitStr = "aaa_bbb_c_c_c";
        assertTrue(StringUtil.camel2Split(camelStr).equals(splitStr));
        assertTrue(StringUtil.split2Camel(splitStr).equals(camelStr));


        camelStr = "aaaBbbCcc";
        splitStr = "___aaa_bbb_ccc";
        assertTrue(StringUtil.split2Camel(splitStr).equals(camelStr));

        camelStr = "aaaBbbCcc";
        splitStr = "___aaa____bbb_ccc";
        assertTrue(StringUtil.split2Camel(splitStr).equals(camelStr));


        camelStr = "aBC";
        splitStr = "_a_b_c_";
        assertTrue(StringUtil.split2Camel(splitStr).equals(camelStr));

        camelStr = "abc";
        assertTrue(StringUtil.camel2Split(camelStr).equals(camelStr));
    }
}
