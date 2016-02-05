package org.tat.api.library.configuration;



import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tat.api.core.filter.DynamicRequestJsonFilterSupport;
import org.tat.api.core.interceptor.EmbedInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan({"org.tat.api.library","org.tat.api.core.interceptor"})
public class AppConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor());
	}

	@Bean
	HandlerInterceptorAdapter interceptor() {
		return new  EmbedInterceptor();
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    converters.add(converter());
	}


	@Bean
	DynamicRequestJsonFilterSupport filterSupport() {
	    return new DynamicRequestJsonFilterSupport();
	}

	@Bean
	MappingJackson2HttpMessageConverter converter() {
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    ObjectMapper mapper = new ObjectMapper();
	    DynamicRequestJsonFilterSupport filterSupport = filterSupport();
		mapper.setAnnotationIntrospector(filterSupport .getAnnotationIntrospector());
	    mapper.setFilters(filterSupport.getFilterProvider());
	    converter.setObjectMapper(mapper);
	    return converter;
	}
}


