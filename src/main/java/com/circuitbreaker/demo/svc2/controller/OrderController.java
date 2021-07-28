package com.circuitbreaker.demo.svc2.controller;

import java.util.List;

import com.circuitbreaker.demo.svc2.model.Movie;
import com.circuitbreaker.demo.svc2.model.StandarResponse;
import com.circuitbreaker.demo.svc2.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderS;

    @GetMapping("/movies/{id}")

    public ResponseEntity<StandarResponse<Movie[]>> createOrder(@PathVariable String id) {
        logger.info("********************** CALL inside Controller ************************ ");
        StandarResponse<Movie[]> m = orderS.getOrderList(id);
        return new ResponseEntity<StandarResponse<Movie[]>>(m, HttpStatus.OK);
    }

}