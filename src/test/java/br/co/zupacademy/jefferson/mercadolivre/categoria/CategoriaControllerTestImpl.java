package br.co.zupacademy.jefferson.mercadolivre.categoria;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class CategoriaControllerTestImpl {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${security.oauth2.client.client-id}")
	private String client_id;
	
	@Value("${security.oauth2.client.client-secret}")
	private String client_secret;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	private CategoriaRequest request;
	
	@Test
	@WithUserDetails(value = "alex@gmail.com")
	public void cadastraCategoriaDeveriaSalvarCategoriaSemHierarquiasQuandoIdCategoriaMaeENulo() throws Exception {
		request = new CategoriaRequest("Telefone", null);
		String json = objectMapper.writeValueAsString(request);
		
		mockMvc.perform(post("/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(request.getNome()))
				.andExpect(jsonPath("$.idCategoriaMae").isEmpty());
	}
	
	@Test
	public void cadastraCategoriaDeveriaSalvarCategoriaComHierarquiasQuandoIdCategoriaMaeNaoENulo() throws Exception {
		request = new CategoriaRequest("LG", 1L);
		String json = objectMapper.writeValueAsString(request);
	
		mockMvc.perform(post("/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(request.getNome()))
				.andExpect(jsonPath("$.idCategoriaMae").exists());
	}
}
