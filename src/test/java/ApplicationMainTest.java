import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

public class ApplicationMainTest extends BaseTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * reids
     */
    @Test
    public void test1(){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set("name","TOM",30, TimeUnit.SECONDS);
        System.out.println(stringStringValueOperations.get("name"));
    }
}
