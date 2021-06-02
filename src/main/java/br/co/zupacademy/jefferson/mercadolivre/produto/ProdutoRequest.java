package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.co.zupacademy.jefferson.mercadolivre.categoria.Categoria;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;

public class ProdutoRequest {

	@NotBlank(message = "Nome não pode ser nulo ou vazio")
	private String nome;
	@NotNull(message = "Quantidade não pode ser nulo")
	@Positive
	private Integer quantidade;
	@NotBlank(message = "Descrição não pode ser nulo ou vazio")
	@Length(max = 1000)
	private String descricao;
	@Positive
	@NotNull(message = "Valor não pode ser nulo")
	private BigDecimal valor;
	@NotNull
	private Long idCategoria;
	@Size(min = 3)
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

	public ProdutoRequest() {
	}
	
	public ProdutoRequest(@NotBlank(message = "Nome não pode ser nulo ou vazio") String nome,
			@NotNull(message = "Quantidade não pode ser nulo") @Positive Integer quantidade,
			@NotBlank(message = "Descrição não pode ser nulo ou vazio") @Length(max = 1000) String descricao,
			@Positive @NotNull(message = "Valor não pode ser nulo") BigDecimal valor, @NotNull Long idCategoria,
			@Size(min = 3) List<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
		this.caracteristicas = caracteristicas;
	}

	public ProdutoRequest(Produto produto, List<CaracteristicaProduto> caracteristicaProduto) {
		nome = produto.getNome();
		quantidade = produto.getQuantidade();
		descricao = produto.getDescricao();
		valor = produto.getValor();
		idCategoria = produto.getCategoria().getId();
		caracteristicaProduto.forEach(obj -> {
			caracteristicas.add(new CaracteristicaRequest(obj));
		});
	}

	public Produto toModel(Categoria categoria, Usuario usuario) {
		return new Produto(nome, quantidade, descricao, valor, categoria, usuario, caracteristicas);
	}
	
	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
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
	
}
