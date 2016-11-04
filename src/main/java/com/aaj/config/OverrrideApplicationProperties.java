package com.aaj.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Retention(RetentionPolicy.RUNTIME)
@PropertySources({ @PropertySource("classpath:config/default.properties"),
		@PropertySource(value = "classpath:config/${com.aaj.environment:local}.properties", ignoreResourceNotFound = true) })
public @interface OverrrideApplicationProperties {

}
