package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import java.net.URI;

import br.co.zupacademy.jefferson.mercadolivre.enums.Carteira;
import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;

public interface CarteiraAbstract {

	URI redirecionaCompra(Carteira carteira, Produto produto);
}
