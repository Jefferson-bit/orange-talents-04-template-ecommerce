package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.springframework.util.Assert;

import br.co.zupacademy.jefferson.mercadolivre.categoria.Categoria;
import br.co.zupacademy.jefferson.mercadolivre.opiniao.Opiniao;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;
import br.co.zupacademy.jefferson.mercadolivre.utils.UsuarioLogado;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private Integer quantidade;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private BigDecimal valor;
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Usuario usuario;
	@Column(nullable = false)
	private Instant cadastradoEm;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade= CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade= CascadeType.MERGE)
	private List<Opiniao> opinioes = new ArrayList<>();
	
	@Deprecated
	public Produto() {
	}
	
	public Produto(String nome, Integer quantidade, String descricao, BigDecimal valor, Categoria categoria,
			Usuario usuario, Collection<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica ->  caracteristica.toModel(this))
		.collect(Collectors.toSet()));
		Assert.isTrue(caracteristicas.size() >= 3, "Todo produto precisa ter no minimo 3 caracteristicas");
	}


	@PrePersist
	public void createdAt() {
		cadastradoEm = Instant.now();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public static class ProdutoBuilder {
		private String nome;
		private Integer quantidade;
		private String descricao;
		private BigDecimal valor;
		private Categoria categoria;
		private Usuario usuario;
		private Set<CaracteristicaRequest> caracteristicas = new HashSet<>();
		
		public ProdutoBuilder() {
		}
		
		public ProdutoBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public ProdutoBuilder quantidade(Integer quantidade) {
			this.quantidade = quantidade;
			return this;
		}
		
		public ProdutoBuilder descricao(String descricao) {
			this.descricao = descricao;
			return this;
		}
		
		public ProdutoBuilder valor(BigDecimal valor) {
			this.valor = valor;
			return this;
		}
		
		public ProdutoBuilder categoria(Categoria categoria) {
			this.categoria = categoria;
			return this;
		}
		
		public ProdutoBuilder usuario(Usuario usuario) {
			this.usuario = usuario;
			return this;
		}
		
		public ProdutoBuilder caracteristicas(Set<CaracteristicaRequest> caracteristicas) {
			this.caracteristicas = caracteristicas;
			return this;
		}
		
		public Produto build() {
			return new Produto(nome, quantidade, descricao, valor, categoria, usuario, caracteristicas);
		}
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean verificaSeEDosuarioLogado(UsuarioLogado usuarioLogado) {
		Usuario usuario = usuarioLogado.getUsuarioLogado();
		return this.usuario.equals(usuario);
	}
}
