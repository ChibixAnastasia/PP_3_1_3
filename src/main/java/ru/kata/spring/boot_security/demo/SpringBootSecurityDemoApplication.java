package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}


/*	@Bean
	DispatcherServlet dispatcherServlet () {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}*/

}
