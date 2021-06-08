package br.co.zupacademy.jefferson.mercadolivre.transacao;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;
import br.co.zupacademy.jefferson.mercadolivre.sistemaexterno.EmailRequest;

@Service
public class SendErrorEmail implements ObserverEventoCompra, SendEmailService {

	@Override
	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		EmailRequest request = send(compra);
		restTemplate.postForEntity("http://localhost:8080/email", request, EmailRequest.class);
	}

	@Override
	public EmailRequest send(Compra compra) {
	return new EmailRequest("br.com.zup@bootcamp", "zupper",
			compra.getUsuario().getEmail(), "Transação negada", "Não foi possivel processar sua compra",
		"text/html");
	}
}
