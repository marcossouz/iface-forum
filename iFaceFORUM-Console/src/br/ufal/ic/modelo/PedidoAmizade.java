package br.ufal.ic.modelo;

public class PedidoAmizade {

	private int idPedido;
	private int idUsuarioRemetente;
	private int idUsuarioDestinatario;
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

	public int getIdUsuarioDestinatario() {
		return idUsuarioDestinatario;
	}

	public void setIdUsuarioDestinatario(int idUsuarioDestinatario) {
		this.idUsuarioDestinatario = idUsuarioDestinatario;
	}

	public String getStatus_pedido() {
		return status_pedido;
	}

	public void setStatus_pedido(String status_pedido) {
		this.status_pedido = status_pedido;
	}
	
}
