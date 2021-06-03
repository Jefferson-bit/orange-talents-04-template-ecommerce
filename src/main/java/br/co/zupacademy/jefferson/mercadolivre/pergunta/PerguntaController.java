package br.co.zupacademy.jefferson.mercadolivre.pergunta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class PerguntaController {
	
	private ProdutoRepository produtoRepository;
	private PerguntaRepository perguntaRepository;
	private UsuarioLogado usuarioLogado;
	@Autowired
	private Email email;
	
	public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository,
			UsuarioLogado usuarioLogado) {
		this.produtoRepository = produtoRepository;
		this.perguntaRepository = perguntaRepository;
		this.usuarioLogado = usuarioLogado;
	}

	@PostMapping(value = "/produtos/{id}/perguntas")
	public ResponseEntity<PerguntaRequest> adicionaPergunta(@PathVariable Long id ,@Valid @RequestBody PerguntaRequest request){
		Produto produto = produtoRepository.findById(id)
			.orElseThrow(() -> new RecursoNaoEncontradoExcecao("Produto not found: " + id));
		Usuario usuario = usuarioLogado.getUsuarioLogado();
		Pergunta pergunta = request.toModel(usuario, produto);
		perguntaRepository.save(pergunta);
		email.novaPergunta(pergunta);
		return ResponseEntity.ok(request);
	}
	
}