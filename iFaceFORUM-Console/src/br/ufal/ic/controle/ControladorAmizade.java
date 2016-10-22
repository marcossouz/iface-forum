package br.ufal.ic.controle;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.ufal.ic.exceptions.EnviarMensagemException;
import br.ufal.ic.exceptions.PedidoAmizadeException;
import br.ufal.ic.modelo.Comunidade;
import br.ufal.ic.modelo.MensagemAmigo;
import br.ufal.ic.modelo.MensagemComunidade;
import br.ufal.ic.modelo.PedidoAmizade;
import br.ufal.ic.modelo.Usuario;
import br.ufal.ic.persistencia.AmizadeDAO;
import br.ufal.ic.persistencia.ComunidadeDAO;
import br.ufal.ic.persistencia.ComunidadeUsuarioDAO;
import br.ufal.ic.persistencia.MensagemAmigoDAO;
import br.ufal.ic.persistencia.MensagemComunidadeDAO;
import br.ufal.ic.persistencia.PedidoAmizadeDAO;
import br.ufal.ic.persistencia.UsuarioDAO;

public class ControladorAmizade {
	
	Scanner read = new Scanner(System.in);
	
	int opc, cod;
	String nome_usuario;
	String senha;
	String nome;
	String sobrenome;
	String email;
	int idade;
	String sexo;
	
	
	ControladorPrincipal ctrlPrincipal;
	
	UsuarioDAO userDAO = new UsuarioDAO();
	Comunidade c = new Comunidade();
	ComunidadeDAO comunDAO = new ComunidadeDAO();
	ComunidadeUsuarioDAO comunidade_usuarioDAO = new ComunidadeUsuarioDAO();
	MensagemAmigoDAO msgDAO;
	MensagemComunidadeDAO msgCDAO;
	MensagemAmigo mensagem;
	MensagemComunidade mensagemC;
	AmizadeDAO amizadeDAO;
	
