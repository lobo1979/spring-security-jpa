package com.pt.springsecurityjpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

// dony by https://www.youtube.com/watch?v=TNt3GHuayXs
// https://o7planning.org/10683/create-a-shopping-cart-web-application-with-spring-boot-hibernate
@SpringBootApplication
public class SpringSecurityJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaApplication.class, args);
	}


}
