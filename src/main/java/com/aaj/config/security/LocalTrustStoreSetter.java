package com.aaj.config.security;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalTrustStoreSetter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalTrustStoreSetter.class);

	public static void localTrustStore() {
		LOGGER.info("using local trustore");
		Properties systemProps = System.getProperties();
		String path = LocalTrustStoreSetter.class.getResource("/jssecacerts.jks").getPath();
		systemProps.put("javax.net.ssl.trustStore", path);
		System.setProperties(systemProps);
	}

}
