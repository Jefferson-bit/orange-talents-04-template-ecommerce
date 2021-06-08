package br.co.zupacademy.jefferson.mercadolivre.sistemaexterno;

import javax.validation.constraints.NotNull;

public class RankingRequest {
	
	@NotNull
	private Long idCompra;
	@NotNull
	private Long idVendedor;
	
	public RankingRequest() {
	}
	
	public RankingRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}

	@Override
	public String toString() {
		return "RankingRequest [idCompra=" + idCompra + ", idVendedor=" + idVendedor + "]";
	}
	
	public Long getIdCompra() {
		return idCompra;
	}
	
	public Long getIdVendedor() {
		return idVendedor;
	}
	
}
