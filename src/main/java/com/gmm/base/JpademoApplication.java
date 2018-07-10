package com.gmm.base;

//Author: Muthu Mariyappan G

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication //The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes,
@ComponentScan({"com.gmm.services","com.gmm.base.rest"}) //Automatic detection of beans for autowiring
@EntityScan("com.gmm.entities") //used to identify JPA entities
@EnableJpaRepositories("com.gmm.repositories") //Enables and scans for Spring Data repositories
public class JpademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpademoApplication.class, args); //This method starts whole Spring Framework
	}
}
