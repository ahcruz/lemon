package com.lemon.message.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RateLimiterConfig {
    @Bean
    public RateLimiter rateLimiter(){
        return RateLimiter.create(5);
    };
}
