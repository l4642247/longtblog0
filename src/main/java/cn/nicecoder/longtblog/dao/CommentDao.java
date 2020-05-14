package cn.nicecoder.longtblog.dao;

import cn.nicecoder.longtblog.entity.Comment;
import cn.nicecoder.longtblog.pojo.CommentStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Long>, JpaSpecificationExecutor {
    @Query(value="SELECT a.*,b.name as name,b.pic as pic, a.create_time as createtime " +
            "FROM comment a LEFT JOIN user b on a.userid = b.id order by a.create_time desc LIMIT ?1,?2 ;", nativeQuery = true)
    List<CommentStatistic> findByNoCondition(int pageNo, int pageSize);

    @Query(value="SELECT a.*,b.name as name,b.pic as pic, a.create_time as createtime " +
            "FROM comment a LEFT JOIN user b on a.userid = b.id WHERE discussid = ?1 AND a.type=?4 order by a.create_time desc LIMIT ?2,?3 ;", nativeQuery = true)
    List<CommentStatistic> findByArtId(Long CommentId, int pageNo, int pageSize, String type);

    @Query(value="SELECT a.*,b.name as name,b.pic as pic, a.create_time as createtime " +
            "FROM comment a LEFT JOIN user b on a.userid = b.id WHERE a.type=?3 order by a.create_time desc LIMIT ?1,?2 ;", nativeQuery = true)
    List<CommentStatistic> findByArtId(int pageNo, int pageSize, String type);

    @Query(value="SELECT a.*,b.name as name,b.pic as pic,c.name as toname,c.pic as topic " +
            "FROM comment a LEFT JOIN user b on a.userid = b.id LEFT JOIN user c on " +
            "a.touserid = c.id WHERE discussid = ?1 AND a.type=?4  order by a.create_time desc LIMIT ?2,?3 ;", nativeQuery = true)
    List<CommentStatistic> findByCommentId(Long CommentId, int pageNo, int pageSize, String type);

    @Query(value="SELECT count(0) FROM comment a WHERE a.discussid = ?1 AND a.type=?2 ", nativeQuery = true)
    int commentCount(Long artId, String type);

    @Transactional
    @Modifying
    @Query(value="update comment set agree = agree + 1 where id = ?1", nativeQuery = true)
    void updateAgree(Long id);
}
