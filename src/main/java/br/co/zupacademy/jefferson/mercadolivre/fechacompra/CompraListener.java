package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CompraListener implements ApplicationListener<EmailRequest>{

	private static final Logger LOGGER = LoggerFactory.getLogger(CompraListener.class);

	@Override
	public void onApplicationEvent(EmailRequest event) {
		LOGGER.info("Enviando email");
		LOGGER.info("from email: {} ",event.getFromEmail());
		LOGGER.info("from name: {} " ,event.getFromName());
		LOGGER.info("to: {} " ,event.getTo());
		LOGGER.info("subject: {} " ,event.getSubject());
		LOGGER.info("body: {} ",event.getBody());
		LOGGER.info("Content-Type: {} ",event.getContentType());
		
		int seconds =  (int) ((event.getTimestamp() / 100) %60);
		int minutes = (int) ((event.getTimestamp() / (1000*60)) % 60);
		int hours   =  (int) ((event.getTimestamp() / (1000*60*60)) % 24);
		LocalTime of = LocalTime.of(hours, minutes, seconds);
		
		LOGGER.info("Horario do envio: {} ", of);
		LOGGER.info("Email enviado");
		
	}

}
