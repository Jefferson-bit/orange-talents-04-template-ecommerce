package br.co.zupacademy.jefferson.mercadolivre.usuario;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	private UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public ResponseEntity<Void> cadastraUsuario(@Valid @RequestBody UsuarioRequest request) {
		Usuario usuario = request.toModel();
		usuarioRepository.save(usuario);
		return ResponseEntity.noContent().build();
	}
}
