package br.co.zupacademy.jefferson.mercadolivre.pergunta;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private Instant criadaEm;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Pergunta() {
	}
	
	public Pergunta(String titulo, Usuario usuario, Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	@PrePersist
	public void createdAt() {
		criadaEm = Instant.now();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public Usuario getDonoDoProduto() {
		return produto.getUsuario();
	}

	public String getTitulo() {
		return titulo;
	}
}
