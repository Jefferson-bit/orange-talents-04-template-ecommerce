package br.co.zupacademy.jefferson.mercadolivre.transacao;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;
import br.co.zupacademy.jefferson.mercadolivre.sistemaexterno.EmailRequest;

public interface SendEmailService {
	EmailRequest send(Compra compra);
}
