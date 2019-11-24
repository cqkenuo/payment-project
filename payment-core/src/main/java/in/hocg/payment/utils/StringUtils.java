package in.hocg.payment.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class StringUtils {
    
    /**
     * 字符串比较
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        
        return s1 != null && s1.equals(s2);
    }
    
    /**
     * 字符串转实体
     *
     * @param content
     * @param returnClass
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String content, Class<T> returnClass) {
        
        // 如果是 NULL
        if (Objects.isNull(content)) {
            return null;
        }
        
        // 如果是基础类型
        if (ClassUtils.isBaseType(returnClass) || String.class.equals(returnClass)) {
            return ((T) content);
        }
        
        Object result;
        char word = content.charAt(0);
        if (isJSON(word)) {
            result = JSONUtils.toBean(content, returnClass);
        } else if (isXml(word)) {
            result = XMLUtils.toBean(content, returnClass);
        } else {
            result = content;
        }
    
        return (T) result;
    }
    
    /**
     * JSON 文本
     *
     * @param word
     * @return
     */
    public static boolean isJSON(char word) {
        return "{[".indexOf(word) >= 0;
    }
    
    /**
     * XML 文本
     *
     * @param word
     * @return
     */
    public static boolean isXml(char word) {
        return "<".indexOf(word) >= 0;
    }
    
}
