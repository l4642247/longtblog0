package cn.nicecoder.longtblog.controller;

import cn.nicecoder.longtblog.entity.Article;
import cn.nicecoder.longtblog.entity.User;
import cn.nicecoder.longtblog.service.ArticleService;
import cn.nicecoder.longtblog.service.ClickService;
import cn.nicecoder.longtblog.service.UserService;
import cn.nicecoder.longtblog.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @Author: longt
 * @Date: 2019/5/24 10:46
 * @Description:
 */
@RestController
@RequestMapping("/click")
public class ClickController {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ClickService clickService;

    @RequestMapping(value = "/create/{artid}", method = RequestMethod.GET)
    public void createClick(@PathVariable Long artid,
                            HttpServletRequest request) throws UnsupportedEncodingException {
        String username = IPUtil.getIpAddress(request);
        User user = userService.findByUsername(username);
        if(user == null){
            user = new User();
            user.setType("0");
            user.setUsername(username);
            user.setPic("https://nicecoder.cn/imagelibrary/20190509/20190509_0930360.JPG");
            user = userService.create(user);
        }
        Article article = articleService.articleDetail(artid);
        clickService.create(user, article);
    }
}
