package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.produto.ProdutoRepository;
import br.co.zupacademy.jefferson.mercadolivre.utils.UsuarioLogado;

@RestController
@RequestMapping(value = "/compras")
public class CompraController {

	private CompraRepository compraRepository;
	private ProdutoRepository produtoRepository;
	private ApplicationEventPublisher applicationEventPublisher;
	private UsuarioLogado usuarioLogado;
		
	public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository,
			ApplicationEventPublisher applicationEventPublisher, UsuarioLogado usuarioLogado) {
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
		this.applicationEventPublisher = applicationEventPublisher;
		this.usuarioLogado = usuarioLogado;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CompraRequest> compraProduto(@Valid @RequestBody CompraRequest request) {
		Produto produto = produtoRepository.findById(request.getIdProduto())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		boolean abateEstoque = produto.abateEstoque(request.getQuantidade());

		if (abateEstoque) {
			Compra compra = request.toModel(produto, usuarioLogado.getUsuarioLogado());
			simulaEnvioDeEmail(produto);
			compraRepository.save(compra);
			if(compra.getCarteira().equals(Carteira.PAG_SEGURO)) {
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://www.baeldung.com")).build();
			}else {
				// ESTOU REDIRECIONANDO pra essas url pq no desafio a url não existe e está me retornando 404,
				//mas funciona, a compra é efetuada, salva no banco e é redirecionado pras url
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://www.alura.com.br")).build();
			}
		}

		return ResponseEntity.badRequest().build();
	}

	private void simulaEnvioDeEmail(Produto produto) {
		EmailRequest emailRequest = new EmailRequest(this, "br.com.zup@bootcamp", "zupper",
				produto.getUsuario().getEmail(), "Compra do ps5", "Obrigado por compra na nossa api do mercado livre",
				"text/html");
		applicationEventPublisher.publishEvent(emailRequest);
	}
	
	
	
}
