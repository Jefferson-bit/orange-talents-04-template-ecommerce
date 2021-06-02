package br.co.zupacademy.jefferson.mercadolivre.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@ManyToOne
	private Categoria idCategoriaMae;

	@Deprecated
	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
	}
	/**
	 * 
	 * @param id
	 * @param nome
	 * CONSTRUTOR SOMENTE PARA TESTE
	 */
	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setIdCategoriaMae(Categoria idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}
}