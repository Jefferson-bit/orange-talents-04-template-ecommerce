package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.util.Assert;

import br.co.zupacademy.jefferson.mercadolivre.enums.Carteira;
import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.transacao.FechaTransacaoGateway;
import br.co.zupacademy.jefferson.mercadolivre.transacao.Transacao;
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
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	public Compra() {
	}

	public Compra(Produto produto, Integer quantidade, Usuario usuario, Carteira carteira) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.carteira = carteira;
	}

	public Long getId() {
		return id;
	}
	
	public Long getDonoDoProduto() {
		return this.produto.getUsuario().getId();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Long getComprador() {
		return this.usuario.getId();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void tentaEfetuarUmaTransacao(@Valid FechaTransacaoGateway request) {
	Transacao novaTransacao = request.toModel(this);
		
		Assert.state(!this.transacoes.contains(novaTransacao),
				"Já existe uma transacao igual a essa processada "
						+ novaTransacao);
		Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");
		this.transacoes.add(novaTransacao);
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
				.filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"existe mais de uma transacao concluida com sucesso "+this.id);
		return transacoesConcluidasComSucesso;
	}
	
	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}
	
}
