package com.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author emrys.he
 *订单服务
 */

@RestController
public class ItemController {

	@RequestMapping("item/{id}")
	public String getItemById(@PathVariable("id") Long id) {
		
		return "rs:" + id + ";1";
		
	}
	
}
