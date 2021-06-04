
package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class BuscaProdutoController {

	private ProdutoRepository produtoRepository;

	public BuscaProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@GetMapping
	public ResponseEntity <List<DetalhesDoProdutoResponse>> buscaTudo(){
		List<Produto> produtoList = produtoRepository.findAll();
		List<DetalhesDoProdutoResponse> produtoResponse = produtoList.stream()
			.map(obj -> 
			 new DetalhesDoProdutoResponse(obj, obj.getCaracteristicas(), obj.getImagens(), obj.getOpinioes(), obj.getPerguntas()))
			.collect(Collectors.toList());
		return ResponseEntity.ok(produtoResponse);
		}
}
