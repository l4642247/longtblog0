package cn.nicecoder.longtblog.service;

import cn.nicecoder.longtblog.entity.Catalog;
import org.springframework.data.domain.Page;

public interface CatalogService {

    /**
     * 查询类别
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<Catalog> catalogPage(int pageNumber, int pageSize);

    /**
     * 创建
     * @param catalog
     * @return
     */
    public Catalog catalogCreate(Catalog catalog);


    /**
     * 删除
     * @param id
     */
    public void deleteCatalog(Long id);


    /**
     * 查询
     * @param id
     * @return
     */
    public Catalog findById(Long id);

    /**
     * 更新数量
     * @param id
     * @param add
     */
    public void updateCount(Long id, boolean add);



}