	public boolean verificaAmizade(Usuario u, int id_teste){
		u.amigos = new ArrayList<Integer>();
		amizadeDAO = new AmizadeDAO();
		
		u.amigos = amizadeDAO.buscaAmigos(u);
		
		return u.amigos.contains(id_teste);
		
	}
	
	
	public void telaAmigos(Usuario u){
		
		Usuario user = new Usuario();
		
		user = userDAO.buscarPorNomeUsuario(u.getNome_usuario());
		
		PedidoAmizade pedido;
		PedidoAmizadeDAO pedidoDAO;
		MensagemAmigo mensagem;
		Usuario usuario;
		String conteudo;
		String titulo = null;
		
		int controle;
		
		ctrlPrincipal = new ControladorPrincipal();
		
		System.out.println("----------------");
		System.out.println("MENU DE AMIZADES");
		System.out.println("----------------");
		
		System.out.println("\n1 - Ver amigos");
		System.out.println("2 - Adicionar novo amigo");
		System.out.println("3 - Desfazer amizade com um amigo");
		System.out.println("4 - Enviar mensagem a um amigo");
		System.out.println("5 - Enviar mensagem a um outro usuário da rede");
		System.out.println("6 - Exibir caixa de mensagens");
		System.out.println("7 - Ver solicitações de amizade");
		System.out.println("8 - Voltar\n");
		
		do{
			try{
				opc = Integer.parseInt(read.next());
				if(opc < 1 || opc > 8){
					System.err.println("Por favor, digite apenas os números indicados!");
					opc = -1;
				}
			}catch(NumberFormatException e){
				opc = -1;
				System.err.println("Por favor, digite uma opção válida!");
				
			}
		}while(opc == -1);
		
		ctrlPrincipal.limparTela();
		
		switch(opc){
			case 1:
				user.amigos = new ArrayList<Integer>();
				amizadeDAO = new AmizadeDAO();
				usuario = new Usuario();
				
				user.amigos = amizadeDAO.buscaAmigos(user);
				
				System.out.println("-----------");
				System.out.println("Meus amigos");
				System.out.println("-----------\n");
				
				if(user.amigos.size() == 0){
					System.out.println("Você ainda não possui amigos...");
				}else{
					for(Integer i: user.amigos){
						if(i != user.getIdUsuario()){
							usuario = userDAO.buscarPorId(i);
							System.out.println(usuario.getNome()+" "+usuario.getSobrenome()+" ("+usuario.getNome_usuario()+")");
						}
					}
				}
				
				ctrlPrincipal.limparTela();
				
				telaAmigos(u);
				
				break;
			case 2:
				List<Usuario> lista_usuarios = new ArrayList<Usuario>();
				lista_usuarios = userDAO.buscarTodos();
				usuario = new Usuario();
				
				System.out.println("Outros usuários cadastrados: \n");
				System.out.println("------------------------------");
				
				for(Usuario usu: lista_usuarios){
					if(usu.getIdUsuario() != user.getIdUsuario() && !verificaAmizade(user, usu.getIdUsuario())){
						System.out.println("Código: "+usu.getIdUsuario());
						System.out.println("Nome: "+usu.getNome()+" ("+usu.getNome_usuario()+")");
						System.out.println("------------------------------");
					}
				}
				
				
				System.out.printf("\nInforme o código do usuário que deseja solicitar amizade: ");
				do{
					try{
						cod = Integer.parseInt(read.next());
						usuario = userDAO.buscarPorId(cod);
						if(usuario == null || usuario.getIdUsuario() == user.getIdUsuario()){
							cod = -1;
							System.err.println("Por favor, informe corretamente um código de usuário.");
						}
					}catch(NumberFormatException e){
						cod = -1;
						System.err.println("Por favor, digite uma opção válida!");
						
					}
				}while(cod == -1);
				
				//criando pedido
				pedido = new PedidoAmizade();
				pedidoDAO = new PedidoAmizadeDAO();
				
				pedido.setIdUsuarioRemetente(user.getIdUsuario());
				pedido.setIdUsuarioDestinatario(cod);
				pedido.setStatus_pedido("pendente");
				
			try {
				pedidoDAO.criarPedido(pedido);
			} catch (PedidoAmizadeException e1) {
				// TODO Auto-generated catch block
				System.err.println(e1.getMessage());
			}
				
				ctrlPrincipal.limparTela();
				
				telaAmigos(u);
				
				break;
			case 3:
				user.amigos = new ArrayList<Integer>();
				amizadeDAO = new AmizadeDAO();
				usuario = new Usuario();
				
				user.amigos = amizadeDAO.buscaAmigos(user);
				
				System.out.println("-----------");
				System.out.println("Meus amigos");
				System.out.println("-----------\n");
				
				if(user.amigos.size() == 0){
					System.out.println("Você ainda não possui amigos...");
				}else{
					for(Integer i: user.amigos){
						if(i != user.getIdUsuario()){
							usuario = userDAO.buscarPorId(i);
							System.out.println("Código: "+usuario.getIdUsuario());
							System.out.println(usuario.getNome()+" "+usuario.getSobrenome()+" ("+usuario.getNome_usuario()+")");
						}
					}
					
				
				
					System.out.printf("Informe o código do amigo pra desfazer a amizade: ");
					
					do{
						try{
							cod = Integer.parseInt(read.next());
							usuario = userDAO.buscarPorId(cod);
							if(usuario == null){
								cod = -1;
								System.err.println("Por favor, informe corretamente um código de usuário.");
							}else{
								if(!user.amigos.contains(usuario.getIdUsuario())){
									cod = -1;
									System.err.println("Por favor, informe corretamente um código de amigo.");
								}
							}
						}catch(NumberFormatException e){
							cod = -1;
							System.err.println("Por favor, digite uma opção válida!");
							
						}
					}while(cod == -1);
					
					amizadeDAO = new AmizadeDAO();
					
					amizadeDAO.removerAmizade(user, usuario);
				}
				
				ctrlPrincipal.limparTela();
				
				telaAmigos(u);
				
				break;
			case 4: 
				user.amigos = new ArrayList<Integer>();
				amizadeDAO = new AmizadeDAO();
				usuario = new Usuario();
				controle = 0;
				
				user.amigos = amizadeDAO.buscaAmigos(user);
				
				System.out.println("Escolha um de seus amigos para enviar uma mensagem...\n");
				
				System.out.println("--------------------------------");
				for(Integer i: user.amigos){
					if(i != user.getIdUsuario()){
						usuario = userDAO.buscarPorId(i);
						System.out.println("Código: "+usuario.getIdUsuario());
						System.out.println("Nome: "+usuario.getNome()+" ("+usuario.getNome_usuario()+")");
						System.out.println("--------------------------------");
						controle = 1;
					}
				}
				
				if(controle == 0){
					System.out.println("Ops, você não possui amigos para enviar mensagens...");
				}else{
					System.out.printf("\nCódigo:");
					
					do{
						try{
							cod = Integer.parseInt(read.next());
							if(!user.amigos.contains(cod)){
								System.err.println("Por favor, entre com um código correto de amigo!");
							}
						}catch(NumberFormatException e){
							cod = -1;
							System.err.println("Por favor, digite uma opção válida!");
							
						}
					}while(cod == -1 || !user.amigos.contains(cod));
				
					usuario = userDAO.buscarPorId(cod);
					
					System.out.println("Deseja inserir título em sua mensagem? <1-Sim> <2-Não>");
					
					do{
						try{
							opc = Integer.parseInt(read.next());
							if(opc!=1 && opc!=2){
								opc = -1;
								System.err.println("Digite apenas os números indicados!");
							}
						}catch(NumberFormatException e){
							opc = -1;
							System.err.println("Digite uma opção válida!");
						}
					}while(opc == -1);
					
					read.nextLine();
					
					if (opc == 1){
						System.out.printf("Título: ");
						titulo = read.nextLine();
					}
					
					System.out.println("Escreva a sua mensagem: ");
					conteudo = read.nextLine();
					
					mensagem = new MensagemAmigo();
					msgDAO = new MensagemAmigoDAO();
					
					mensagem.setTitulo(titulo);
					mensagem.setConteudo(conteudo);
					mensagem.setIdUsuarioRemetente(user.getIdUsuario());
					mensagem.setIdUsuarioDestinatario(usuario.getIdUsuario());
					
					Calendar c = Calendar.getInstance();
					Date d = c.getTime();
					
					DateFormat data = DateFormat.getDateInstance(DateFormat.MEDIUM);
					//System.out.println("Data brasileira: "+data.format(d));
					
					DateFormat hora = DateFormat.getTimeInstance();
					//System.out.println("Hora formatada: "+hora.format(d));
					
					String data_hora = data.format(d)+", "+hora.format(d);
					
					mensagem.setData_hora(data_hora);
					
					try {
						msgDAO.enviarMensagem(user, usuario, mensagem);
					} catch (EnviarMensagemException e) {
						System.err.println(e.getMessage());
					}
				}
				
				ctrlPrincipal.limparTela();
				
				telaAmigos(u);
				
				break;
			case 5:
				List<Usuario> total = new ArrayList<Usuario>();
				usuario = new Usuario();
				total = userDAO.buscarTodos();
				controle = 0;
				
				user.amigos = new ArrayList<Integer>();
				amizadeDAO = new AmizadeDAO();
				
				user.amigos = amizadeDAO.buscaAmigos(user);
				
				System.out.println("Escolha um outro usuário da rede para enviar uma mensagem...\n");
				
				for(Usuario usu: total){
					if(usu.getIdUsuario() != user.getIdUsuario() && !verificaAmizade(user, usu.getIdUsuario())){
						System.out.println("Código: "+usu.getIdUsuario());
						System.out.println("Nome: "+usu.getNome()+" ("+usu.getNome_usuario()+")");
						System.out.println("--------------------------------");
						controle = 1;
					}
				}
				
				if(controle == 0){
					System.out.println("Ops, não existem outros usuários na rede...");
				}else{
					System.out.printf("\nCódigo: ");
					
					do{
						try{
							cod = Integer.parseInt(read.next());
							
							if(user.amigos.contains(cod)){
								System.err.println("Por favor, entre com um código de usuário correto!");
							}
							
						}catch(NumberFormatException e){
							cod = -1;
							System.err.println("Por favor, digite uma opção válida!");
							
						}
					}while(cod == -1 || user.amigos.contains(cod));
					
					usuario = userDAO.buscarPorId(cod);
					
					System.out.println("Deseja inserir título em sua mensagem? <1-Sim> <2-Não>");
					
					do{
						try{
							opc = Integer.parseInt(read.next());
							if(opc!=1 && opc!=2){
								opc = -1;
								System.err.println("Digite apenas os números indicados!");
							}
						}catch(NumberFormatException e){
							opc = -1;
							System.err.println("Digite uma opção válida!");
						}
					}while(opc == -1);
					
					read.nextLine();
					
					if (opc == 1){
						System.out.printf("Título: ");
						titulo = read.nextLine();
					}
					
					System.out.println("Escreva a sua mensagem: ");
					conteudo = read.nextLine();
					
					mensagem = new MensagemAmigo();
					msgDAO = new MensagemAmigoDAO();
					
					mensagem.setTitulo(titulo);
					mensagem.setConteudo(conteudo);
					mensagem.setIdUsuarioRemetente(user.getIdUsuario());
					mensagem.setIdUsuarioDestinatario(usuario.getIdUsuario());
					
					Calendar c = Calendar.getInstance();
					Date d = c.getTime();
					
					DateFormat data = DateFormat.getDateInstance(DateFormat.MEDIUM);
					//System.out.println("Data brasileira: "+data.format(d));
					
					DateFormat hora = DateFormat.getTimeInstance();
					//System.out.println("Hora formatada: "+hora.format(d));
					
					String data_hora = data.format(d)+", "+hora.format(d);
					
					mensagem.setData_hora(data_hora);
					
					try {
						msgDAO.enviarMensagem(user, usuario, mensagem);
					} catch (EnviarMensagemException e) {
						System.err.println(e.getMessage());
					}
				}
				
				ctrlPrincipal.limparTela();
				
				telaAmigos(u);
				
				break;
				
			case 6:
				msgDAO = new MensagemAmigoDAO();
				int contador1 = 1;
				int contador2 = 1;
				System.out.println("------------------");
				System.out.println("Caixa de mensagens");
				System.out.println("------------------\n\n");
				
				user.mensagensRecebidas = msgDAO.buscarMensagensRecebidas(user);
				
				System.out.println("Mensagens Recebidas: \n");
					
					if(user.mensagensRecebidas.size() == 0){
						System.out.println("Você ainda não recebeu mensagens...");
					}else{
						System.out.println("--------------------------------------------------------");
						for(MensagemAmigo m: user.mensagensRecebidas){
							System.out.println("Mensagem "+contador1);
							
							if(m.getTitulo() != null){
								System.out.println("Título: "+m.getTitulo());
							}
							
							System.out.println("Conteúdo: "+m.getConteudo());
							usuario = userDAO.buscarPorId(m.getIdUsuarioRemetente());
							System.out.println("Remetente: "+usuario.getNome()+" ("+usuario.getNome_usuario()+")");
							System.out.println("Data e hora de recebimento: "+m.getData_hora());
							contador1++;
							
							System.out.println("--------------------------------------------------------");
						}
					}
					
				
				user.mensagensEnviadas = msgDAO.buscarMensagensEnviadas(user);
					
				System.out.println("\n\n\nMensagens Enviadas: \n");
				
					if(user.mensagensEnviadas.size() == 0){
						System.out.println("Você ainda não enviou mensagens...");
					}else{
						System.out.println("--------------------------------------------------------");
						for(MensagemAmigo m: user.mensagensEnviadas){
							System.out.println("Mensagem "+contador2);
							
							if(m.getTitulo() != null){
								System.out.println("Título: "+m.getTitulo());
							}
							
							System.out.println("Conteúdo: "+m.getConteudo());
							usuario = userDAO.buscarPorId(m.getIdUsuarioDestinatario());
							System.out.println("Destinatário: "+usuario.getNome()+" ("+usuario.getNome_usuario()+")");
							System.out.println("Data e hora de envio: "+m.getData_hora());
							
							contador2++;
							System.out.println("--------------------------------------------------------");
						}
					}
						
				ctrlPrincipal.limparTela();
				
				telaAmigos(u);
				
				break;
			case 7:
				pedidoDAO = new PedidoAmizadeDAO();
				amizadeDAO = new AmizadeDAO();
				
				user.pedidosAmizade = pedidoDAO.buscarPedidosPorStatus("pendente", user.getIdUsuario());
				
				Usuario remetente = new Usuario();
				
				System.out.println("Solicitações de amizade: \n");
				
				if(user.pedidosAmizade.size() == 0){
					System.out.println("Não há solicitações de amizade até o momento.");
				}else{
					for(PedidoAmizade p: user.pedidosAmizade){
						remetente = userDAO.buscarPorId(p.getIdUsuarioRemetente());
						
						System.out.println("O usuário "+remetente.getNome()+" ("+remetente.getNome_usuario()+") quer ser seu amigo.");
						System.out.println("1-Aceitar / 2-Recusar");
						
						do{
							try{
								opc = Integer.parseInt(read.next());
								if(opc!=1 && opc!=2){
									opc = -1;
									System.err.println("Digite apenas os números indicados!");
								}
							}catch(NumberFormatException e){
								opc = -1;
								System.err.println("Digite uma opção válida!");
							}
						}while(opc == -1);
						
						if(opc == 1){
							p.setStatus_pedido("aceito");
							amizadeDAO.adicionarAmizade(remetente, user);
							System.out.println("\nVocês são amigos agora!");
						}else{
							p.setStatus_pedido("recusado");
							System.out.println("\nConvite recusado!");
						}
						
						pedidoDAO.atualizarPedido(p);
					}
				}
				
				ctrlPrincipal.limparTela();
				
				telaAmigos(u);
				
				break;
				
			case 8:
				ctrlPrincipal = new ControladorPrincipal();
				
				ctrlPrincipal.limparTela();
				ctrlPrincipal.carregarTelaUsuario(u);
				break;
				
		}
	}

}
