package br.co.zupacademy.jefferson.mercadolivre.enums;

public enum StatusDaCompra {
	
	SUCESSO(0,"SUCESSO"),
	ERRO(1, "ERRO");
	
	private int code;
	private String status;
	
	StatusDaCompra(int code, String status) {
		this.code = code;
		this.status = status;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getStatus() {
		return status;
	}
}
