package br.co.zupacademy.jefferson.mercadolivre.transacao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import br.co.zupacademy.jefferson.mercadolivre.enums.StatusDaCompra;
import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String idtransacao;
	@Enumerated(EnumType.STRING)
	private StatusDaCompra status;
	@ManyToOne
	private Compra compra;
	@Column(name = "efetivada_em")
	private LocalDateTime efetivadaEm;

	@Deprecated
	public Transacao() {
	}

	public Transacao(String idtransacao, StatusDaCompra status, Compra compra) {
		this.idtransacao = idtransacao;
		this.status = status;
		this.compra = compra;
	}

	@PrePersist
	public void createdAt() {
		efetivadaEm = LocalDateTime.now();
	}

	public Compra getCompra() {
		return compra;
	}

	public String getIdtransacao() {
		return idtransacao;
	}

	public StatusDaCompra getStatus() {
		return status;
	}
	
	public boolean concluidaComSucesso(){
		return this.status.equals(StatusDaCompra.SUCESSO);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idtransacao == null) ? 0 : idtransacao.hashCode());
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
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idtransacao == null) {
			if (other.idtransacao != null)
				return false;
		} else if (!idtransacao.equals(other.idtransacao))
			return false;
		return true;
	}
}
