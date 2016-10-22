
package br.ufal.ic.modelo;

import java.util.List;

public class Comunidade {
	
	private int idComunidade;
	private String nome;
	private String descricao;
	private int idUsuarioGerente;
	
	public List<Integer> membrosComunidade;
	public List<MensagemComunidade> mensagensComunidade;
	
	//Getters e Setters
	public int getIdComunidade() {
		return idComunidade;
	}
	public void setIdComunidade(int idComunidade) {
		this.idComunidade = idComunidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getIdUsuarioGerente() {
		return idUsuarioGerente;
	}
	public void setIdUsuarioGerente(int idUsuarioGerente) {
		this.idUsuarioGerente = idUsuarioGerente;
	}
	
}
