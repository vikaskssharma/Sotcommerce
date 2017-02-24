package com.sot.ecommerce.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class /* , SecurityConfig.class */};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}



	/*
	 * @Override public void onStartup(ServletContext servletContext) throws
	 * ServletException { super.onStartup(servletContext);
	 * FilterRegistration.Dynamic reg =
	 * servletContext.addFilter("SiteMeshFilter",
	 * "com.opensymphony.sitemesh.webapp.SiteMeshFilter");
	 * EnumSet<DispatcherType> disps = EnumSet.of( DispatcherType.REQUEST,
	 * DispatcherType.FORWARD); reg.addMappingForUrlPatterns(disps, true, "/*");
	 * }
	 */

}
