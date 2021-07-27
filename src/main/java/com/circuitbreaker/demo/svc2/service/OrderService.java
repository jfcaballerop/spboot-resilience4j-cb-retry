package com.circuitbreaker.demo.svc2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.circuitbreaker.demo.svc2.model.Movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
@CircuitBreaker(name = "orderService", fallbackMethod = "fallBack")
@Retry(name = "orderService")
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static final String ORDER_SERVICE = "orderService";
    @Autowired
    private RestTemplate restTemplate;

    private int attempts = 1;

    public List<Movie> getOrderList(String id) {

        String url = "http://suing.logesta.com:7013/api/movies";

        logger.info("item {} service call attempted::: {}", attempts++, id);
        // String response = restTemplate.getForObject(url, String.class);
        ResponseEntity<Movie[]> response = restTemplate.getForEntity(url, Movie[].class);
        logger.info("item service called");
        Movie[] movies = response.getBody();
        List<Movie> m = Arrays.asList(movies);
        logger.info("OK " + id + ": Response circuit breaker");
        return m;

    }

    public List<Movie> fallBack(Throwable e) {
        attempts = 1;
        logger.info("********************** Error inside fallBack ************************");
        List<Movie> m = new ArrayList<Movie>();
        return m;

    }

    public List<Movie> fallback_retry(Throwable e) {
        attempts = 1;
        logger.info("********************** Error inside fallback_retry ************************");
        List<Movie> m = new ArrayList<Movie>();
        return m;

    }
}
