package cn.nicecoder.longtblog.service;

import cn.nicecoder.longtblog.entity.Agree;

/**
 *
 */
public interface AgreeService {
    public Agree create(Long id, String type, Long uid);
}
