package com.lemon.message.filter;

import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestLimitFilter extends OncePerRequestFilter {
    private final String HEADER_USER = "user";
    private final int RATE_LIMIT = 5;

    private LoadingCache<String, String> loadingCache;
    private RateLimiter rateLimiter;

    @Autowired
    public RequestLimitFilter(LoadingCache<String, String> loadingCache, RateLimiter rateLimiter) {
        this.loadingCache = loadingCache;
        this.rateLimiter = rateLimiter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String user = request.getHeader(HEADER_USER);

        if (user == null){
            System.out.println("User need for authorization");
            throw new ServletException("User need for authorization");
        }

        if (user.isEmpty()){
            System.out.println("User don't be blank");
            throw new ServletException("User don't be blank");
        }

        String u = loadingCache.getIfPresent(user);

        if (u == null){
            loadingCache.put(user, user);
            rateLimiter.setRate(RATE_LIMIT);
        }
        else{
            if (rateLimiter.getRate() == 1){
                System.out.println("Too many connections");
                throw  new ServletException("Too many connections");
            }

            rateLimiter.setRate(rateLimiter.getRate()-1);
        }

        System.out.println(rateLimiter.getRate());

        filterChain.doFilter(request, httpServletResponse);
    }
}
