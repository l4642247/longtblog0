package cn.nicecoder.longtblog.service;


import cn.nicecoder.longtblog.entity.User;

/**
 *
 */
public interface UserService {
    public User create(User user);

    public User findByUsername(String username);
}
