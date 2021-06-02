package br.co.zupacademy.jefferson.mercadolivre.produto;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ProdutoControllerTestImpl {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Value("${security.oauth2.client.client-id}")
	private String client_id;
	
	@Value("${security.oauth2.client.client-secret}")
	private String client_secret;
	private ProdutoRequest produtoRequest;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() throws Exception{
		produtoRequest = ProdutoFactory.criaProdutoRequest();
	}
	
	@Test
	public void cadastraProdutoDeveriaSalvarProdutoERetornarStatus200QuandoExistirCategoriaEUsuario() throws Exception {
		String token = obtainAccessToken("alex@gmail.com", "123456");
		String json = objectMapper.writeValueAsString(produtoRequest);
		
		mockMvc.perform(post("/produtos")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk());
	}
	
	@Test
	public void adicionaImagemDeveriaSalvarURLDaImagemNoBancoQuandoPertencerAoCriadorDoProduto() throws Exception {
		String token = obtainAccessToken("alex@gmail.com", "123456");
		List<String> diversasImagens = Arrays.asList("hello.txt", "miragem.jpg", "correcao.png");
		
	    MockMultipartFile file 
	      = new MockMultipartFile(
	        "imagens", 
	        diversasImagens.toString(), 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "Hello, World!".getBytes()
	      );
		mockMvc.perform(multipart("/produtos/{id}/imagens", 1L).file(file)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void adicionaImagemDeveriaRetornarForbiddenQuandoProdutoNaoPertenceAoUsuarioLogado() throws Exception {
		String token = obtainAccessToken("tanjiro@gmail.com", "123456");
	    MockMultipartFile file 
	      = new MockMultipartFile(
	        "imagens", 
	        "hello.txt", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "Hello, World!".getBytes()
	      );
		mockMvc.perform(multipart("/produtos/{id}/imagens", 1L).file(file)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
				.andExpect(status().isForbidden());
	}
	
	
	private String obtainAccessToken(String username, String password) throws Exception {
		 
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("client_id", client_id);
	    params.add("username", username);
	    params.add("password", password);

	    ResultActions result 
	      = mockMvc.perform(post("/oauth/token")
	        .params(params)
	        .with(httpBasic(client_id, client_secret ))
	        .accept("application/json;charset=UTF-8"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json;charset=UTF-8"));

	    String resultString = result.andReturn().getResponse().getContentAsString();

	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    return jsonParser.parseMap(resultString).get("access_token").toString();
	}
}
