package br.co.zupacademy.jefferson.mercadolivre.categoria;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaRequest> cadastraCategoria(@Valid @RequestBody CategoriaRequest request ){
		Categoria categoria = request.toModel();
		categoriaRepository.save(categoria);
		if(request.getIdCategoriaMae() != null ) {
			Optional<Categoria> categoriaMae = categoriaRepository.findById(request.getIdCategoriaMae());
			categoria.setIdCategoriaMae(categoriaMae.get());
		}
		return ResponseEntity.ok(request);
	}
	
}
