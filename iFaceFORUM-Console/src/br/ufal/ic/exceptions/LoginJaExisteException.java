package br.ufal.ic.exceptions;


public class LoginJaExisteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public LoginJaExisteException(String nome_usuario){
		super("O nome de usu�rio "+nome_usuario+" j� existe na rede. Por favor, insira outro nome.");
	}
}
