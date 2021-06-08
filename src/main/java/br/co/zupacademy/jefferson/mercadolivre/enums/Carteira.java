package br.co.zupacademy.jefferson.mercadolivre.enums;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;

public enum Carteira {

	PAG_SEGURO() {
		@Override
		public URI redirecionaUrl(Compra compra) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toUri();
			return URI.create("pagseguro.com/"+ compra.getId() + "?redirectUrl="+uri);
		}
	}, PAY_PAL() {
		@Override
		public URI redirecionaUrl(Compra compra) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toUri();
			return URI.create("paypal.com/"+ compra.getId() + "?redirectUrl="+uri);
		}
	};

	public abstract URI redirecionaUrl(Compra compra);
}
