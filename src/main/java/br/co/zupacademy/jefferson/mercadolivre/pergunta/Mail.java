package br.co.zupacademy.jefferson.mercadolivre.pergunta;

public interface Mail {
	
	void send(String fromEmail, String fromName, String to, String subject, String body);
}
