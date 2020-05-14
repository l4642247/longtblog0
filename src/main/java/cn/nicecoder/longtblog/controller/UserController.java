package cn.nicecoder.longtblog.controller;

import cn.nicecoder.longtblog.entity.User;
import cn.nicecoder.longtblog.service.BlogUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: longt
 * @Date: 2019/3/29 16:21
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    BlogUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestParam(value = "username",required = true) String username,
                         @RequestParam(value = "password",required = true) String password){
        return userService.login(username, password);
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public User info(@PathVariable Long id){
        User user = userService.userInfo(id);
        return user;
    }

    @RequestMapping(value = "/info/all", method = RequestMethod.GET)
    public List<User> infoAll(){
        return userService.AllUserInfo();
    }
}
