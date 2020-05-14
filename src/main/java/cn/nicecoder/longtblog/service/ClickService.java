package cn.nicecoder.longtblog.service;

import cn.nicecoder.longtblog.entity.Article;
import cn.nicecoder.longtblog.entity.Click;
import cn.nicecoder.longtblog.entity.User;

public interface ClickService {
    //插入
    public Click create(User user, Article article);

    //删除文章关联的记录
    public void deleteByArtId(Long id);
}
