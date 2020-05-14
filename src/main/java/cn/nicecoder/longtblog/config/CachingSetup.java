package cn.nicecoder.longtblog.config;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Author: longt
 * @Date: 2019/6/4 16:00
 * @Description:
 */
@Component
public  class CachingSetup implements JCacheManagerCustomizer {
    @Override
    public void customize(CacheManager cacheManager)
    {
        MutableConfiguration mutableConfiguration = new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 10)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true);
        cacheManager.createCache("catalogPage", mutableConfiguration);
        cacheManager.createCache("findTop8", mutableConfiguration);
        cacheManager.createCache("tagPage", mutableConfiguration);
    }
}