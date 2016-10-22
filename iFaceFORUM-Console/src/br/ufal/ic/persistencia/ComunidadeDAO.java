package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.exceptions.CriacaoComunidadeException;
import br.ufal.ic.modelo.Comunidade;

public class ComunidadeDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void criarComunidade(Comunidade comunidade) throws CriacaoComunidadeException{
		//Comando SQL p/ cadastrar
		String sql = "INSERT INTO COMUNIDADE (nome,descricao,idUsuarioGerente) VALUES (?,?,?)";
		
		//Preparador que faz a inserção dos dados na tabela
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, comunidade.getNome()); //substituindo ? por nome
			preparador.setString(2, comunidade.getDescricao()); //substituindo ? por descricao
			preparador.setInt(3, comunidade.getIdUsuarioGerente()); //substituindo ? por senha
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\nComunidade criada com sucesso!");
			
		} catch (SQLException e) {
			
			throw new CriacaoComunidadeException("Problema na criação da comunidade. Tente novamente.");
		}
			
	}
	
	public List<Comunidade> buscarComunidades(){
		
		String sql = "SELECT * FROM COMUNIDADE";
		
		List<Comunidade> lista = new ArrayList<Comunidade>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				Comunidade c = new Comunidade();
				
				c.setIdComunidade(resultado.getInt("idComunidade"));
				c.setNome(resultado.getString("nome"));
				c.setDescricao(resultado.getString("descricao"));
				c.setIdUsuarioGerente(resultado.getInt("idUsuarioGerente"));
				
				//Adicionando os registros na lista criada
				lista.add(c);
			}
			
			preparador.close();
			
			//System.out.println("Lista comunidades 1 criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao criar lista de comunidades!");
		}
		
		return lista;
	}
	
	public List<Comunidade> buscarComunidadesPorGerente(Integer id){
		
		String sql = "SELECT * FROM COMUNIDADE WHERE idUsuarioGerente=?";
		
		List<Comunidade> lista = new ArrayList<Comunidade>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,id);
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				Comunidade c = new Comunidade();
				
				c.setIdComunidade(resultado.getInt("idComunidade"));
				c.setNome(resultado.getString("nome"));
				c.setDescricao(resultado.getString("descricao"));
				c.setIdUsuarioGerente(resultado.getInt("idUsuarioGerente"));
				
				//Adicionando os registros na lista criada
				lista.add(c);
			}
			
			preparador.close();
			
			//System.out.println("Lista comunidades 2 criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar comunidades do gerente! Tente novamente.");
		}
		
		return lista;
	}
	
public List<Comunidade> buscarComunidadesNaoGerenciadas(Integer id){
		
		String sql = "SELECT * FROM COMUNIDADE WHERE idUsuarioGerente<>?";
		
		List<Comunidade> lista = new ArrayList<Comunidade>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,id);
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				Comunidade c = new Comunidade();
				
				c.setIdComunidade(resultado.getInt("idComunidade"));
				c.setNome(resultado.getString("nome"));
				c.setDescricao(resultado.getString("descricao"));
				c.setIdUsuarioGerente(resultado.getInt("idUsuarioGerente"));
				
				lista.add(c);
			}
			
			preparador.close();
			
			//System.out.println("Lista comunidades 3 criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar comunidades. Tente novamente.");
		}
		
		return lista;
	}

	public Comunidade buscarPorId(Integer id){
		String sql = "SELECT * FROM comunidade WHERE idComunidade=?";
		Comunidade c = null;
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,id);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){ 
				c = new Comunidade();
				c.setIdComunidade(resultado.getInt("idComunidade"));
				c.setNome(resultado.getString("nome"));
				c.setDescricao(resultado.getString("descricao"));
				c.setIdUsuarioGerente(resultado.getInt("idUsuarioGerente"));
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao buscar comunidade. Tente novamente.");
		}
	
		return c;
		
	}


}
