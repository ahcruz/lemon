package com.lemon.message.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public LoadingCache<String, String> loadingCache(){

        CacheLoader<String, String> loader;

        loader = new CacheLoader<String, String>() {

            @Override
            public String load(String s) throws Exception {
                return null;
            }
        };

        LoadingCache<String, String> cache;

        cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .build(loader);

        return cache;
    }

}
