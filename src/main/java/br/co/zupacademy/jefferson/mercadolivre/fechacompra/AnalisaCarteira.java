package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.co.zupacademy.jefferson.mercadolivre.enums.Carteira;
import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;

@Component
public class AnalisaCarteira implements CarteiraAbstract{
	
	@Override
	public URI redirecionaCompra(Carteira carteira, Produto produto) {
		if(carteira.equals(Carteira.PAG_SEGURO)) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/retorno-pagseguro/{id}").buildAndExpand(produto.getId()).toUri();
			return URI.create("pagseguro.com/"+ produto.getId() + "?redirectUrl="+uri);
		} else {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/retorno-paypal/{id}").buildAndExpand(produto.getId()).toUri();
			return URI.create("paypal.com/"+ produto.getId() + "?redirectUrl="+uri);
		}
	}

}
