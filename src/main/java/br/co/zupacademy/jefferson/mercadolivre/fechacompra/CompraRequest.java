package br.co.zupacademy.jefferson.mercadolivre.fechacompra;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.co.zupacademy.jefferson.mercadolivre.enums.Carteira;
import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

public class CompraRequest {
	
	@NotNull
	@Positive
	private Integer quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private Carteira carteira;
	
	public CompraRequest() {
	}
	
	public CompraRequest(@NotNull @Positive Integer quantidade, @NotNull Long idProduto) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
	}

	public Compra toModel(Produto produto, Usuario usuario) {
		return new Compra(produto, quantidade, usuario, carteira);
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Carteira getCarteira() {
		return carteira;
	}
}
