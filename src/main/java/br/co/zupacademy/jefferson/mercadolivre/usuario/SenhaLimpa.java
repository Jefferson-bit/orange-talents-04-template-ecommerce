package br.co.zupacademy.jefferson.mercadolivre.usuario;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {
	
	private String senha;

	public SenhaLimpa() {
	}
	
	public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
		this.senha = senha;
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
