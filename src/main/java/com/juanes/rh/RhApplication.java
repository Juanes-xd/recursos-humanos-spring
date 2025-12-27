package com.juanes.rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class RhApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER"));
		System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
		SpringApplication.run(RhApplication.class, args);
	}

}
