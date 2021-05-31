package br.co.zupacademy.jefferson.mercadolivre.categoria;

import javax.validation.constraints.NotBlank;

import br.co.zupacademy.jefferson.mercadolivre.utils.UniqueValue;

public class CategoriaRequest {

	@NotBlank(message = "Nome não pode ser nulo")
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Categoria existente")
	private String nome;
	private Long idCategoriaMae;

	public CategoriaRequest() {
	}
	
	public CategoriaRequest(@NotBlank(message = "Nome não pode ser nulo") String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel() {
		return new Categoria(nome);
	}

	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}
}
