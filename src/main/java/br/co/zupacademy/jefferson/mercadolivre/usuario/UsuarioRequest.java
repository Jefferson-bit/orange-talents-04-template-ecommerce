package br.co.zupacademy.jefferson.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UsuarioRequest {

	@Email(message = "Email invalido")
	@NotBlank(message = "Email não pode ser nulo ou vazio")
	private String email;
	@NotBlank(message = "Senha não pode ser nulo ou vazio")
	@Length(min = 6, message = "Senha deve conter no minimo 6 caracteres")
	private String senha;

	public UsuarioRequest() {
	}

	public UsuarioRequest(
			@Email(message = "Email invalido") @NotBlank(message = "Email não pode ser nulo ou vazio") String email,
			@NotBlank(message = "Senha não pode ser nulo ou vazio") String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(email, new SenhaLimpa(senha));
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
}
