package ru.practice4v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.practice4v2.beans.*;
import ru.practice4v2.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication(scanBasePackages = "ru.practice4v2")

public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
