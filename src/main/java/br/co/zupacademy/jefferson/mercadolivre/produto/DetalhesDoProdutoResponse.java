package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

import br.co.zupacademy.jefferson.mercadolivre.opiniao.Opiniao;
import br.co.zupacademy.jefferson.mercadolivre.opiniao.OpiniaoResponse;
import br.co.zupacademy.jefferson.mercadolivre.pergunta.Pergunta;
import br.co.zupacademy.jefferson.mercadolivre.pergunta.PerguntaResponse;

public class DetalhesDoProdutoResponse {

	private String nome;
	private Integer quantidade;
	private String descricao;
	private BigDecimal valor;
	private Set<DetalhesDaCaracteristicaProdutoResponse> caracteristicas = new HashSet<>();
	private Set<DetalhesDaImagemDoProdutoResponse> linksDoProduto = new HashSet<>();
	private List<PerguntaResponse> perguntas = new ArrayList<>();
	private List<OpiniaoResponse> opinioes = new ArrayList<>();

	public DetalhesDoProdutoResponse() {
	}

	public DetalhesDoProdutoResponse(Produto produto, Set<CaracteristicaProduto> caracteristicas,
			Set<ImagemProduto> imagemProduto, List<Opiniao> opinioes, List<Pergunta> perguntas) {
		nome = produto.getNome();
		quantidade = produto.getQuantidade();
		descricao = produto.getDescricao();
		valor = produto.getValor();
		caracteristicas.forEach(obj -> this.caracteristicas.add(new DetalhesDaCaracteristicaProdutoResponse(obj)));
		imagemProduto.forEach(obj -> this.linksDoProduto.add(new DetalhesDaImagemDoProdutoResponse(obj)));
		opinioes.forEach(obj -> this.opinioes.add(new OpiniaoResponse(obj)));
		perguntas.forEach(obj -> this.perguntas.add(new PerguntaResponse(obj)));

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

	public Set<DetalhesDaImagemDoProdutoResponse> getLinksDoProduto() {
		return linksDoProduto;
	}


	public List<PerguntaResponse> getPerguntas() {
		return perguntas;
	}

	public List<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}
	
	public OptionalDouble getNotaMedia() {
		IntStream mapToInt = opinioes.stream().mapToInt(obj -> obj.getNota());
		return mapToInt.average();
	}

}
