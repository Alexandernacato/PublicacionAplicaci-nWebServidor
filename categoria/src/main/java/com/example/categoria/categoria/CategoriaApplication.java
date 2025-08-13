package com.example.categoria.categoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CategoriaApplication {

	public static void main(String[] args) {
			System.out.println("🚀 Aplicación Products iniciando...");
		SpringApplication.run(CategoriaApplication.class, args);
		System.out.println("Aplicación Products ejecutándose correctamente.");
	}

}
