package com.li.bbs.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 参数校验工具类
 * 用于校验前端传来的参数
 */
@Component
public class ValidationUtil {

    // 邮箱正则表达式
    private static final String EMAIL_PATTERN =
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // 用户名正则表达式（字母、数字、下划线，2-20位）
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{2,20}$";

    // 手机号正则表达式
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";

    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    /**
     * 校验邮箱格式
     * @param email 邮箱地址
     * @return 校验结果 true:格式正确 false:格式错误
     */
    public static boolean isValidEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }
        return emailPattern.matcher(email).matches();
    }

    /**
     * 校验用户名格式
     * @param username 用户名
     * @return 校验结果 true:格式正确 false:格式错误
     */
    public static boolean isValidUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return false;
        }
        return usernamePattern.matcher(username).matches();
    }

    /**
     * 校验手机号格式
     * @param phone 手机号
     * @return 校验结果 true:格式正确 false:格式错误
     */
    public static boolean isValidPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            return false;
        }
        return phonePattern.matcher(phone).matches();
    }

    /**
     * 校验字符串是否为空
     * @param str 待校验字符串
     * @return 校验结果 true:不为空 false:为空
     */
    public static boolean isNotBlank(String str) {
        return StringUtils.hasText(str);
    }

    /**
     * 校验字符串长度
     * @param str 待校验字符串
     * @param min 最小长度
     * @param max 最大长度
     * @return 校验结果 true:符合长度要求 false:不符合长度要求
     */
    public static boolean isValidLength(String str, int min, int max) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        return length >= min && length <= max;
    }

    /**
     * 校验数字是否在指定范围内
     * @param number 待校验数字
     * @param min 最小值
     * @param max 最大值
     * @return 校验结果 true:在范围内 false:不在范围内
     */
    public static boolean isValidRange(Integer number, int min, int max) {
        if (number == null) {
            return false;
        }
        return number >= min && number <= max;
    }
}
