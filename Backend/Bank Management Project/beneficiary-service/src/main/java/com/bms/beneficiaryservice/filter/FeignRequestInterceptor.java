package com.bms.beneficiaryservice.filter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {
	private static final String AUTHORIZATION = "Authorization";

	@Override
	public void apply(RequestTemplate requestTemplate) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes == null) {
			return;
		}
		HttpServletRequest request = requestAttributes.getRequest();
		if (request == null) {
			return;
		}
		String token = request.getHeader(AUTHORIZATION);
		if (token == null) {
			return;
		}

		List<String> headers = Collections.list(request.getHeaderNames()).stream().collect(Collectors.toList());
		for (String h : headers) {
			requestTemplate.header(h, request.getHeader(h));
		}
	}
}