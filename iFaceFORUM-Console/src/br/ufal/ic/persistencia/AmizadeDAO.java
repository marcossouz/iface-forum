package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.modelo.Usuario;

public class AmizadeDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void adicionarAmizade(Usuario amigo_remetente, Usuario amigo_destinatario){
		
		String sql = "INSERT INTO AMIZADE(id_amigo_remetente, id_amigo_destinatario) VALUES (?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, amigo_remetente.getIdUsuario()); 
			preparador.setInt(2, amigo_destinatario.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
			
		} catch (SQLException e) {
			
			System.err.println("Erro ao efetivar relacionamente de amizade! Tente novamente.");
		}
			
	}
	
	public void removerAmizade(Usuario remetente, Usuario destinatario){
		String sql = "DELETE FROM AMIZADE WHERE (id_amigo_remetente=? AND id_amigo_destinatario=?) OR (id_amigo_destinatario=? AND id_amigo_remetente=?)";
		
		try {
			System.out.println(remetente.getIdUsuario()+" - "+destinatario.getIdUsuario());
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, remetente.getIdUsuario()); 
			preparador.setInt(2, destinatario.getIdUsuario());
			preparador.setInt(3, remetente.getIdUsuario());
			preparador.setInt(4, destinatario.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\n\nAmizade desfeita com sucesso!");
			
		} catch (SQLException e) {
			
			System.err.println("Não foi possível desfazer a amizade! Tente novamente.");
		}
			
	}
	
	public List<Integer> buscaAmigos(Usuario usuario){
		String sql = "SELECT * FROM AMIZADE WHERE id_amigo_remetente=? OR id_amigo_destinatario=?";
		
		List<Integer> lista = new ArrayList<Integer>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,usuario.getIdUsuario());
			preparador.setInt(2, usuario.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				lista.add(resultado.getInt("id_amigo_remetente"));
				lista.add(resultado.getInt("id_amigo_destinatario"));
			}
			
			preparador.close();
			
			//System.out.println("Lista amigos criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Não foi possível criar a lista de amigos! Por favor tente novamente.");
		}
		
		return lista;
	}
	
	
}
