package br.co.zupacademy.jefferson.mercadolivre.opiniao;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class OpiniaoControllerTestImpl {
	
	@Autowired
	private MockMvc mockMvc;
	private OpiniaoRequest request;
	
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
		request = new OpiniaoRequest(4, "Não sei fazer testes", "Preciso melhorar muito ainda");
	}
	
	@Test
	@WithMockUser
	public void adicionaOpiniaoDeveriaSalvarUmaOpiniaoEmUmProdutoQuandoIdExistir() throws Exception {
		String json = objectMapper.writeValueAsString(request);
	
		mockMvc.perform(post("/produtos/{id}/opinioes", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	public void adicionaOpiniaoDeveriaRetornarNotFoundQuandoIdNaoExistir() throws Exception {
		String json = objectMapper.writeValueAsString(request);
	
		mockMvc.perform(post("/produtos/{id}/opinioes", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());
	}
}
