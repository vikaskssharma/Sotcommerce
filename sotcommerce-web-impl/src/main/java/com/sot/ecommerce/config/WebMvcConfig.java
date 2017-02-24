package com.sot.ecommerce.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@ComponentScan(basePackages = { "com.sot.ecommerce." }, useDefaultFilters = false, includeFilters = @Filter({ Controller.class }))
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);

		registry.addViewController("/login").setViewName("login");
		
		registry.addViewController("admin").setViewName("admin");
	}

	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		// NB, selecting HTML5 as the template mode.
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		return resolver;

	}

	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		return engine;
	}

/*	@Bean
	public ViewResolver viewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] { "*" });
		viewResolver.setCache(false);
		return viewResolver;
	}*/

	
	 @Bean 
	 public ViewResolver resolver() { 
		 InternalResourceViewResolver url = new InternalResourceViewResolver(); url.setPrefix("WEB-INF/views/");
	     url.setSuffix(".jsp"); 
	   return url; 
	  }
	 

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();

		Properties mappings = new Properties();
		mappings.put("org.springframework.dao.DataAccessException", "error");
		b.setExceptionMappings(mappings);
		return b;
	}
}
