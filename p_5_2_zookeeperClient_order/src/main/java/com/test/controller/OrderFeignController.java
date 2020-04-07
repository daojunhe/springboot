package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.feign.ItemFeignClient;

/**
 * 
 * @author emrys.he
 *订单服务
 *
 *设置统一的hystrix fallback接口
 *
 */

@RestController
public class OrderFeignController {
	@Autowired
	private ItemFeignClient itemFeignClient;

	@RequestMapping("order3/{id}")
	public String getOrderById3(@PathVariable("id") Long id) {
//		app-item需要用
		String item = itemFeignClient.queryItemById(id);
		System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + item);
		return "rs:" + id + ";" + " item:"+item;
		
	}
	
}
