package cn.nicecoder.longtblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LongtblogApplication extends SpringBootServletInitializer {

    // 重写 configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(LongtblogApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LongtblogApplication.class, args);
    }

}
