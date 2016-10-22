package br.ufal.ic.modelo;

public class MensagemComunidade {
	
	private int idMensagem;
	private String titulo;
	private String conteudo;
	private int id_comunidade;
	private int id_usuario_remetente;
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
	public int getId_comunidade() {
		return id_comunidade;
	}
	public void setId_comunidade(int id_comunidade) {
		this.id_comunidade = id_comunidade;
	}
	public int getId_usuario_remetente() {
		return id_usuario_remetente;
	}
	public void setId_usuario_remetente(int id_usuario_remetente) {
		this.id_usuario_remetente = id_usuario_remetente;
	}
	public String getData_hora() {
		return data_hora;
	}
	public void setData_hora(String data_hora) {
		this.data_hora = data_hora;
	}
	
	
}
