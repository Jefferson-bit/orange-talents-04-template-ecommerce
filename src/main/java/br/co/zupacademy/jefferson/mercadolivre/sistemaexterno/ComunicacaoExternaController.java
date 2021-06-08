package br.co.zupacademy.jefferson.mercadolivre.sistemaexterno;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComunicacaoExternaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComunicacaoExternaController.class);

	@PostMapping("/notas-fiscais")
	public void notaFiscal(@Valid @RequestBody NotaFiscalRequest request) {
		LOGGER.info("Nota Fiscal: {} ", request.toString());
	}

	@PostMapping("/ranking")
	public void ranking(@Valid @RequestBody RankingRequest request) {
		LOGGER.info("Ranking: {} ", request.toString());
	}

	@PostMapping("/email")
	public void send(@Valid @RequestBody EmailRequest request) {
		LOGGER.info("Enviando email");
		LOGGER.info("from email: {} ", request.getFromEmail());
		LOGGER.info("from name: {} ", request.getFromName());
		LOGGER.info("to: {} ", request.getTo());
		LOGGER.info("subject: {} ", request.getSubject());
		LOGGER.info("body: {} ", request.getBody());
		LOGGER.info("Content-Type: {} ", request.getContentType());
		LOGGER.info("Email enviado");
	}
}