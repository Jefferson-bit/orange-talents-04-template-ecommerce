package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.co.zupacademy.jefferson.mercadolivre.categoria.Categoria;
import br.co.zupacademy.jefferson.mercadolivre.categoria.CategoriaRepository;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;
import br.co.zupacademy.jefferson.mercadolivre.utils.UsuarioLogado;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
		
	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;
	@Autowired
	private Uploader uploadFake;
	
	@Autowired
	private UsuarioLogado usuarioLogado;
	
	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoRequest> cadastraProduto(
			@Valid @RequestBody ProdutoRequest request){
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(request.getIdCategoria());
		Usuario usuario = usuarioLogado.getUsuarioLogado();

		Produto produto = request.toModel(categoriaOptional.get(), usuario);
		produtoRepository.save(produto);
		return ResponseEntity.ok(request);
	}
	
	@PostMapping(value = "/{id}/imagens")
	public ResponseEntity<Void> adicionaImagens( @PathVariable Long id, @Valid ImagensRequest request){
		Set<String> links = uploadFake.envia(request.getImagens());
		Produto produto = produtoRepository.getOne(id);

		if(!produto.verificaSeEDosuarioLogado(usuarioLogado)) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		
		produto.associaImagens(links);
		produtoRepository.save(produto);
		return ResponseEntity.noContent().build();
				
	}
}
