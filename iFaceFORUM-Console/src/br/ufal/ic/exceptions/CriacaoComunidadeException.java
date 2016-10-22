package br.ufal.ic.exceptions;

import java.sql.SQLException;

public class CriacaoComunidadeException extends SQLException {

	private static final long serialVersionUID = 1L;

	public CriacaoComunidadeException(String erro) {
		super(erro);
	}
}
