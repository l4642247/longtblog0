package cn.nicecoder.longtblog.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.nicecoder.longtblog.entity.Comment;
import cn.nicecoder.longtblog.entity.User;
import cn.nicecoder.longtblog.pojo.CommentResult;
import cn.nicecoder.longtblog.service.CommentService;
import cn.nicecoder.longtblog.service.UserService;
import cn.nicecoder.longtblog.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @Author: longt
 * @Date: 2019/5/13 16:21
 * @Description:
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam(value = "discussid",required = false,defaultValue = "0") Long discussid,
                               @RequestParam(value = "uid",required = false) Long uid,
                               @RequestParam(value = "touid",required = false) Long touid,
                               @RequestParam(value = "type",required = true) String type,
                               @RequestParam(value = "email",required = false) String email,
                               @RequestParam(value = "website",required = false) String website,
                               @RequestParam(value = "name",required = true) String name,
                               @RequestParam(value = "content",required = true) String content,
                               HttpServletRequest request) throws UnsupportedEncodingException {
        String url = "";
        if(discussid != 0){
            url = "redirect:../info/" + discussid;
        }else{
            url = "redirect:../gbook.html";
        }
        ModelAndView mv = new ModelAndView(url);
        String username = IPUtil.getIpAddress(request);
        User user = userService.findByUsername(username);
        if(user == null){
            user = new User();
            user.setType("0");
            user.setUsername(username);
        }
        if(!ObjectUtil.isNull(email)){
            user.setEmail(email);
        }
        if(!ObjectUtil.isNull(website)){
            user.setWebsite(website);
        }
        user.setName(name);
        user.setPic("https://nicecoder.cn/imagelibrary/20190509/20190509_0930360.JPG");
        user = userService.create(user);

        Long userId = user.getId();
        Comment comment = new Comment(type, discussid, userId, touid, new Date(), 0, "1", content.getBytes());
        comment =commentService.createComment(comment);
        mv.addObject("comment", comment);
        return mv;
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentResult> tagPage(@RequestParam(value = "artId",required = false) Long artId,
                                       @RequestParam(value = "pageNumber",defaultValue = "0") int pageNumber,
                                       @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        if(artId != null) {
            return commentService.commentPage(true, artId, pageNumber, pageSize);
        }
        return commentService.commentPage(false,null, pageNumber, pageSize);
    }

}
