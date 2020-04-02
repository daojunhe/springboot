package org.p_1_helloSpringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("hello")
	public String hello() {
		
		System.out.println("hello springboot");
		
		return "hello springboot";
	}
}
