package br.ufal.ic.modelo;

public class PedidoComunidade {
	
	private int idPedido;
	private int idUsuarioRemetente;
	private int idUsuarioGerente;
	private int idComunidadePedido;
	private String status_pedido;
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
	public int getIdUsuarioRemetente() {
		return idUsuarioRemetente;
	}
	
	public void setIdUsuarioRemetente(int idUsuarioRemetente) {
		this.idUsuarioRemetente = idUsuarioRemetente;
	}

	public int getIdUsuarioGerente() {
		return idUsuarioGerente;
	}

	public void setIdUsuarioGerente(int idUsuarioGerente) {
		this.idUsuarioGerente = idUsuarioGerente;
	}

	public String getStatus_pedido() {
		return status_pedido;
	}

	public void setStatus_pedido(String status_pedido) {
		this.status_pedido = status_pedido;
	}

	public int getIdComunidadePedido() {
		return idComunidadePedido;
	}

	public void setIdComunidadePedido(int idComunidadePedido) {
		this.idComunidadePedido = idComunidadePedido;
	}
	
}
