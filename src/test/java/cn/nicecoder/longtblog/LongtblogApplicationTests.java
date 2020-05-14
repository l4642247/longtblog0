package cn.nicecoder.longtblog;

import cn.nicecoder.longtblog.service.BlogUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LongtblogApplicationTests {

    @Autowired
    BlogUserService blogUserService;

    @Test
    public void contextLoads() {
        Boolean bool = blogUserService.login("1","2");
        System.out.print(bool);
    }

}
