package com.rajangautam.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.rajangautam.wallet.auth.AuthMiddleware;

@SpringBootApplication
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthMiddleware> filterRegistrationBean() {
		FilterRegistrationBean<AuthMiddleware> registrationBean = new FilterRegistrationBean<>();
		AuthMiddleware authMiddleware = new AuthMiddleware();
		registrationBean.setFilter(authMiddleware);
		registrationBean.addUrlPatterns("/api/categories/*");
		registrationBean.addUrlPatterns("/api/transactions/*");
		return registrationBean;
	}
}
