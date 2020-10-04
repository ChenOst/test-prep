package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestPreparationApplication {

	@Autowired
	private QuestionRepository repository;
	DocxParser docxParser;

	public static void main(String[] args) {
		SpringApplication.run(TestPreparationApplication.class, args);
	}

	@Bean
	public CommandLineRunner sendDatabase() {
		return (args) -> {
			repository.saveAll(docxParser.readDocxFile());
		};
	}
}
