package cn.nicecoder.longtblog.service.Impl;

import cn.nicecoder.longtblog.dao.CommentDao;
import cn.nicecoder.longtblog.entity.Comment;
import cn.nicecoder.longtblog.pojo.CommentResult;
import cn.nicecoder.longtblog.pojo.CommentStatistic;
import cn.nicecoder.longtblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: longt
 * @Date: 2019/5/13 16:30
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public Comment createComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public List<CommentResult> commentPage(boolean condition, Long artId, int pageNumber, int pageSize) {
        List<CommentResult> result = new ArrayList<CommentResult>();
        List<CommentStatistic> cstatic = new ArrayList<CommentStatistic>();
        if(condition) {
            if (artId != null) {
                cstatic = commentDao.findByArtId(artId, pageNumber, pageSize, "1");
            } else {
                cstatic = commentDao.findByArtId(pageNumber, pageSize, "0");
            }
        }else{
            cstatic = commentDao.findByNoCondition(pageNumber, pageSize);
        }
        cstatic.forEach(o -> {
            CommentResult comm = o.toComment();
            // 2级
            Long commId = comm.getId();
            List<CommentResult> result2 = new ArrayList<CommentResult>();
            List<CommentStatistic> cstaticbyCommId= commentDao.findByCommentId(commId,0,100,"2");
            cstaticbyCommId.forEach(o2 -> {
                CommentResult comm2 = o2.toComment();
                result2.add(comm2);
            });
            comm.setCommentResults(result2);
            result.add(comm);
        });
        return result;
    }
}
