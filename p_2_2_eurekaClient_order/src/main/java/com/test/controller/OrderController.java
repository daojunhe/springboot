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
 *http://127.0.0.1:8092/order/2 无熔断会报错
 *http://127.0.0.1:8092/order2/2 有熔断会调用queryItemByIdFallbackMethod
 *http://127.0.0.1:8092/order3/2 服务降级
 *
 */

@RestController
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("order/{id}")
	public String getOrderById(@PathVariable("id") Long id) {
//		app-item需要用
		String itemUrl = "http://app-item/item/1";
//		String itemUrl = "http://127.0.0.1:8091/item/1";
		String item = restTemplate.getForObject(itemUrl, String.class);
		return "rs:" + id + ";" + " item:"+item;
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 * 熔断
	 */
	@RequestMapping("order2/{id}")
	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
	public String getOrderById2(@PathVariable("id") Long id) {
//		app-item需要用
		String itemUrl = "http://app-item/item/1";
//		String itemUrl = "http://127.0.0.1:8091/item/1";
		String item = restTemplate.getForObject(itemUrl, String.class);
		System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + item);
		
		return "rs:" + id + ";" + " item:"+item;
		
	}
	
	
	/**
     * 请求失败执行的方法
     * fallbackMethod的方法参数个数类型要和原方法一致
     *
     * @param id
     * @return
     */
    public String queryItemByIdFallbackMethod(Long id) {
        return "查询商品信息出错!";
    }
	
	
}
