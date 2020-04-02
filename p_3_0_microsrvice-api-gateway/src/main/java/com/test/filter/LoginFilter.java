package com.test.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


import io.micrometer.core.instrument.util.StringUtils;
/**
 *  网关过滤器
 *  加入到Spring容器
 * @author Evan
 */
@Component
public class LoginFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true; // 该过滤器需要执行
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
            requestContext.setResponseStatusCode(401); // 设置响应状态码
            requestContext.setResponseBody(" token is empty!!"); // 设置响应状态码
            return null;
        }
        return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return  "pre"; // 设置过滤器类型为：pre
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;// 设置执行顺序为0
	}

}
