package com.aaj.service.impl;

import org.springframework.stereotype.Service;

@Service
public class DummyService implements com.aaj.service.DummyService {

	@Override
	public void doNothing() {
		System.out.println("doing nothing");

	}

}
