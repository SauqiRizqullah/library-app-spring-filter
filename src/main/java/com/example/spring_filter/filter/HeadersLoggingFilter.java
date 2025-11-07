package com.example.spring_filter.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class HeadersLoggingFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info("1. Auth:");
        log.info("Authentication Type: {}", request.getAuthType());
        log.info("User Principal: {}", request.getUserPrincipal());
//        log.info("Authorized User: {}", SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("2. Network and Client:");
        log.info("Remote Address: {}", request.getRemoteAddr());
        log.info("User Agent: {}", request.getHeader("User-Agent"));
        log.info("Referer: {}", request.getHeader("Referer"));
        log.info("X-Forwareded-For: {}", request.getHeader("X-Forwarded-For"));
        log.info("3. Request:");
        log.info("HTTP Method: {}", request.getMethod());
        log.info("Request URI: {}", request.getRequestURI());
        log.info("Query String: {}", request.getQueryString());
        Collections.list(request.getHeaderNames())
                .forEach(header -> {
                    log.info("Header: {}={}", header, request.getHeader(header));
                });
        log.info("Content Type: {}", request.getContentType());
        log.info("Content Length: {}", request.getContentLengthLong());
        chain.doFilter(request, response);
    }
}
