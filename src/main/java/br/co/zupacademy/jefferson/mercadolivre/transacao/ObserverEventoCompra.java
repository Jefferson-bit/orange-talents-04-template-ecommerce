package br.co.zupacademy.jefferson.mercadolivre.transacao;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;

public interface ObserverEventoCompra {
	
	void processa(Compra compra);
}
