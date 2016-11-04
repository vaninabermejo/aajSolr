package com.aaj.ui;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

public class JstlView extends InternalResourceView {
	private static final String BASE_PATH = "/WEB-INF/views";
	private static final String TEMPLATE_NAME = "template.jsp";

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		requireNonNull(response);
		requireNonNull(request);
		requireNonNull(model);
		exposeModelAsRequestAttributes(model, request);

		final String dispatcherPath = prepareForRendering(request, response);
		final RequestDispatcher rd = request.getRequestDispatcher(BASE_PATH + TEMPLATE_NAME);
		setContent(dispatcherPath, request);
		includeHeaderAndFooter(!"login".equals(this.getBeanName()), request);

		response.setContentType("text/html");
		rd.include(request, response);
	}

	static void includeHeaderAndFooter(boolean include, HttpServletRequest request) {
		requireNonNull(request).setAttribute("includeHeaderAndFooter", include);
	}

	static void setContent(String contentJsp, HttpServletRequest request) {
		requireNonNull(request).setAttribute("content", contentJsp);
	}
}
