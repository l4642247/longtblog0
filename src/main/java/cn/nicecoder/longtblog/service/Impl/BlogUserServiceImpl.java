package cn.nicecoder.longtblog.service.Impl;

import cn.hutool.crypto.SecureUtil;
import cn.nicecoder.longtblog.dao.UserDao;
import cn.nicecoder.longtblog.entity.User;
import cn.nicecoder.longtblog.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: longt
 * @Date: 2019/3/29 15:50
 * @Description: 实现类
 */
@Service
public class BlogUserServiceImpl implements BlogUserService {
    @Autowired
    UserDao blogUserDao;

    @Override
    public boolean login(String username, String password) {
        User user = blogUserDao.findByUsername(username);
        if(user != null && SecureUtil.md5(password).equals(user.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public User userInfo(Long id) {
        User user  = null;
        //在空的Optional实例上调用get()，抛出NoSuchElementException
        if(blogUserDao.findById(id) != null && blogUserDao.findById(id).isPresent()){
            user = blogUserDao.findById(id).get();
        }
        return user;
    }

    @Override
    public List<User> AllUserInfo() {
        return blogUserDao.findAll();
    }
}
