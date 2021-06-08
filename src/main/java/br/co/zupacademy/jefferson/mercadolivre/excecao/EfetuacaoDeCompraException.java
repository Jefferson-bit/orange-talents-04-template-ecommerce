package br.co.zupacademy.jefferson.mercadolivre.excecao;

public class EfetuacaoDeCompraException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EfetuacaoDeCompraException(String msg) {
		super(msg);
	}
}
