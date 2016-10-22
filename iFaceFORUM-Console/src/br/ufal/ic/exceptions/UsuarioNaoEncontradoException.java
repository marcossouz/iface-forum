package br.ufal.ic.exceptions;

import br.ufal.ic.modelo.Usuario;

public class UsuarioNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(Usuario user) {
		super("O usu�rio "+user.getNome_usuario()+" n�o foi encontrado! Por favor, tente novamente.");
	}
}
