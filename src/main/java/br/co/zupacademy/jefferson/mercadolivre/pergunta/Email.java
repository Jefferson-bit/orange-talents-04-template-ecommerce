package br.co.zupacademy.jefferson.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Email {
	
	@Autowired
	private Mail mail;
		
	public void novaPergunta(Pergunta pergunta) {
		mail.send(pergunta.getDonoDoProduto().getEmail(), "Alex Brown",
				pergunta.getUsuario().getEmail(), "Alguma assunto aqui", pergunta.getTitulo());
	}


}
