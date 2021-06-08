package br.co.zupacademy.jefferson.mercadolivre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.co.zupacademy.jefferson.mercadolivre.transacao.SendEmail;
import br.co.zupacademy.jefferson.mercadolivre.transacao.SendEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Bean
	public SendEmailService sendEmailService() {
		return new SendEmail();
	}
}
