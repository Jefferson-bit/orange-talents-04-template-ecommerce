package br.co.zupacademy.jefferson.mercadolivre.usuario;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import br.co.zupacademy.jefferson.mercadolivre.perfis.Perfil;

@Entity
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Instant cadastradoEm;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usuario_perfil", 
	joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private Set<Perfil> perfis = new HashSet<>();

	@Deprecated
	public Usuario() {
	}

	public Usuario(String email, SenhaLimpa senhaLimpa) {
		Assert.notNull(email, "Email não pode ser nulo");
		Assert.notNull(senhaLimpa, "Objeto do tipo senha não pode ser nulo");
		this.email = email;
		this.senha = senhaLimpa.hash();
	}

	@PrePersist
	public void createdAt() {
		cadastradoEm = Instant.now();
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfis.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return senha;
	}
}
