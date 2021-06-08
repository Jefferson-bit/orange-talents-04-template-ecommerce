package br.co.zupacademy.jefferson.mercadolivre.transacao;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;

public interface FechaTransacaoGateway {
	Transacao toModel(Compra compra);
}
