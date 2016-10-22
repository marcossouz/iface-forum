package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.exceptions.PedidoComunidadeException;
import br.ufal.ic.modelo.PedidoComunidade;

public class PedidoComunidadeDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void criarPedido(PedidoComunidade pedido) throws PedidoComunidadeException{
		
		String sql = "INSERT INTO PEDIDO_COMUNIDADE (id_usuario_remetente, id_usuario_gerente, id_comunidade_pedido, status_pedido) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, pedido.getIdUsuarioRemetente()); 
			preparador.setInt(2, pedido.getIdUsuarioGerente());
			preparador.setInt(3, pedido.getIdComunidadePedido());
			preparador.setString(4, pedido.getStatus_pedido());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\nPedido de participação enviado com sucesso!");
			
		} catch (SQLException e) {
			
			throw new PedidoComunidadeException("Erro ao enviar solicitação de participação na comunidade! Tente novamente.");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Não foi possível fechar a conexão.");
			}
		}
	}
	
	public void atualizarPedido(PedidoComunidade pedido){
		
		String sql = "UPDATE PEDIDO_COMUNIDADE SET status_pedido=? WHERE idpedido_comunidade=?";
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pedido.getStatus_pedido());
			preparador.setInt(2, pedido.getIdPedido());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Pedido atualizado com sucesso!");
			
		}catch (SQLException e){
			System.err.println("Erro ao atualizar pedido de comunidade. Tente novamente.");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Não foi possível fechar a conexão.");
			}
		}
		
	}
	
	public List<PedidoComunidade> buscarPedidosPorGerente(Integer id_gerente, String status_pedido){
		
		String sql = "SELECT * FROM PEDIDO_COMUNIDADE WHERE id_usuario_gerente=? AND status_pedido=?";
		
		List<PedidoComunidade> lista = new ArrayList<PedidoComunidade>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,id_gerente);
			preparador.setString(2, status_pedido);
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				PedidoComunidade p = new PedidoComunidade();
				
				p.setIdPedido(resultado.getInt("idpedido_comunidade"));
				p.setIdUsuarioRemetente(resultado.getInt("id_usuario_remetente"));
				p.setIdUsuarioGerente(resultado.getInt("id_usuario_gerente"));
				p.setIdComunidadePedido(resultado.getInt("id_comunidade_pedido"));
				
				lista.add(p);
			}
			
			preparador.close();
			
			//System.out.println("Lista pedidos por status criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar pedidos de solitação por gerente. Tente novamente");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Não foi possível fechar a conexão.");
			}
		}
		
		return lista;
	}
	
	
}
