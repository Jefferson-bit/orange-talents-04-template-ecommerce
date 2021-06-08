package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import javax.validation.Valid;

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
	private UsuarioLogado usuarioLogado;

	public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository,
		UsuarioLogado usuarioLogado) {
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
		this.usuarioLogado = usuarioLogado;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CompraRequest> compraProduto(@Valid @RequestBody CompraRequest request) {

		Produto produto = produtoRepository.findById(request.getIdProduto())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		produto.abateEstoque(request.getQuantidade());

		Compra compra = request.toModel(produto, usuarioLogado.getUsuarioLogado());
		compraRepository.save(compra);
		
		return ResponseEntity.status(HttpStatus.FOUND).location(compra.redirecionaUri()).build();
	}
}
