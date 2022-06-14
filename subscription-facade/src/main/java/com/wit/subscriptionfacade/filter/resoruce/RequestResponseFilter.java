package com.wit.subscriptionfacade.filter.resoruce;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wit.subscriptiondomain.util.Utils;

@Component
public class RequestResponseFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LogManager.getLogger(RequestResponseFilter.class);

	private static final String X_REQUEST_ID = "X-REQUEST-ID";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
		Map<?, ?> jsonRequest = new HashMap<>();
		try {
			String requestId = request.getHeader(X_REQUEST_ID);
			if (StringUtils.isEmpty(requestId)) {
				requestId = UUID.randomUUID().toString();
			}
			MDC.put(Utils.MDC_REQUEST_ID, requestId);

			RequestWrapper wrapper = new RequestWrapper(request);
			byte[] body = StreamUtils.copyToByteArray(wrapper.getInputStream());
			if (body.length > 0) {
				jsonRequest = new ObjectMapper().readValue(body, Map.class);
			}

			filterChain.doFilter(wrapper, responseWrapper);
			response.addHeader(X_REQUEST_ID, MDC.get(Utils.MDC_REQUEST_ID));
		} finally {
			LOGGER.info("Request URL: {} - Method: {} - Body: {}", request.getRequestURL(), request.getMethod(),
					jsonRequest);
			LOGGER.info("Response Status: {} - Body: {}", response.getStatus(), getRespondeBody(responseWrapper));
		}
	}

	private String getRespondeBody(ContentCachingResponseWrapper responseCacheWrapperObject) throws IOException {
		byte[] responseArray = responseCacheWrapperObject.getContentAsByteArray();
		String responseStr = new String(responseArray, responseCacheWrapperObject.getCharacterEncoding());
		responseCacheWrapperObject.copyBodyToResponse();
		return responseStr;
	}

}
