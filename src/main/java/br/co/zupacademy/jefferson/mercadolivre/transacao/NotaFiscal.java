package br.co.zupacademy.jefferson.mercadolivre.transacao;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;

@Service
public class NotaFiscal implements ObserverEventoCompra{
	
	@Override
	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idComprador", compra.getComprador());		

		restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
				request, String.class);
	}
}
