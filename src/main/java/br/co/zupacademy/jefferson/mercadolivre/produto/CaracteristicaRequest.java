package br.co.zupacademy.jefferson.mercadolivre.produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {

	@NotBlank(message = "Não pode ser nulo ou vazio")
	private String nome;
	@NotBlank(message = "Não pode ser nulo ou vazio")
	private String descricao;

	public CaracteristicaRequest(@NotBlank(message = "Não pode ser nulo ou vazio") String nome,
			@NotBlank(message = "Não pode ser nulo ou vazio") String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public CaracteristicaRequest(CaracteristicaProduto caracteristicaProduto) {
		nome = caracteristicaProduto.getNome();
		descricao = caracteristicaProduto.getDescricao();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public CaracteristicaProduto toModel(Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}
}
