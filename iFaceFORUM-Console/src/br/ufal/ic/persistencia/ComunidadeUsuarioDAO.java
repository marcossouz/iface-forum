package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.modelo.Comunidade;
import br.ufal.ic.modelo.Usuario;

public class ComunidadeUsuarioDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void adicionarMembro(Comunidade comunidade, Usuario usuario){
		String sql = "INSERT INTO COMUNIDADE_USUARIO(id_comunidade, id_usuario) VALUES (?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, comunidade.getIdComunidade()); 
			preparador.setInt(2, usuario.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\nMembro adicionado com sucesso!");
			
		} catch (SQLException e) {
			System.err.println("Erro ao adicionar membro a comunidade");
		}
	}
	
	public List<Integer> buscaMembrosPorComunidade(Integer id){
		
		String sql = "SELECT * FROM COMUNIDADE_USUARIO WHERE id_comunidade=?";
		
		List<Integer> lista = new ArrayList<Integer>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,id);
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				lista.add(resultado.getInt("id_usuario"));
			}
			
			preparador.close();
			
			//System.out.println("Lista membros de comunidades criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar membros por comunidade.");
		}
		
		return lista;
	}
	
	public List<Integer> buscarComunidadesPorUsuario(Usuario usuario){

		
		String sql = "SELECT * FROM COMUNIDADE_USUARIO WHERE id_usuario=?";
		
		List<Integer> lista = new ArrayList<Integer>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,usuario.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				lista.add(resultado.getInt("id_comunidade"));
			}
			
			preparador.close();
			
			//System.out.println("Lista comunidades por usuario criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar comunidades de usuário. Tente novamente.");
		}
		
		return lista;
	}
 
	public void removerMembro(Comunidade comunidade, Usuario usuario){
		String sql = "DELETE FROM COMUNIDADE_USUARIO WHERE id_comunidade=? AND id_usuario=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, comunidade.getIdComunidade());
			preparador.setInt(2, usuario.getIdUsuario());

			preparador.execute();
			preparador.close();
			
			System.out.println("\nMembro removido com sucesso!");
			
		} catch (SQLException e) {
			System.err.println("Erro ao remover membro de comunidade. Tente novamente.");
		}
	}
}
