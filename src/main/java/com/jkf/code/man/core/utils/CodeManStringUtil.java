package com.jkf.code.man.core.utils;

import org.apache.commons.lang3.StringUtils;

public class CodeManStringUtil {

    private static final char UNDERLINE = '_';

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isBlank(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (StringUtils.isBlank(param)) {
            return param;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * 第一个小括号内的数据:()
     * 示例：(abc)->abc
     * (a(bc))->a(bc
     */
    public static String firstInfoInParentheses(String str) {
        return str.substring(str.indexOf("(") + 1, str.indexOf(")"));
    }

    /**
     * 第一个单引号内的数据：''
     * 示例：'abc'->abc
     * 'ab'd'c'->ab
     */
    public static String firstInfoInSingleQuotes(String str) {
        String[] arr = str.split("'");
        return arr[1].trim();
    }

    /**
     * 返回最后一个单引号内部的数据：''
     * 示例：'abc'->abc
     * 'ab'd'c'->c
     */
    public static String lastInfoInSingleQuotes(String str) {
        String[] arr = str.split("'");
        return arr[arr.length - 1].trim();
    }

    /**
     * 返回最外侧括号内的数据
     * 示例：(abc)->abc
     * (ab(c))->ab(c)
     */
    public static String outmostInfoInParentheses(String str) {
        return str.substring(str.indexOf("(") + 1, str.lastIndexOf(")"));
    }

    /**
     * 返回最后一个单引号内部的数据：``
     * 示例：`abc`->abc
     * `ab`d`c`->ab
     */
    public static String firstInfoInApostrophe(String str) {
        String[] arr = str.split("`");
        return arr[1].trim();
    }


    /**
     * 校验特殊字符串
     * '，`，(，)
     * 不能包含上述特殊字符
     */
    public static void checkSpecialCharacters(String str) {
        if(str.contains("`")||str.contains("'")||str.contains("(")||str.contains(")")){
            throw new IllegalArgumentException("包含了特殊字符\"'，`,(，)\"");
        }
    }
}
