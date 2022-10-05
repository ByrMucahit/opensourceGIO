package com.example.opensourcegio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OpenSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenSourceApplication.class, args);
	}

}
