package br.co.zupacademy.jefferson.mercadolivre.usuario;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.util.Assert;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Instant cadastradoEm;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	
	@Deprecated
	public Usuario() {
	}

	public Usuario(String email, SenhaLimpa senhaLimpa) {
		Assert.notNull(email, "Email não pode ser nulo");
		Assert.notNull(senhaLimpa, "Objeto do tipo senha não pode ser nulo");
		this.email = email;
		this.senha = senhaLimpa.hash() ;
	}

	@PrePersist
	public void createdAt() {
		cadastradoEm = Instant.now();
	}
	
	public String getEmail() {
		return email;
	}
}
