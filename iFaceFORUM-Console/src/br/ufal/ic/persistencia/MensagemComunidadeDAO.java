package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.exceptions.EnviarMensagemComunidadeException;
import br.ufal.ic.modelo.Comunidade;
import br.ufal.ic.modelo.MensagemComunidade;
import br.ufal.ic.modelo.Usuario;

	public class MensagemComunidadeDAO {
		
		private Connection con = Conexao.getConnection();
	
		public void enviarMensagemComunidade(Usuario remetente, Comunidade c, MensagemComunidade mensagem) throws EnviarMensagemComunidadeException{
		
			String sql = "INSERT INTO MENSAGEM_COMUNIDADE(titulo, conteudo, id_usuario_remetente, id_comunidade, data_hora) VALUES (?,?,?,?,?)";
			
			try {
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, mensagem.getTitulo()); 
				preparador.setString(2, mensagem.getConteudo());
				preparador.setInt(3, remetente.getIdUsuario());
				preparador.setInt(4, c.getIdComunidade());
				preparador.setString(5, mensagem.getData_hora());
				
				preparador.execute();
				preparador.close();
				
				System.out.println("\nMensagem enviada com sucesso!");
				
			} catch (SQLException e) {
				
				throw new EnviarMensagemComunidadeException("Erro ao enviar mensagem a comunidade. Tente novamente.");
			}
		}
		
		
		public List<MensagemComunidade> buscarMensagensPorComunidade(Comunidade comunidade){
			
			String sql = "SELECT * FROM MENSAGEM_COMUNIDADE WHERE id_comunidade=? ORDER BY idmensagem_comunidade DESC";
			
			List<MensagemComunidade> lista = new ArrayList<MensagemComunidade>();
			
			try{
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1,comunidade.getIdComunidade());
				
				ResultSet resultado = preparador.executeQuery();
				
				while (resultado.next()){
					
					MensagemComunidade m = new MensagemComunidade();
					
					m.setIdMensagem(resultado.getInt("idmensagem_comunidade"));
					m.setTitulo(resultado.getString("titulo"));
					m.setConteudo(resultado.getString("conteudo"));
					m.setId_usuario_remetente(resultado.getInt("id_usuario_remetente"));
					m.setData_hora(resultado.getString("data_hora"));
					
					lista.add(m);
				}
				
				preparador.close();
				
				//System.out.println("Caixa de mensagens comunidade criada com sucesso!");
				
			} catch(SQLException e){
				System.err.println("Erro ao buscar mensagens da comunidade! Tente novamente.");
			}
			
			return lista;
			
		}
	
	
}
	
