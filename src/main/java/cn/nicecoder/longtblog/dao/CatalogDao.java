package cn.nicecoder.longtblog.dao;

import cn.nicecoder.longtblog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CatalogDao extends JpaRepository<Catalog, Long>, JpaSpecificationExecutor {
    @Transactional
    @Modifying
    @Query(value="update catalog set count = count + 1 where id = ?1", nativeQuery = true)
    void updateCatalogCount(Long id);

    @Transactional
    @Modifying
    @Query(value="update catalog set count = count - 1 where id = ?1", nativeQuery = true)
    void updateCatalogCountSub(Long id);

    @Query(value="select sort from catalog where sort = (select max(sort) from catalog)", nativeQuery = true)
    Long lastSort();

    @Transactional
    @Modifying
    @Query(value="update catalog set sort = sort + 1 where sort >= ?1", nativeQuery = true)
    void updateSortBack(Long sort);
}
