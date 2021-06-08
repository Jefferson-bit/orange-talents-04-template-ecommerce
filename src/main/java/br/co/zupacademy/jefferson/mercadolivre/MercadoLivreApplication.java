package br.co.zupacademy.jefferson.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableResourceServer
public class MercadoLivreApplication {
	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

}
