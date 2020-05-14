package cn.nicecoder.longtblog.service.Impl;

import cn.nicecoder.longtblog.dao.UserDao;
import cn.nicecoder.longtblog.entity.User;
import cn.nicecoder.longtblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: longt
 * @Date: 2019/3/29 15:50
 * @Description: 实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
