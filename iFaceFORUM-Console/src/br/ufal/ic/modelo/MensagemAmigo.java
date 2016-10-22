package br.ufal.ic.modelo;


public class MensagemAmigo {
	
	private int idMensagem;
	private String titulo;
	private String conteudo;
	private int idUsuarioRemetente;
	private int idUsuarioDestinatario;
	private String data_hora;
	
	public int getIdMensagem() {
		return idMensagem;
	}
	
	public void setIdMensagem(int idMensagem) {
		this.idMensagem = idMensagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public int getIdUsuarioRemetente() {
		return idUsuarioRemetente;
	}
	public void setIdUsuarioRemetente(int idUsuarioRemetente) {
		this.idUsuarioRemetente = idUsuarioRemetente;
	}
	public int getIdUsuarioDestinatario() {
		return idUsuarioDestinatario;
	}
	public void setIdUsuarioDestinatario(int idUsuarioDestinatario) {
		this.idUsuarioDestinatario = idUsuarioDestinatario;
	}

	public String getData_hora() {
		return data_hora;
	}

	public void setData_hora(String data_hora) {
		this.data_hora = data_hora;
	}

}
