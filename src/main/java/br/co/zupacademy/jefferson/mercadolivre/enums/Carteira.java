package br.co.zupacademy.jefferson.mercadolivre.enums;

public enum Carteira {

	PAG_SEGURO(1, "PAG_SEGURO"), PAY_PAL(2, "PAY_PAL");

	private String tipoCarteira;
	private int code;

	Carteira(int code, String tipoCarteira) {
		this.code = code;
		this.tipoCarteira = tipoCarteira;
	}

	public int getCode() {
		return code;
	}

	public String getTipoCarteira() {
		return tipoCarteira;
	}
}
