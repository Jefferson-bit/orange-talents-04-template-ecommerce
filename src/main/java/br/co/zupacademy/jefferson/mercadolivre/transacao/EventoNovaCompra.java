package br.co.zupacademy.jefferson.mercadolivre.transacao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;

@Service
public class EventoNovaCompra {

	@Autowired
	private Set<ObserverEventoCompra> onEventoCompra;
	
	@Autowired
	private SendErrorEmail erroEmail;
	
	public void processaEvento(Compra compra) {
		if (compra.processadaComSucesso()) {
			onEventoCompra.forEach(objEvent -> objEvent.processa(compra));
		} else {
			System.out.println("Dentro do if");
			erroEmail.processa(compra);
		}

	}
}
