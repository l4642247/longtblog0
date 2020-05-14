package cn.nicecoder.longtblog.dao;

import cn.nicecoder.longtblog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TagDao extends JpaRepository<Tag, Long>, JpaSpecificationExecutor {
    Tag findByName(String name);

    @Query(value="SELECT * FROM article_tag a LEFT JOIN tag b ON a.tag_id=b.id WHERE article_id = ?1", nativeQuery = true)
    List<Tag> findByArtId(Long artId);
}
