package br.co.zupacademy.jefferson.mercadolivre.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;
import br.co.zupacademy.jefferson.mercadolivre.usuario.UsuarioRepository;

@Component
public class UsuarioLogado implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional =usuarioRepository.findByEmail(username);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Not found " + username));
		return usuario;
	}

}
