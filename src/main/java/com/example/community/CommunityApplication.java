package com.example.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication {
	private static final Class<?>[] CONFIG_CLASSES = new Class<?>[] {
			CommunityApplication.class
	};

	public static void main(String[] args) {
		SpringApplication.run(CONFIG_CLASSES, args);
	}
}
