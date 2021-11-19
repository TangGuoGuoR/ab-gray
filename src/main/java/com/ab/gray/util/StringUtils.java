package com.ab.gray.util;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: StringUtils
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || value.length() <= 0;
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return value != null && value.length() > 0;
    }

}
