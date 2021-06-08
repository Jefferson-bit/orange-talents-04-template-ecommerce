package br.co.zupacademy.jefferson.mercadolivre.sistemaexterno;

public class EmailRequest {

	private String fromEmail;
	private String fromName;
	private String to;
	private String subject;
	private String body;
	private String contentType;

	public EmailRequest(String fromEmail, String fromName, String to, String subject, String body,
			String contentType) {
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

	@Override
	public String toString() {
		return "EmailRequestTest [fromEmail=" + fromEmail + ", fromName=" + fromName + ", to=" + to + ", subject="
				+ subject + ", body=" + body + ", contentType=" + contentType + "]";
	}

}
