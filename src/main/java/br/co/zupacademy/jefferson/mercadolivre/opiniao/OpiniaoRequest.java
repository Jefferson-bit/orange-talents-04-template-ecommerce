package br.co.zupacademy.jefferson.mercadolivre.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

public class OpiniaoRequest {

	@Min(1)
	@Max(5)
	private Integer nota;
	@NotBlank(message = "titulo não pode ser nulo ou vazio")
	private String titulo;
	@NotBlank(message = "descrição não pode ser nulo ou vazio")
	@Length(max = 500)
	private String descricao;

	public OpiniaoRequest() {
	}

	public OpiniaoRequest(@Min(1) @Max(5) Integer nota,
			@NotBlank(message = "titulo não pode ser nulo ou vazio") String titulo,
			@NotBlank(message = "descrição não pode ser nulo ou vazio") @Length(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao toModel(Usuario usuario, Produto produto) {
		return new Opiniao(nota, titulo, descricao, usuario, produto);
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
