package br.co.zupacademy.jefferson.mercadolivre.opiniao;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.co.zupacademy.jefferson.mercadolivre.excecao.RecursoNaoEncontradoExcecao;
import br.co.zupacademy.jefferson.mercadolivre.produto.Produto;
import br.co.zupacademy.jefferson.mercadolivre.produto.ProdutoRepository;
import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;
import br.co.zupacademy.jefferson.mercadolivre.utils.UsuarioLogado;

@RestController
public class OpiniaoController {
	
	private OpiniaoRepository opiniaoRepository;
	private ProdutoRepository produtoRepository;
	private UsuarioLogado usuarioLogado;
	
	public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository,
			UsuarioLogado usuarioLogado) {
		this.opiniaoRepository = opiniaoRepository;
		this.produtoRepository = produtoRepository;
		this.usuarioLogado = usuarioLogado;
	}

	@PostMapping(value = "/produtos/{id}/opinioes")
	@Transactional
	public ResponseEntity<OpiniaoRequest> adicionaOpiniao(@PathVariable Long id, @Valid @RequestBody OpiniaoRequest request){
		Usuario usuario = usuarioLogado.getUsuarioLogado();
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		Produto	produto =  produtoOptional.orElseThrow(() -> new RecursoNaoEncontradoExcecao("Produto not found: " + id));
		
		Opiniao opiniao = request.toModel(usuario, produto);
		opiniaoRepository.save(opiniao);
		return ResponseEntity.ok(request);
	}
	
}
