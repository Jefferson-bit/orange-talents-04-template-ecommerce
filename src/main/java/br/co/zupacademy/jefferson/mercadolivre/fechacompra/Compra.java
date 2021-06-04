package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

@Entity
public class Compra {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Produto produto;
	@Column(nullable = false)
	private Integer quantidade;
	@ManyToOne
	private Usuario usuario;
	@Enumerated(EnumType.STRING)
	private Carteira carteira;
	@Enumerated(EnumType.STRING)
	private StatusDaCompra status;
	
	public Compra() {
	}

	public Compra(Produto produto, Integer quantidade, Usuario usuario, Carteira carteira) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.carteira = carteira;
		setStatus(StatusDaCompra.INICIADA);
	}
	
	public Carteira getCarteira() {
		return carteira;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setStatus(StatusDaCompra status) {
		this.status = status;
	}
}
