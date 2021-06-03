package br.co.zupacademy.jefferson.mercadolivre.pergunta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockEmail implements Mail{
	private static final Logger LOGGER = LoggerFactory.getLogger(MockEmail.class);
	
	@Override
	public void send(String fromEmail, String fromName, String to, String subject, String body) {
		LOGGER.info("from email: {} ",fromEmail);
		LOGGER.info("from name: {} " ,fromName);
		LOGGER.info("to: {} " ,to);
		LOGGER.info("subject: {} " ,subject);
		LOGGER.info("body: {} ",body);
	}

}
