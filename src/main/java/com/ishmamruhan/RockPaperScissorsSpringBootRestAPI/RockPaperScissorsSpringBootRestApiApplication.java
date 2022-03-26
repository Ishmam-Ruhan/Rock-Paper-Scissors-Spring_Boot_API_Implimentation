package com.ishmamruhan.RockPaperScissorsSpringBootRestAPI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Rock-Paper-Scissors-API-Development", version = "1.0.0-Snapshot"))
public class RockPaperScissorsSpringBootRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockPaperScissorsSpringBootRestApiApplication.class, args);
	}

}
