package br.co.zupacademy.jefferson.mercadolivre.pergunta;

public class PerguntaResponse {
	
	private String titulo;
	
	public PerguntaResponse() {
	}
	
	public PerguntaResponse(Pergunta pergunta) {
		titulo = pergunta.getTitulo();
	}
	
	public String getTitulo() {
		return titulo;
	}
}
