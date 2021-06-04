
package br.co.zupacademy.jefferson.mercadolivre.produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/produtos")
public class BuscaProdutoController {

	private ProdutoRepository produtoRepository;

	public BuscaProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity <DetalhesDoProdutoResponse> buscaProdutos(@PathVariable Long id){
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok(new DetalhesDoProdutoResponse(produto));
		}
}
