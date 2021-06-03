package br.co.zupacademy.jefferson.mercadolivre.pergunta;

import javax.validation.constraints.NotBlank;

import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	public PerguntaRequest() {
	}

	public PerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta toModel(Usuario usuario, Produto produto) {
		return new Pergunta(titulo, usuario, produto);
	}
	
	public String getTitulo() {
		return titulo;
	}
}
