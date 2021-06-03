package br.co.zupacademy.jefferson.mercadolivre.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Integer nota;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String descricao;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Opiniao() {
	}

	public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	
}
