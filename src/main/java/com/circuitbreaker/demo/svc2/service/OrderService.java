package com.circuitbreaker.demo.svc2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.circuitbreaker.demo.svc2.model.Movie;
import com.circuitbreaker.demo.svc2.model.StandarResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import com.circuitbreaker.demo.svc2.config.errorCodes;

@Service
@CircuitBreaker(name = "orderService", fallbackMethod = "fallBack")
@Retry(name = "orderService")
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static final String ORDER_SERVICE = "orderService";
    @Autowired
    private RestTemplate restTemplate;

    private int attempts = 1;

    public <T> StandarResponse<Movie[]> getOrderList(String id) {

        String url = "http://localhost:3000/api/movies/" + id;

        logger.info("item {} service call attempted::: {}", attempts++, id);
        // String response = restTemplate.getForObject(url, String.class);
        ParameterizedTypeReference<StandarResponse<Movie[]>> typeRef = new ParameterizedTypeReference<StandarResponse<Movie[]>>() {
        };
        ResponseEntity<StandarResponse<Movie[]>> result = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);

        // ResponseEntity<StandarResponse<T>> response = restTemplate.getForEntity(url,
        // StandarResponse<T>.class);
        logger.info("item service called");
        StandarResponse<Movie[]> resp = result.getBody();
        logger.info("OK " + id + ": Response circuit breaker");
        return resp;

    }

    public <T> StandarResponse<Movie[]> fallBack(Throwable e) {
        attempts = 1;
        logger.info("********************** Error inside fallBack ************************");
        Movie m[] = {};
        StandarResponse<Movie[]> resp = new StandarResponse<Movie[]>();
        resp.codigo = errorCodes.HTTP_CB_ERROR;
        resp.error = "true";
        resp.mensaje = "CircuitBreaker error response";
        resp.datos = m;

        return resp;

    }

    public <T> StandarResponse<Movie[]> fallback_retry(Throwable e) {
        attempts = 1;
        logger.info("********************** Error inside fallback_retry ************************");
        Movie m[] = {};
        StandarResponse<Movie[]> resp = new StandarResponse<Movie[]>();
        resp.codigo = errorCodes.HTTP_RETRY_ERROR;
        resp.error = "true";
        resp.mensaje = "Retry error response";
        resp.datos = m;

        return resp;

    }
}
