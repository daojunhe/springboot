package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.netflix.ribbon.proxy.annotation.Hystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix //熔断
public class OrderApp {

	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}

	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}
}
