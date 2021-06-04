package br.co.zupacademy.jefferson.mercadolivre.opiniao;

public class OpiniaoResponse {

	private Integer nota;
	private String titulo;
	private String descricao;
	
	public OpiniaoResponse() {
	}

	public OpiniaoResponse(Opiniao opiniao) {
		nota = opiniao.getNota();
		titulo = opiniao.getTitulo();
		descricao = opiniao.getDescricao();
	}
	
	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
