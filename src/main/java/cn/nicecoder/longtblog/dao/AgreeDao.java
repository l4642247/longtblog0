package cn.nicecoder.longtblog.dao;

import cn.nicecoder.longtblog.entity.Agree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AgreeDao extends JpaRepository<Agree, Long>, JpaSpecificationExecutor {
    Agree findTopByAgreeidAndTypeAndUserid(Long agreeId, String type, Long userId);

    Agree findTopByAgreeidAndType(Long agreeId, String type);
}
