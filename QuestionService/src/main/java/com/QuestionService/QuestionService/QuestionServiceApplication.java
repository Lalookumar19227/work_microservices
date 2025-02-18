package com.QuestionService.QuestionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EnableJpaRepositories(basePackages = "com.QuestionService.QuestionService.repositories")
//@EntityScan(basePackages = "com.QuestionService.QuestionService.entities")
//@EnableJpaRepositories(basePackages = "com.QuestionService.QuestionService.repositories")
//@ComponentScan(basePackages = "com.QuestionService.QuestionService")

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.QuestionService.QuestionService.repositories")
public class QuestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);

		System.out.println("hello Question Service ");
	}

}
