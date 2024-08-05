package com.assignment.config;

import com.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    @Autowired
    private CacheManager cacheManager;

    TaskService taskService;

    @Scheduled(fixedRate = 60000)
    public void clearCache() {
        System.out.println("****** clearing the Cache");
        cacheManager.getCacheNames().parallelStream().forEach(
                name -> cacheManager.getCache(name).clear()
        );
    }
}
