package com.softplangestaoambiental;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Softplan-API", version = "1", description = "API desenvolvida para o Desafio Softplan Gestao Ambiental"))
public class SoftplangestaoambientalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftplangestaoambientalApplication.class, args);
	}

}
