package br.co.zupacademy.jefferson.mercadolivre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.co.zupacademy.jefferson.mercadolivre.transacao.SendEmailService;
import br.co.zupacademy.jefferson.mercadolivre.transacao.SendMockEmail;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public SendEmailService sendEmailService() {
		return new SendMockEmail();
	}
}
