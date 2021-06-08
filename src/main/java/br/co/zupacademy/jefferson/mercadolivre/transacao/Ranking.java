package br.co.zupacademy.jefferson.mercadolivre.transacao;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.co.zupacademy.jefferson.mercadolivre.fechacompra.Compra;
import br.co.zupacademy.jefferson.mercadolivre.sistemaexterno.RankingRequest;

@Service
public class Ranking implements ObserverEventoCompra{
		
	@Override
	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		RankingRequest request = new RankingRequest(compra.getId(), compra.getDonoDoProduto());
		restTemplate.postForEntity("http://localhost:8080/ranking", request, RankingRequest.class );
		
	}
	
}
