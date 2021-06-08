package br.co.zupacademy.jefferson.mercadolivre.sistemaexterno;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idComprador;

	public NotaFiscalRequest() {
	}

	public NotaFiscalRequest(@NotNull Long idCompra, @NotNull Long idComprador) {
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		return "NotaFiscalRequest [idCompra=" + idCompra + ", idComprador=" + idComprador + "]";
	}
	
	public Long getIdCompra() {
		return idCompra;
	}
	
	public Long getIdComprador() {
		return idComprador;
	}
}
