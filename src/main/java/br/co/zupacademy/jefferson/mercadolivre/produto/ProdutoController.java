package br.co.zupacademy.jefferson.mercadolivre.produto;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
	private UsuarioLogado usuarioLogado;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
			UsuarioLogado usuarioLogado) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.usuarioLogado = usuarioLogado;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoRequest> cadastraProduto(
			@Valid @RequestBody ProdutoRequest request){
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(request.getIdCategoria());
		Usuario usuario = usuarioLogado.buscaUsuario();

		Produto produto = request.toModel(categoriaOptional.get(), usuario);
		produtoRepository.save(produto);
		return ResponseEntity.ok(request);
	}
}
