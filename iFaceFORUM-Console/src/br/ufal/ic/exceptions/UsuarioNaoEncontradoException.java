package br.ufal.ic.exceptions;

import br.ufal.ic.modelo.Usuario;

public class UsuarioNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(Usuario user) {
		super("O usuário "+user.getNome_usuario()+" não foi encontrado! Por favor, tente novamente.");
	}
}
