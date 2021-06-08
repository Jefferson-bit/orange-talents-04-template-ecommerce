package br.co.zupacademy.jefferson.mercadolivre.transacao;

import org.springframework.web.client.RestTemplate;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;
import br.co.zupacademy.jefferson.mercadolivre.sistemaexterno.EmailRequest;

public class SendEmail  implements ObserverEventoCompra, SendEmailService {

	@Override
	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		EmailRequest request = send(compra);
		restTemplate.postForEntity("http://localhost:8080/email", request, EmailRequest.class);
	}

	/**
	 * Aqui poderia ter a lógica de envio real, usando o stmp do google, sendgrid ou outros por ai.
	 */
	@Override
	public EmailRequest send(Compra compra) {
		
		EmailRequest emailRequest = new EmailRequest("FromEmail Mockado", "FromName aqui",
			compra.getUsuario().getEmail(), "Subject Aqui", "Body aqui",
		"content type aq");
		return emailRequest;
	}

}
