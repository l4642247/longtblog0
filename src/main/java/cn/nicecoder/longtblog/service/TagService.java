package cn.nicecoder.longtblog.service;

import cn.nicecoder.longtblog.entity.Tag;
import cn.nicecoder.longtblog.entity.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {

    /**
     * 查询类别
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<Tag> tagPage(int pageNumber, int pageSize);

    /**
     * 创建
     * @param tag
     * @return
     */
    public Tag tagCreate(Tag tag);


    /**
     * 删除
     * @param id
     */
    public void deleteTag(Long id);


    /**
     * 查询
     * @param id
     * @return
     */
    public Tag findById(Long id);

    /**
     * 查询
     * @param name
     * @return
     */
    public Tag findByName(String name);

    /**
     * 查询
     * @param artId
     * @return
     */
    public List<Tag> findTagsById(Long  artId);
}
