package br.co.zupacademy.jefferson.mercadolivre.transacao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;
import br.co.zupacademy.jefferson.mercadolivre.fechacompra.CompraRepository;

@RestController
public class FechaTransacaoController {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private EventoNovaCompra evento;

	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public ResponseEntity<FechaTransacaoPagSeguroRequest> fechaTransacaoPagSeguro(@PathVariable Long id, @Valid @RequestBody FechaTransacaoPagSeguroRequest request){
		finalizaTransacao(id, request);
		return ResponseEntity.ok(request);
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public ResponseEntity<FechaTransacaoPayPalRequest> fechaTransacaoPayPal(@PathVariable Long id, @Valid @RequestBody FechaTransacaoPayPalRequest request){
		finalizaTransacao(id, request);
		return ResponseEntity.ok(request);
	}
	
	private void finalizaTransacao(Long id, FechaTransacaoGateway request) {
		Compra compra = compraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		compra.tentaEfetuarUmaTransacao(request);
		compraRepository.save(compra);
		evento.processaEvento(compra);
	}
}
