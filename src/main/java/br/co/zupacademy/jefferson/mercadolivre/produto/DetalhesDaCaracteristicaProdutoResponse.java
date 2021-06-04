package br.co.zupacademy.jefferson.mercadolivre.produto;

public class DetalhesDaCaracteristicaProdutoResponse {
	
	private String nome;
	private String descricao;
	
	
	public DetalhesDaCaracteristicaProdutoResponse() {
	}

	public DetalhesDaCaracteristicaProdutoResponse(CaracteristicaProduto caracteristica) {
		nome = caracteristica.getNome();
		descricao = caracteristica.getDescricao();
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getNome() {
		return nome;
	}
}
