package cn.nicecoder.longtblog.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: longt
 * @Date: 2019/3/29 10:21
 * @Description:
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 认证请求规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/","/js/**","/css/**","/assets/**","/layuiadmin/**","/prism/**"
                            ,"/index.html","/about.html","/gbook.html","/info/**","/infopic.html","/list.html","/share.html"
                            ,"/agree/**","/article/**","/catalog/**","/click/**","/tag/**","/comment/**")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login.html")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .headers().frameOptions().disable();
        http.csrf().disable();
    }

    /**
     * 授权
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new passwordEncoder())
                .withUser("longt").password("123456").roles("ADMIN")
                .and()
                .withUser("zhiw").password("123456").roles("GUSET");
    }
}

