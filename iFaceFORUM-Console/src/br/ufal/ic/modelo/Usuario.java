package br.ufal.ic.modelo;

import java.util.List;

public class Usuario {
	
	private int idUsuario;
	private String nome;
	private String sobrenome;
	private String nome_usuario;
	private String senha;
	private String email;
	private int idade;
	private String sexo;
	
	public List<PedidoComunidade> pedidosComunidade;
	public List<PedidoAmizade> pedidosAmizade;
	public List<MensagemAmigo> mensagensRecebidas;
	public List<MensagemAmigo> mensagensEnviadas;
	public List<Integer> amigos;
	public List<Integer> comunidades;
	
	//Getters e Setters
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Integer id) {
		this.idUsuario = id;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome_usuario() {
		return nome_usuario;
	}
	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}
