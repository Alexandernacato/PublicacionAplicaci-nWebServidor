package com.example.prueba.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PruebaApplication {

	public static void main(String[] args) {
		System.out.println("🚀 Aplicación Products iniciando...");
		SpringApplication.run(PruebaApplication.class, args);
		System.out.println("Aplicación Products ejecutándose correctamente.");
	}

}
