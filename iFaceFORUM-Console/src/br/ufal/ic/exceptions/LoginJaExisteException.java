package br.ufal.ic.exceptions;


public class LoginJaExisteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public LoginJaExisteException(String nome_usuario){
		super("O nome de usuário "+nome_usuario+" já existe na rede. Por favor, insira outro nome.");
	}
}
