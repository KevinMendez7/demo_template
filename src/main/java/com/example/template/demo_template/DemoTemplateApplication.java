package com.example.template.demo_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class DemoTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTemplateApplication.class, args);
	}

}
