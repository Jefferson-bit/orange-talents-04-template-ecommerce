package br.co.zupacademy.jefferson.mercadolivre.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;
import br.co.zupacademy.jefferson.mercadolivre.usuario.UsuarioRepository;

@Component
public class UsuarioLogado implements UserDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioLogado.class);

	@Autowired
	private  UsuarioRepository usuarioRepository;

	public  Usuario getUsuarioLogado() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Usuario> usuario = usuarioRepository.findByEmail(name);
		return usuario.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
		LOG.info("usuario: " + username);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Not found " + username));
		return usuario;
	}

}
