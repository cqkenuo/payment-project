package in.hocg.payment.bean;

import in.hocg.payment.PaymentService;
import in.hocg.payment.convert.StringConvert;
import lombok.Getter;

/**
 * Created by hocgin on 2019/12/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class PaymentInitializingBean<S extends PaymentService>
        implements InitializingBean {
    
    @Getter
    protected S service;
    
    @Getter
    private String content;
    
    protected void setContent(String content) {
        this.content = content;
    }
    
    protected void setService(S service) {
        this.service = service;
    }
    
    @Override
    public void afterPropertiesSet() {
    
    }
    
    public static <T extends PaymentInitializingBean,
            S extends PaymentService> T from(S service,
                                             StringConvert convert,
                                             String content,
                                             Class<T> clazz) {
        T object = (T) convert.convert(content, clazz);
        object.setService(service);
        object.setContent(content);
        object.afterPropertiesSet();
        return object;
    }
}
