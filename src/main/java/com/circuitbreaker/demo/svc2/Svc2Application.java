package com.circuitbreaker.demo.svc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Svc2Application {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	public static void main(String[] args) {
		SpringApplication.run(Svc2Application.class, args);
	}

}
