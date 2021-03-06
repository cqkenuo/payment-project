package in.hocg.payment.core;

import com.google.common.collect.Maps;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * Created by hocgin on 2019/11/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Accessors(chain = true, fluent = true)
public class ErrorContext {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();
    
    private ErrorContext stored;
    
    /**
     * 触发位置
     */
    @Setter
    private String activity;
    
    /**
     * 入参
     */
    @Setter
    private Object args;
    
    /**
     * 描述
     */
    @Setter
    private String message;
    
    /**
     * 异常信息
     */
    @Setter
    private Throwable cause;
    
    /**
     * URL
     */
    @Setter
    private String url;
    
    private ErrorContext() {
    }
    
    public static ErrorContext instance() {
        ErrorContext context = LOCAL.get();
        if (context == null) {
            context = new ErrorContext();
            LOCAL.set(context);
        }
        return context;
    }
    
    public ErrorContext store() {
        stored = this;
        LOCAL.set(new ErrorContext());
        return LOCAL.get();
    }
    
    public ErrorContext recall() {
        if (stored != null) {
            LOCAL.set(stored);
            stored = null;
        }
        return LOCAL.get();
    }
    
    public ErrorContext reset() {
        this.activity = null;
        this.message = null;
        this.cause = null;
        this.url = null;
        this.args = null;
        
        LOCAL.remove();
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        
        if (this.message != null) {
            description.append(LINE_SEPARATOR);
            description.append("### ");
            description.append(this.message);
        }
        
        if (activity != null) {
            description.append(LINE_SEPARATOR);
            description.append("### 触发场景: ");
            description.append(activity);
        }
        
        if (this.url != null) {
            description.append(LINE_SEPARATOR);
            description.append("### 请求地址: ");
            description.append(this.url);
        }
        
        if (args != null) {
            description.append(LINE_SEPARATOR);
            description.append("### 参数: ");
            description.append(args);
        }
        
        if (cause != null) {
            description.append(LINE_SEPARATOR);
            description.append("### 异常: ");
            description.append(cause.toString());
        }
        
        return description.toString();
    }
    
}
