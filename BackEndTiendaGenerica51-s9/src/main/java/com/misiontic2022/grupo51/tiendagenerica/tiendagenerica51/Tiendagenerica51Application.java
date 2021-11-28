package com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Tiendagenerica51Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Tiendagenerica51Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Tiendagenerica51Application.class);
	}

}
