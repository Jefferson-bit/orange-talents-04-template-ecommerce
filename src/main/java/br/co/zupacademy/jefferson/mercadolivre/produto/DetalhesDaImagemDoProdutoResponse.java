package br.co.zupacademy.jefferson.mercadolivre.produto;

public class DetalhesDaImagemDoProdutoResponse {
	
	private String links;
	
	
	public DetalhesDaImagemDoProdutoResponse() {
	}
	
	public DetalhesDaImagemDoProdutoResponse(ImagemProduto imagemProduto) {
		links = imagemProduto.getLink();
	}
	
	
	public String getLinks() {
		return links;
	}
}
