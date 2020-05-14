package cn.nicecoder.longtblog.service.Impl;

import cn.nicecoder.longtblog.dao.AgreeDao;
import cn.nicecoder.longtblog.dao.ArticleDao;
import cn.nicecoder.longtblog.dao.CommentDao;
import cn.nicecoder.longtblog.entity.Agree;
import cn.nicecoder.longtblog.entity.Comment;
import cn.nicecoder.longtblog.service.AgreeService;
import cn.nicecoder.longtblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: longt
 * @Date: 2019/5/13 16:30
 * @Description:
 */
@Service
public class AgreeServiceImpl implements AgreeService {
    @Autowired
    AgreeDao agreeDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    ArticleDao articleDao;

    @Override
    public Agree create(Long id, String type, Long uid) {
        Agree agree = agreeDao.findTopByAgreeidAndTypeAndUserid(id, type, uid);
        if(agree == null){
            agree = agreeDao.save(new Agree(type, id, uid, new Date()));
            if("1".equals(type)){
                articleDao.updateAgree(id);
            }else{
                commentDao.updateAgree(id);
            }
        }else{
            return null;
        }
        return agree;
    }

}
