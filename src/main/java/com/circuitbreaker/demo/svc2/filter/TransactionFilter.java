package com.circuitbreaker.demo.svc2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.circuitbreaker.demo.svc2.model.UserMock;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Component
@Order(1)
@CircuitBreaker(name = "orderService", fallbackMethod = "fallBack")
@Retry(name = "orderService")
public class TransactionFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(TransactionFilter.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String url = "http://localhost:3000/redisMock/getJson/C1B10D08C6E614F31891FD9C0D843502";
        
        logger.info("*** FILTER START: ");
        ResponseEntity<UserMock> resp = restTemplate.getForEntity(url, UserMock.class);
        HttpServletRequest req = (HttpServletRequest) request;
        ObjectMapper objectMapper = new ObjectMapper();
        
        
        logger.info("*** FILTER CALL: {}", objectMapper.writeValueAsString(resp.getBody()));
        chain.doFilter(request, response);
        logger.info("*** FILTER END : {}", req.getRequestURI());
    }

    // other methods
    public void fallBack(ServletRequest request, ServletResponse response, FilterChain chain, Throwable e)
            throws IOException, ServletException {
        logger.info("*** Error inside fallBack Filter *****");
        chain.doFilter(request, response);

    }
}