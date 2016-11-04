package com.aaj.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.aaj.config.security.LocalTrustStoreSetter;
import com.aaj.ui.ErrorHandlerServlet;
import com.github.pukkaone.jsp.EscapeXmlELResolverListener;

@EnableAspectJAutoProxy
@EnableWebMvc
@Configuration
@ComponentScan("com.aaj")
@OverrrideApplicationProperties
public class AppInitializer implements WebApplicationInitializer {
	public static final String ENV_VARIABLE = "com.aaj.environment";

	static final AnnotationConfigWebApplicationContext context;

	@Autowired
	private Environment env;

	static {
		context = new AnnotationConfigWebApplicationContext();
		context.register(AppInitializer.class);
	}

	@PostConstruct
	public void init() {
		if ("local".equals(env.getProperty(ENV_VARIABLE))) {
			LocalTrustStoreSetter.localTrustStore();
		}
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addListener(new RequestContextListener());
		servletContext.addListener(new EscapeXmlELResolverListener());

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
				new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.addMapping("/login");
		dispatcher.addMapping("/logout");

		servletContext.addServlet("error-handler", ErrorHandlerServlet.class).addMapping("/error-handler");
		// this was added due to an issue with spring security. it should be
		// resolved
		// anyway don't think that we need to add this for the porpuse of the
		// training
		// servletContext.addFilter("ieAdditionalHeaderForBootstrapFonts",
		// IECustomResponseHeaderFilter.class)
		// .addMappingForUrlPatterns(null, true, "/*");
		// servletContext.addFilter("responseHeaderCacheFilter", new
		// BrowserCacheControlFilter)

	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPLaceholderConfigure() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Scope(value = "request")
	public SecurityContext securityContext() {
		return SecurityContextHolder.getContext();
	}
}
