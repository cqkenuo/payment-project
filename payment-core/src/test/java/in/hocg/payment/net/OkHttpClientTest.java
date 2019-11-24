package in.hocg.payment.net;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hocgin on 2019/11/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
class OkHttpClientTest {
    
    @Test
    void get() {
        HttpClient client = HttpClient.getInstance(OkHttpClient.class);
        String text = client.get("http://www.baidu.com", Maps.newHashMap(), String.class);
        log.debug("文本: {}", text);
    }
}