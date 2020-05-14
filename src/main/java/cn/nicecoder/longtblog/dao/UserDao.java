package cn.nicecoder.longtblog.dao;

import cn.nicecoder.longtblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
