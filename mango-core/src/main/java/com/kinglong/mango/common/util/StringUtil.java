package com.kinglong.mango.common.util;

/**
 * Created by chenjinlong on 16/1/14.
 */
public class StringUtil {
    public final static char DEFAULT_SPLIT = '_';

    public static Boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static Boolean isEmptyAfterTrim(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String split2Camel(String str) {
        return split2Camel(str, DEFAULT_SPLIT);
    }

    public static String split2Camel(String str, char split) {
        if (isEmpty(str)) {
            return str;
        }
        int starInx = 0;
        while (starInx < str.length() && str.charAt(starInx) == split) starInx++;
        if (starInx >= str.length()) {
            return "";
        }
        StringBuilder buf = new StringBuilder();
        buf.append(str.charAt(starInx));


        for (int i = starInx + 1; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch != split) {
                if (i > 0 && str.charAt(i - 1) == split) {
                    buf.append(char2UpperCase(ch));
                } else {
                    buf.append(ch);
                }
            }
        }
        return buf.toString();
    }

    public static String camel2Split(String str) {
        return camel2Split(str, DEFAULT_SPLIT);
    }

    public static String camel2Split(String str, char split) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder buf = null;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (buf == null) {
                    buf = new StringBuilder();
                    if (i > 0) {
                        buf.append(str.substring(0, i));
                    }
                }
                if (i > 0) {
                    buf.append(split);
                }
                buf.append(char2LowerCase(ch));
            } else if (buf != null) {
                buf.append(ch);
            }
        }
        return buf == null ? str : buf.toString();
    }

    private static char char2UpperCase(char ch) {
        if (Character.isLowerCase(ch))
            return Character.toUpperCase(ch);
        return ch;
    }

    private static char char2LowerCase(char ch) {
        if (Character.isUpperCase(ch))
            return Character.toLowerCase(ch);
        return ch;
    }
}
