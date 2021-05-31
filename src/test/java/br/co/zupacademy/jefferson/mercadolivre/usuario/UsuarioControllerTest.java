package br.co.zupacademy.jefferson.mercadolivre.usuario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class UsuarioControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper  objectMapper;
	private UsuarioRequest usuarioRequest;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
	void setUp() throws Exception{
		usuarioRequest = new UsuarioRequest("alex@gmail.com", "123456");
	}
	
	@Test
	public void cadastraUsuarioDeveriaCriarUmUsuarioComSucesso() throws Exception {
		String json = objectMapper.writeValueAsString(usuarioRequest);
		mockMvc.perform(post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNoContent());
		Optional<Usuario> optionalEmail = usuarioRepository.findByEmail(usuarioRequest.getEmail());
		Assertions.assertTrue(optionalEmail.isPresent());
	}
}
