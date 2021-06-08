package br.co.zupacademy.jefferson.mercadolivre.transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.co.zupacademy.jefferson.mercadolivre.enums.StatusDaCompra;
import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;

public class FechaTransacaoPayPalRequest implements FechaTransacaoGateway {
	
	@NotBlank
	private String idTransacao;
	@Min(0)
	@Max(1)
	private int status;
	
	@Override
	public Transacao toModel(Compra compra) {
		return new Transacao(idTransacao, this.status == 0 ? StatusDaCompra.ERRO : StatusDaCompra.SUCESSO, compra);
	}
	
	public FechaTransacaoPayPalRequest() {
	}
	
	public String getIdTransacao() {
		return idTransacao;
	}
	
	public int getStatus() {
		return status;
	}
}
