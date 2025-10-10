package com.li.bbs.util;

import com.li.bbs.Pojo.Post;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 参数校验工具类
 * 用于校验前端传来的参数
 */
@Component
public class ValidationUtil {
    //登录注册：邮箱、用户名、手机号
    // 邮箱正则表达式
    private final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // 用户名正则表达式（字母、数字、下划线，2-20位）
    private final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{2,20}$";
    // 密码正则表达式（字母、下划线、数字，6-20位，不能包含中文）
    private final String PASSWORD_PATTERN = "^[a-zA-Z0-9_]{6,20}$";

    // 手机号正则表达式
    private final String PHONE_PATTERN = "^1[3-9]\\d{9}$";

    private final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);
    private final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    /**
     * 校验邮箱格式
     *
     * @param email 邮箱地址
     * @return 校验结果 true:格式正确 false:格式错误
     */
    public boolean isValidEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }
        return emailPattern.matcher(email).matches();
    }

    /**
     * 校验用户名格式
     *
     * @param username 用户名
     * @return 校验结果 true:格式正确 false:格式错误
     */
    public boolean isValidUsername(String username) {

        if (!StringUtils.hasText(username)) {
            return false;
        }
        return usernamePattern.matcher(username).matches();
    }

    /**
     * 校验手机号格式
     *
     * @param phone 手机号
     * @return 校验结果 true:格式正确 false:格式错误
     */
    public boolean isValidPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            return false;
        }
        return phonePattern.matcher(phone).matches();
    }

    /**
     * 校验字符串是否为空
     *
     * @param str 待校验字符串
     * @return 校验结果 true:不为空 false:为空
     */
    public boolean isNotBlank(String str) {
        return StringUtils.hasText(str);
    }

    /**
     * 校验字符串长度
     *
     * @param str 待校验字符串
     * @param min 最小长度
     * @param max 最大长度
     * @return 校验结果 true:符合长度要求 false:不符合长度要求
     */
    public boolean isValidLength(String str, int min, int max) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        return length >= min && length <= max;
    }

    /**
     * 校验数字是否在指定范围内
     *
     * @param number 待校验数字
     * @param min    最小值
     * @param max    最大值
     * @return 校验结果 true:在范围内 false:不在范围内
     */
    public boolean isValidRange(Integer number, int min, int max) {
        if (number == null) {
            return false;
        }
        return number >= min && number <= max;
    }

    //添加评论参数校验
    public boolean isValidComment(String content) {
        if (!StringUtils.hasText(content)) {
            return false; // 评论不能为空
        }
        // 评论长度不能超过200字
        if (!isValidLength(content, 1, 200)) {
            return false;
        }

        // 检查是否包含敏感字符
        if (containsSensitiveWords(content)) {
            return false;
        }

        return true;
    }

    /**
     * 检查是否包含敏感词
     *
     * @param content 待检查内容
     * @return true:包含敏感词 false:不包含
     */
    private boolean containsSensitiveWords(String content) {
        if (content == null) {
            return false;
        }

        // 敏感词列表(可以从数据库或配置文件加载)
        String[] sensitiveWords = {
                "垃圾", "废物", "傻逼", "笨蛋", "蠢货", "白痴",
                "fuck", "shit", "damn", "idiot", "stupid"
        };

        for (String word : sensitiveWords) {
            if (content.contains(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取敏感词列表(实际应用中建议从配置文件或数据库加载)
     *
     * @return 敏感词数组
     */
    public String[] getSensitiveWords() {
        return new String[]{
                "垃圾", "废物", "傻逼", "笨蛋", "蠢货", "白痴",
                "fuck", "shit", "damn", "idiot", "stupid"
                // 可以根据需要添加更多敏感词
        };
    }


    //添加帖子参数校验:
    public boolean isValidPostTitle(String title) {
        if (!StringUtils.hasText(title)) {
            return false; // 标题不能为空
        }
        if (!isValidLength(title, 1, 50)) {
            return false;// 标题长度不能超过50字
        }
        if (containsSensitiveWords(title)) {
            return false; // 标题不能包含敏感词
        }
        return true;
    }

    public boolean isValidPostSubtitle(String subtitle) {
        if (!StringUtils.hasText(subtitle)) {
            return false; // 副标题不能为空
        }
        if (!isValidLength(subtitle, 1, 100)) {
            return false;// 副标题长度不能超过100字
        }
        if (containsSensitiveWords(subtitle)) {
            return false; // 副标题不能包含敏感词
        }
        return true;
    }

    public boolean isValidPostContent(String content) {
        if (!StringUtils.hasText(content)) {
            return false; // 内容不能为空
        }
        if (containsSensitiveWords(content)) {
            return false;//内容不能包含敏感词
        }
        return true;
    }

    public boolean isValidPost(Post post) {
        if (post == null) {
            return false;
        }
        return isValidPostTitle(post.getTitle())
                && isValidPostSubtitle(post.getSubtitle())
                && isValidPostContent(post.getContent());
    }

    //校验User参数
    public boolean isValidAvatarUrl(String avatarUrl) {
        if (!avatarUrl.startsWith("http://") && !avatarUrl.startsWith("https://")) {
            return false;// 头像URL格式必须以http://或https://开头
        }
        return true;
    }

    public boolean isValidPassword(String email) {
        // 密码长度应在6-20位之间,不能有中文
        if (!StringUtils.hasText(email)) {
            return passwordPattern.matcher(email).matches();
        }
        return true;
    }
}

