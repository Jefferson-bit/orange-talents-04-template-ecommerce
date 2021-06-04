package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import br.co.zupacademy.jefferson.mercadolivre.opiniao.OpiniaoResponse;

public class DetalhesDoProdutoResponse {

	private String nome;
	private Integer quantidade;
	private String descricao;
	private BigDecimal valor;
	private Set<DetalhesDaCaracteristicaProdutoResponse> caracteristicas = new HashSet<>();
	private Set<String> linksDaImagem = new HashSet<>();
	private SortedSet<String> perguntas = new TreeSet<>();
	private List<OpiniaoResponse> opinioes = new ArrayList<>();
	private double media;
	private int total;
	public DetalhesDoProdutoResponse() {
	}

	public DetalhesDoProdutoResponse(Produto produto) {
		nome = produto.getNome();
		quantidade = produto.getQuantidade();
		descricao = produto.getDescricao();
		valor = produto.getValor();
		//1
		this.caracteristicas = produto.mapeiaCaracteristicas(DetalhesDaCaracteristicaProdutoResponse::new);
		//1
		this.linksDaImagem = produto.mapeiaImagensDoProduto(obj -> obj.getLink());
		//1
		this.perguntas = produto.mapeiaPergunta(obj -> obj.getTitulo());
		//1
		this.opinioes = produto.mapeiaOpiniao(OpiniaoResponse::new);
		media = produto.media();
		total = produto.total();
	}

	public Set<DetalhesDaCaracteristicaProdutoResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public Set<String> getLinksDaImagem() {
		return linksDaImagem;
	}
	
	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public List<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}
	
	public double getMedia() {
		return media;
	}
	
	public int getTotal() {
		return total;
	}

}
