package com.aaj.ui;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ErrorHandlerServlet extends HttpServlet {

	private static final long serialVersionUID = -6870846089912588407L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerServlet.class);

	public ErrorHandlerServlet() {
		LOGGER.debug("Initializing error handling servlet");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requireNonNull(req);
		requireNonNull(resp);
		JstlView.includeHeaderAndFooter(false, req);
		JstlView.setContent("error.jsp", req);
		req.setAttribute("statusPhrase", HttpStatus.valueOf(resp.getStatus()).getReasonPhrase());
		req.getRequestDispatcher("/WEB-INF/views/template.jsp").forward(req, resp);

	}

}
