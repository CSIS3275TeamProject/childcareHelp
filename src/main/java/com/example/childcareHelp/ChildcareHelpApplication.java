package com.example.childcareHelp;

import com.example.childcareHelp.interceptors.SessionCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Configuration
public class ChildcareHelpApplication implements WebMvcConfigurer {

	@Autowired
	@Qualifier(value = "sessionCheckInterceptor")
	private HandlerInterceptor interceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludePathList = new ArrayList<>();
		excludePathList.add("/");
		excludePathList.add("/login");
		excludePathList.add("/logout");
		excludePathList.add("/family/register");
		excludePathList.add("/family/registerFamily");
		excludePathList.add("/babysitter/register");
		excludePathList.add("/babysitter/registerBabysitter");
		excludePathList.add("/css/**");
		excludePathList.add("/images/**");
		excludePathList.add("/js/**");
		registry.addInterceptor(new SessionCheckInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(excludePathList);

	}

	public static void main(String[] args) {
		SpringApplication.run(ChildcareHelpApplication.class, args);
		//test by hye kyung ko
	}

}
