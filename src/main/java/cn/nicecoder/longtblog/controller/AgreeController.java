package cn.nicecoder.longtblog.controller;

import cn.nicecoder.longtblog.entity.Agree;
import cn.nicecoder.longtblog.entity.User;
import cn.nicecoder.longtblog.service.AgreeService;
import cn.nicecoder.longtblog.service.UserService;
import cn.nicecoder.longtblog.util.AddressUtil;
import cn.nicecoder.longtblog.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @Author: longt
 * @Date: 2019/5/16 11:13
 * @Description:
 */
@RestController
@RequestMapping("/agree")
public class AgreeController {

    @Autowired
    AgreeService agreeService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Agree create(@RequestParam(value = "agreeId",required = true)Long agreeId,
                        @RequestParam(value = "type",required = true)String type,
                        @RequestParam(value = "userId",required = false)Long userId,
                        HttpServletRequest request) throws UnsupportedEncodingException {
        if(userId == null){
            String username = IPUtil.getIpAddress(request);
            User user = userService.findByUsername(username);
            if(user == null){
                user = new User();
                user.setType("0");
                user.setUsername(username);
                user.setName(AddressUtil.getAddresses("ip=" + username, "utf-8"));
                user.setPic("https://nicecoder.cn/imagelibrary/20190509/20190509_0930360.JPG");
                user = userService.create(user);
            }
            userId = user.getId();
        }
        Agree agree = agreeService.create(agreeId, type, userId);
        if(agree == null){
            return new Agree();
        }
        return agree;
    }

}
