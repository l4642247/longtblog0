package cn.nicecoder.longtblog.service.Impl;

import cn.nicecoder.longtblog.dao.ArticleDao;
import cn.nicecoder.longtblog.dao.ClickDao;
import cn.nicecoder.longtblog.entity.Article;
import cn.nicecoder.longtblog.entity.Click;
import cn.nicecoder.longtblog.entity.User;
import cn.nicecoder.longtblog.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: longt
 * @Date: 2019/5/24 10:42
 * @Description:
 */
@Service
public class ClickServiceImpl implements ClickService {

    @Autowired
    ClickDao clickDao;

    @Autowired
    ArticleDao articleDao;

    @Override
    public Click create(User user, Article article) {
        Click c = new Click("0",new Date(),user,article);
        articleDao.updateClick(article.getId());
        return clickDao.save(c);
    }

    @Override
    public void deleteByArtId(Long id) {
        clickDao.deleteByArticleId(id);
    }
}
