package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.exceptions.EnviarMensagemException;
import br.ufal.ic.modelo.MensagemAmigo;
import br.ufal.ic.modelo.Usuario;

public class MensagemAmigoDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void enviarMensagem(Usuario remetente, Usuario destinatario, MensagemAmigo mensagem) throws EnviarMensagemException{
		
		String sql = "INSERT INTO MENSAGEM_AMIGO(titulo, conteudo, id_usuario_remetente, id_usuario_destinatario, data_hora) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, mensagem.getTitulo()); 
			preparador.setString(2, mensagem.getConteudo());
			preparador.setInt(3, remetente.getIdUsuario());
			preparador.setInt(4, destinatario.getIdUsuario());
			preparador.setString(5, mensagem.getData_hora());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("\nMensagem enviada com sucesso!");
			
		} catch (SQLException e) {
			
			throw new EnviarMensagemException("Erro ao enviar mensagem. Tente novamente!");
		}
	}
	
	public List<MensagemAmigo> buscarMensagensRecebidas(Usuario usuario){
		
		String sql = "SELECT * FROM MENSAGEM_AMIGO WHERE id_usuario_destinatario=? ORDER BY idmensagem DESC";
		
		List<MensagemAmigo> lista = new ArrayList<MensagemAmigo>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,usuario.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				MensagemAmigo m = new MensagemAmigo();
				
				m.setIdMensagem(resultado.getInt("idmensagem"));
				m.setTitulo(resultado.getString("titulo"));
				m.setConteudo(resultado.getString("conteudo"));
				m.setIdUsuarioRemetente(resultado.getInt("id_usuario_remetente"));
				m.setData_hora(resultado.getString("data_hora"));
				
				lista.add(m);
			}
			
			preparador.close();
			
			//System.out.println("Caixa de mensagens recebidas criada com sucesso!");
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar mensagens! Tente novamente.");
		}
		
		return lista;
		
	}
	
public List<MensagemAmigo> buscarMensagensEnviadas(Usuario usuario){
		
		String sql = "SELECT * FROM MENSAGEM_AMIGO WHERE id_usuario_remetente=? ORDER BY idmensagem DESC";
		
		List<MensagemAmigo> lista = new ArrayList<MensagemAmigo>();
		
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1,usuario.getIdUsuario());
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()){
				
				MensagemAmigo m = new MensagemAmigo();
				
				m.setIdMensagem(resultado.getInt("idmensagem"));
				m.setTitulo(resultado.getString("titulo"));
				m.setConteudo(resultado.getString("conteudo"));
				m.setIdUsuarioDestinatario(resultado.getInt("id_usuario_destinatario"));
				m.setData_hora(resultado.getString("data_hora"));
				
				lista.add(m);
			}
			
			preparador.close();
			
			
		} catch(SQLException e){
			System.err.println("Erro ao buscar mensagens! Tente novamente.");
		}
		
		return lista;
		
	}
	
}
