package br.co.zupacademy.jefferson.mercadolivre.transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.co.zupacademy.jefferson.mercadolivre.enums.StatusDaCompra;
import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;
import br.co.zupacademy.jefferson.mercadolivre.utils.UniqueValue;
import br.co.zupacademy.jefferson.mercadolivre.utils.ValidaStatus;

public class FechaTransacaoPagSeguroRequest implements FechaTransacaoGateway{

	@NotBlank
	@UniqueValue(domainClass = Transacao.class,fieldName = "idTransacao", message = "Essa transação já foi processada")
	private String idTransacao;
	@ValidaStatus(message = "Essa compra já foi processada com sucesso")
	@NotNull
	private StatusDaCompra status;

	public FechaTransacaoPagSeguroRequest() {
	}

	public FechaTransacaoPagSeguroRequest(@NotBlank String idTransacao, @NotNull StatusDaCompra status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	public Transacao toModel(Compra compra) {
		return new Transacao(idTransacao, status, compra);
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusDaCompra getStatus() {
		return status;
	}


}
