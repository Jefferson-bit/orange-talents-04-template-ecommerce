package br.co.zupacademy.jefferson.mercadolivre.fechacompra;

import org.springframework.context.ApplicationEvent;

public class EmailRequest extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	
	
	private String fromEmail;
	private String fromName;
	private String to;
	private String subject;
	private String body;
	private String contentType;
	
	public EmailRequest(Object source, String fromEmail, String fromName, String to, String subject, String body,
			String contentType) {
		super(source);
		this.fromEmail = fromEmail;
		this.fromName = fromName;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.contentType = contentType;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getContentType() {
		return contentType;
	}
	
	
}
