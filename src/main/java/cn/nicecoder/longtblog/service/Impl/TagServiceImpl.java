package cn.nicecoder.longtblog.service.Impl;

import cn.nicecoder.longtblog.dao.TagDao;
import cn.nicecoder.longtblog.entity.Tag;
import cn.nicecoder.longtblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.cache.annotation.CacheResult;
import java.util.List;

/**
 * @Author: longt
 * @Date: 2019/3/29 15:50
 * @Description: 实现类
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    @CacheResult(cacheName="tagPage")
    public Page<Tag> tagPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "name");
        return tagDao.findAll(pageable);
    }

    @Override
    public Tag tagCreate(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public Tag findById(Long id) {
        return tagDao.findById(id).get();
    }

    @Override
    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public List<Tag> findTagsById(Long artId) {
        return tagDao.findByArtId(artId);
    }
}
