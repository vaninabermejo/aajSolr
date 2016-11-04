package com.aaj.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IECustomResponseHeaderFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(IECustomResponseHeaderFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.addHeader("X-UA-Compatible", "IE=Edge");
		} catch (RuntimeException r) {
			LOGGER.warn("error handling custom IE heasders to the response");
			LOGGER.debug("error:", r);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
