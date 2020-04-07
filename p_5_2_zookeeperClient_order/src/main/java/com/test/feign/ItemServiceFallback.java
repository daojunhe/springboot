package com.test.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class ItemServiceFallback implements ItemFeignClient{

	@Override
    public String queryItemById(@PathVariable("id") Long id) {
        return "服务降级方法queryItemById";
    }
	
}
