package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.exceptions.CriacaoUsuarioException;
import br.ufal.ic.modelo.Usuario;

public class UsuarioDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void cadastrar(Usuario usuario) throws CriacaoUsuarioException{
		String sql = "INSERT INTO USUARIO (nome, sobrenome, email, idade, sexo, nome_usuario, senha) VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome()); 
			preparador.setString(2, usuario.getSobrenome());
			preparador.setString(3, usuario.getEmail());
			preparador.setInt(4, usuario.getIdade());
			preparador.setString(5, usuario.getSexo());
			preparador.setString(6, usuario.getNome_usuario()); 
			preparador.setString(7, usuario.getSenha());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\nUsuário cadastrado com sucesso!");
			
		} catch (SQLException e) {
			
			throw new CriacaoUsuarioException("Erro ao cadastrar usuário. Tente novamente.");
			
		}
			
	}
	
	
	public void atualizar(Usuario usuario){
		String sql = "UPDATE USUARIO SET nome=?, sobrenome=?, email=?, idade=?, sexo=?, nome_usuario=?, senha=? WHERE idUsuario=?";
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getSobrenome());
			preparador.setString(3, usuario.getEmail());
			preparador.setInt(4, usuario.getIdade());
			preparador.setString(5, usuario.getSexo());
			preparador.setString(6, usuario.getNome_usuario());
			preparador.setString(7, usuario.getSenha());
			preparador.setInt(8, usuario.getIdUsuario());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\nDados do usuário atualizados com sucesso!");
			
		}catch (SQLException e){
			System.err.println("Erro ao atualizar os dados do usuário. Tente novamente.");
		}
		
	}
	
	
	public void excluir(Usuario usuario){
		
		String sql = "DELETE FROM USUARIO WHERE idUsuario=?";
		
		try {
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, usuario.getIdUsuario());
		
		preparador.execute();
		preparador.close();
		
		System.out.println("\nConta removida com sucesso!");
		
		}catch (SQLException e){
			System.err.println("Erro ao excluir a conta do usuário. Tente novamente.");
		}
		
	}
	
	
	public List<Usuario> buscarTodos(){
		
		String sql = "SELECT * FROM USUARIO";
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(resultado.getInt("idUsuario"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setSobrenome(resultado.getString("sobrenome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setIdade(resultado.getInt("idade"));
				usuario.setSexo(resultado.getString("sexo"));
				usuario.setNome_usuario(resultado.getString("nome_usuario"));
				usuario.setSenha(resultado.getString("senha"));
				
				lista.add(usuario);
			}
			
			preparador.close();
			
			//System.out.println("Lista criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao criar lista de usuários. Tente novamente.");
		}
		
		return lista;
	}
	
	
	public Usuario buscarPorId(Integer id){
		String sql = "SELECT * FROM usuario WHERE IdUsuario=?";
		Usuario usuario = null;
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,id);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){ // se houver um usuario...
				usuario = new Usuario();
				usuario.setIdUsuario(resultado.getInt("idUsuario"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setSobrenome(resultado.getString("sobrenome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setIdade(resultado.getInt("idade"));
				usuario.setSexo(resultado.getString("sexo"));
				usuario.setNome_usuario(resultado.getString("nome_usuario"));
				usuario.setSenha(resultado.getString("senha"));
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao acessar o usuário. Tente novamente.");
		}
		
		return usuario;
		
	}
	
	public Usuario buscarPorNomeUsuario(String nome_usuario){
		String sql = "SELECT * FROM usuario WHERE nome_usuario=?";
		Usuario usuario = null;
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome_usuario);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){ // se houver um usuario...
				usuario = new Usuario();
				usuario.setIdUsuario(resultado.getInt("idUsuario"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setSobrenome(resultado.getString("sobrenome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setIdade(resultado.getInt("idade"));
				usuario.setSexo(resultado.getString("sexo"));
				usuario.setNome_usuario(resultado.getString("nome_usuario"));
				usuario.setSenha(resultado.getString("senha"));
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao buscar usuário.");
		}
		
		return usuario;
		
	}
}