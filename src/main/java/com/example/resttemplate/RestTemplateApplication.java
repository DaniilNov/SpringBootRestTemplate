package com.example.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan (basePackages = {"com.example.resttemplate"})
@SpringBootApplication

public class RestTemplateApplication {


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}

}
