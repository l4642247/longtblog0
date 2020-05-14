package cn.nicecoder.longtblog.service;

import cn.nicecoder.longtblog.entity.Comment;
import cn.nicecoder.longtblog.pojo.CommentResult;

import java.util.List;

/**
 *
 */
public interface CommentService {
    /**
     * 创建
     * @param comment
     * @return
     */
    public Comment createComment(Comment comment);

    /**
     * 查询评论
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<CommentResult> commentPage(boolean condition, Long artId, int pageNumber, int pageSize);
}
