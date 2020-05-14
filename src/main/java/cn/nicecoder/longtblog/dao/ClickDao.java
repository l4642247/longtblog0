package cn.nicecoder.longtblog.dao;

import cn.nicecoder.longtblog.entity.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClickDao extends JpaRepository<Click, Long>, JpaSpecificationExecutor {
    @Transactional
    @Modifying
    @Query(value="DELETE FROM click where article_id = ?1", nativeQuery = true)
    void deleteByArticleId(Long id);
}
