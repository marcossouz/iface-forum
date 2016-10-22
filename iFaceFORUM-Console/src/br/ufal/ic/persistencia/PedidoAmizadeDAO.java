package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.exceptions.PedidoAmizadeException;
import br.ufal.ic.modelo.PedidoAmizade;

public class PedidoAmizadeDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void criarPedido(PedidoAmizade pedido) throws PedidoAmizadeException{
		String sql = "INSERT INTO PEDIDO_AMIZADE (id_usuario_remetente, id_usuario_destinatario, status_pedido) VALUES (?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, pedido.getIdUsuarioRemetente()); 
			preparador.setInt(2, pedido.getIdUsuarioDestinatario());
			preparador.setString(3, pedido.getStatus_pedido());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\nConvite de amizade enviado com sucesso!");
			
		} catch (SQLException e) {
			
			throw new PedidoAmizadeException("Erro ao criar pedido de amizade. Tente novamente.");
		}
	}
	
	public void atualizarPedido(PedidoAmizade pedido){
		
		String sql = "UPDATE PEDIDO_AMIZADE SET status_pedido=? WHERE idpedido_amizade=?";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pedido.getStatus_pedido());
			preparador.setInt(2, pedido.getIdPedido());
			
			preparador.execute();
			preparador.close();
			
			//System.out.println("Pedido amizade atualizado com sucesso!");
			
		}catch (SQLException e){
			System.err.println("Erro ao atualizar pedido para aceito ou rejeitado!");
		}
		
	}
	
	
	public List<PedidoAmizade> buscarPedidosPorStatus(String status_pedido, Integer id_destinatario){
		
		String sql = "SELECT * FROM PEDIDO_AMIZADE WHERE status_pedido=? AND id_usuario_destinatario=?";
		
		List<PedidoAmizade> lista = new ArrayList<PedidoAmizade>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, status_pedido);
			preparador.setInt(2, id_destinatario);
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				PedidoAmizade p = new PedidoAmizade();
				
				p.setIdPedido(resultado.getInt("idpedido_amizade"));
				p.setIdUsuarioRemetente(resultado.getInt("id_usuario_remetente"));
				p.setIdUsuarioDestinatario(resultado.getInt("id_usuario_destinatario"));
				
				lista.add(p);
			}
			
			preparador.close();
			
			//System.out.println("Lista pedidos de amizade pendentes criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar pedidos pendentes! Tente novamente.");
		}
		
		return lista;
	}
	
	
	

}
