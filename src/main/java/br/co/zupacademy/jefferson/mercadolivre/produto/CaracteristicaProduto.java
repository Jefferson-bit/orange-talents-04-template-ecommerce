package br.co.zupacademy.jefferson.mercadolivre.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank(message = "Não pode ser nulo ou vazio") String nome;
	private @NotBlank(message = "Não pode ser nulo ou vazio") String descricao;
	@ManyToOne
	private Produto produto;

	public CaracteristicaProduto(@NotBlank(message = "Não pode ser nulo ou vazio") String nome,
			@NotBlank(message = "Não pode ser nulo ou vazio") String descricao, @NotNull Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	@Deprecated
	public CaracteristicaProduto() {
	}
	
	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		CaracteristicaProduto other = (CaracteristicaProduto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

}