package com.aaj.contoller;

import static java.util.Objects.requireNonNull;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaj.service.DummyService;

public class DummyController {
	private static Logger LOGGER = LoggerFactory.getLogger(DummyController.class);
	private final DummyService service;

	@Inject
	public DummyController(DummyService service) {
		this.service = requireNonNull(service);

	}

	@RequestMapping("/")
	public String doNothing() {
		service.doNothing();
		return "index";

	}

}
