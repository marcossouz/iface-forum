package br.ufal.ic.controle;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.ufal.ic.exceptions.CriacaoComunidadeException;
import br.ufal.ic.exceptions.EnviarMensagemComunidadeException;
import br.ufal.ic.exceptions.PedidoComunidadeException;
import br.ufal.ic.modelo.Comunidade;
import br.ufal.ic.modelo.MensagemAmigo;
import br.ufal.ic.modelo.MensagemComunidade;
import br.ufal.ic.modelo.PedidoComunidade;
import br.ufal.ic.modelo.Usuario;
import br.ufal.ic.persistencia.ComunidadeDAO;
import br.ufal.ic.persistencia.ComunidadeUsuarioDAO;
import br.ufal.ic.persistencia.MensagemAmigoDAO;
import br.ufal.ic.persistencia.MensagemComunidadeDAO;
import br.ufal.ic.persistencia.PedidoComunidadeDAO;
import br.ufal.ic.persistencia.UsuarioDAO;

public class ControladorComunidade {
	
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
	
	Usuario user;
	UsuarioDAO userDAO = new UsuarioDAO();
	Comunidade c = new Comunidade();
	ComunidadeDAO comunDAO = new ComunidadeDAO();
	ComunidadeUsuarioDAO comunidade_usuarioDAO = new ComunidadeUsuarioDAO();
	MensagemAmigoDAO msgDAO;
	MensagemComunidadeDAO msgCDAO;
	MensagemAmigo mensagem;
	MensagemComunidade mensagemC;
	
	
	public void telaComunidade(Usuario u){
			
			ctrlPrincipal = new ControladorPrincipal();
			c = new Comunidade();
			userDAO = new UsuarioDAO();
			comunDAO = new ComunidadeDAO();
			comunidade_usuarioDAO = new ComunidadeUsuarioDAO();
			PedidoComunidade pedido;
			PedidoComunidadeDAO pedidoDAO;
			Comunidade comun = new Comunidade();
			
			user = userDAO.buscarPorNomeUsuario(u.getNome_usuario());
			Usuario user2;
			
			List<Comunidade> listaComunidades = new ArrayList<Comunidade>();
			
			System.out.println("----------------");
			System.out.println("MENU COMUNIDADES");
			System.out.println("----------------");
			
			System.out.println("\n1 - Criar comunidade");
			System.out.println("2 - Listar comunidades existentes");
			System.out.println("3 - Enviar mensagem a uma comunidade");
			System.out.println("4 - Minhas comunidades");
			System.out.println("5 - Comunidades gerenciadas");
			System.out.println("6 - Participar de comunidade");
			System.out.println("7 - Verificar solicitações de usuários");
			System.out.println("8 - Voltar");
			
			do{
				try{
					opc = Integer.parseInt(read.next());
					if(opc < 1 || opc > 8){
						System.err.println("Por favor, digite apenas os números indicados!");
						opc = -1;
					}
				}catch(NumberFormatException e){
					opc =-1;
					System.err.println("Por favor, digite uma opção válida!");
					
				}
			}while( opc == -1);
			
			ctrlPrincipal.limparTela();
			
			switch(opc){
				case 1:
					String nome;
					String descricao;
					
					System.out.printf("Nome da comunidade: ");
					read.nextLine();
					nome = read.nextLine();
					
					System.out.printf("Descrição: ");
					descricao = read.nextLine();
					
					c.setNome(nome);
					c.setDescricao(descricao);
					c.setIdUsuarioGerente(user.getIdUsuario());
					
					try {
						comunDAO.criarComunidade(c);
					} catch (CriacaoComunidadeException e1) {
						System.err.println(e1.getMessage());
					}
					
					ctrlPrincipal.limparTela();
					
					telaComunidade(user);
					break;
				case 2:
					listaComunidades = comunDAO.buscarComunidades();
					System.out.println("\n----------------------");
					System.out.println("Comunidades existentes");
					System.out.println("----------------------");
					
					if(listaComunidades.size() == 0){
						System.out.println("Ainda não há comunidades cadastradas na rede.\n");
					}else{
						System.out.println("\n---------------------------------------------");
						for(Comunidade c: listaComunidades){
							System.out.println("Código: "+c.getIdComunidade());
							System.out.println("Nome: "+c.getNome());
							System.out.println("Descrição: "+c.getDescricao());
							user2 = userDAO.buscarPorId(c.getIdUsuarioGerente());
							if(user2.getIdUsuario() != user.getIdUsuario()){
								System.out.println("Gerente: "+user2.getNome()+" "+user2.getSobrenome());
							}else{
								System.out.println("Gerente: Eu");
							}
							c.membrosComunidade = new ArrayList<Integer>();
							c.membrosComunidade = comunidade_usuarioDAO.buscaMembrosPorComunidade(c.getIdComunidade());
							System.out.println("Número de membros: "+c.membrosComunidade.size());
							System.out.printf("Membros: ");
							for(int i = 0; i < c.membrosComunidade.size(); i++){
								user2 = userDAO.buscarPorId(c.membrosComunidade.get(i));
								if(user2.getIdUsuario() == user.getIdUsuario()){
									System.out.printf("Eu - ");
								}else{
									System.out.printf(user2.getNome()+ "("+user2.getNome_usuario()+") - ");
								}
									
								
							}
							System.out.println();
							System.out.println("---------------------------------------------");
						}
					}
					
					ctrlPrincipal.limparTela();
					
					telaComunidade(u);
					break;
				case 3:
					listaComunidades = comunDAO.buscarComunidades();
					mensagemC = new MensagemComunidade();
					msgCDAO = new MensagemComunidadeDAO();
					String titulo = null, conteudo;
					
					System.out.println("Comunidades existentes");
					System.out.println("----------------------");
					if(listaComunidades.size() == 0){
						System.out.println("Ainda não há comunidades cadastradas na rede.\n");
					}else{
						System.out.println("-------------------------------");
						for(Comunidade c: listaComunidades){
							System.out.println("Código: "+c.getIdComunidade());
							System.out.println("Nome: "+c.getNome());
							System.out.println("Descrição: "+c.getDescricao());
							System.out.println("-------------------------------");
						}
						
						System.out.printf("Escolha o código da comunidade para a qual deseja enviar uma mensagem: ");
						
						do{
							try{
								cod = Integer.parseInt(read.next());
								c = comunDAO.buscarPorId(cod);
								if(c == null){
									System.err.println("Por favor, entre com um código de comunidade existente!");
								}
							}catch(NumberFormatException e){
								cod = -1;
								System.err.println("Por favor, digite uma opção válida!");
								
							}
						}while(cod == -1 || c==null);
						
						
						System.out.println("Deseja inserir título na mensagem? <1-Sim><2-Não>");
						opc = read.nextInt();
						
						read.nextLine();
						
						if (opc == 1){
							System.out.printf("Título: ");
							titulo = read.nextLine();
						}
						
						System.out.println("Escreva sua mensagem: ");
						conteudo = read.nextLine();
						
						mensagemC.setTitulo(titulo);
						mensagemC.setConteudo(conteudo);
						mensagemC.setId_usuario_remetente(user.getIdUsuario());
						mensagemC.setId_comunidade(cod);
						
						Calendar c = Calendar.getInstance();
						Date d = c.getTime();
						
						DateFormat data = DateFormat.getDateInstance(DateFormat.MEDIUM);
						//System.out.println("Data brasileira: "+data.format(d));
						
						DateFormat hora = DateFormat.getTimeInstance();
						//System.out.println("Hora formatada: "+hora.format(d));
						
						String data_hora = data.format(d)+", "+hora.format(d);
						
						mensagemC.setData_hora(data_hora);
						
						try {
							msgCDAO.enviarMensagemComunidade(user, comunDAO.buscarPorId(cod), mensagemC);
						} catch (EnviarMensagemComunidadeException e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
						}
						
					}
					
					ctrlPrincipal.limparTela();
					
					telaComunidade(u);
					break;
				case 4:
					System.out.println("Minhas comunidades");
					System.out.println("------------------");
					
					user.comunidades = new ArrayList<Integer>();
					comun.mensagensComunidade = new ArrayList<MensagemComunidade>();
					
					user.comunidades = comunidade_usuarioDAO.buscarComunidadesPorUsuario(user);
					
					if(user.comunidades.size()==0){
						System.out.println("Você ainda não é membro de nenhuma comunidade\n");
					}else{
						for(Integer id_comunidade: user.comunidades){
							c = comunDAO.buscarPorId(id_comunidade);
							System.out.println("Código: "+c.getIdComunidade());
							System.out.println("Nome: "+c.getNome());
							System.out.println("Descrição: "+c.getDescricao());
							user = userDAO.buscarPorId(c.getIdUsuarioGerente());
							System.out.println("Gerente: "+user.getNome()+" ("+user.getNome_usuario()+")");
							System.out.println("----------------------------------------------");
						}
						
						System.out.printf("Informe o código da comunidade para ver as mensagens: ");
						
						do{
							try{
								cod = Integer.parseInt(read.next());
								comun = comunDAO.buscarPorId(cod);
								if(comun == null){
									System.err.println("Por favor, entre com um código de comunidade existente!");
								}
							}catch(NumberFormatException e){
								cod = -1;
								System.err.println("Por favor, digite uma opção válida!");
								
							}
						}while(cod == -1 || comun==null);
						
						msgCDAO = new MensagemComunidadeDAO();
						
						comun.mensagensComunidade = msgCDAO.buscarMensagensPorComunidade(comun);
						
						System.out.println("\nTotal de mensagens: "+comun.mensagensComunidade.size());
						
						if(comun.mensagensComunidade.size() == 0){
							System.out.println("Ainda não há postagens nessa comunidade.");
						}else{
							System.out.println("\n----------------------------------------------");
							for(MensagemComunidade m: comun.mensagensComunidade){
								System.out.println("Título: "+m.getTitulo());
								System.out.println("Mensagem: "+m.getConteudo());
								user = userDAO.buscarPorId(m.getId_usuario_remetente());
								System.out.println("Remetente: "+user.getNome()+" "+user.getSobrenome());
								System.out.println("Data e hora da postagem: "+m.getData_hora());
								System.out.println("----------------------------------------------");
							}
						}
						
					}
					
					ctrlPrincipal.limparTela();
					
					telaComunidade(u);
					break;
					
				case 5:
					listaComunidades = comunDAO.buscarComunidadesPorGerente(user.getIdUsuario());
					System.out.println("-----------------------");
					System.out.println("Comunidades gerenciadas");
					System.out.println("-----------------------");
					
					if(listaComunidades.size() == 0){
						System.out.println("\nOps, você não gerencia nenhuma comunidade.");
					}else{
						System.out.println("---------------------------------");
						for(Comunidade c: listaComunidades){
							System.out.println("Código: "+c.getIdComunidade());
							System.out.println("Nome: "+c.getNome());
							System.out.println("Descrição: "+c.getDescricao());
							c.membrosComunidade = new ArrayList<Integer>();
							c.membrosComunidade = comunidade_usuarioDAO.buscaMembrosPorComunidade(c.getIdComunidade());
							System.out.println("Número de membros: "+c.membrosComunidade.size());
							System.out.println("Membros: ");
							for(int i = 0; i < c.membrosComunidade.size(); i++){
								user2 = userDAO.buscarPorId(c.membrosComunidade.get(i));
								System.out.printf(user2.getNome()+" ("+user2.getNome_usuario()+") - ");
							}
							System.out.println();
							System.out.println("---------------------------------");
						}
						
						System.out.println("Deseja remover um membro de alguma comunidade? 1-Sim | 2-Não");
						
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
							System.out.println("Informe o código da comunidade que contém o membro a ser excluído: ");
							
							do{
								try{
									cod = Integer.parseInt(read.next());
									comun = comunDAO.buscarPorId(cod);
									if(comun == null){
										System.err.println("Por favor, entre com um código de comunidade existente!");
									}
								}catch(NumberFormatException e){
									cod = -1;
									System.err.println("Por favor, digite uma opção válida!");
									
								}
							}while(cod == -1 || comun==null);
							
							
							comun.membrosComunidade = new ArrayList<Integer>();
							
							comun.membrosComunidade = comunidade_usuarioDAO.buscaMembrosPorComunidade(cod);
							
								if(comun.membrosComunidade.size() == 0){
									System.out.println("A comunidade não possui membros para serem removidos!");
								}else{
									
									for(int i = 0; i < comun.membrosComunidade.size(); i++){
										user = userDAO.buscarPorId(comun.membrosComunidade.get(i));
										System.out.println("Código: "+user.getIdUsuario()+" Nome: "+user.getNome());
									}
									
									int cod_user;
									
									user2 = new Usuario();
									
									System.out.println("\nInforme o código do membro a ser excluído: ");
									
									do{
										try{
											cod_user = Integer.parseInt(read.next());
											user2 = userDAO.buscarPorId(cod_user);
											if(user2== null){
												System.err.println("Por favor, entre com um código de usuário existente!");
											}
										}catch(NumberFormatException e){
											cod_user = -1;
											System.err.println("Por favor, digite uma opção válida!");
											
										}
									}while(cod_user == -1 || user2==null);
									
									comunidade_usuarioDAO.removerMembro(comun, user2);
								}
								
								
								
							}
							
							
					}
					
					ctrlPrincipal.limparTela();
					
					telaComunidade(u);
					break;
				case 6:
					Usuario gerente;
					listaComunidades = comunDAO.buscarComunidadesNaoGerenciadas(user.getIdUsuario());
					int cod;
					
					if(listaComunidades.size() == 0){
						System.out.println("Ainda não comunidades cadastradas na rede.\n");
					}else{
						System.out.println("----------------------------");
						for(Comunidade c: listaComunidades){
							System.out.println("Código: "+c.getIdComunidade());
							System.out.println("Nome: "+c.getNome());
							System.out.println("Descrição: "+c.getDescricao());
							gerente = userDAO.buscarPorId(c.getIdUsuarioGerente());
							System.out.println("Gerente: "+gerente.getNome()+" "+gerente.getSobrenome());
							System.out.println("----------------------------");
						}
						
						System.out.println("Informe o código da comunidade que deseja participar: ");
						
						do{
							try{
								cod = Integer.parseInt(read.next());
								c = comunDAO.buscarPorId(cod);
								if(c == null){
									System.err.println("Por favor, entre com um código de comunidade existente!");
								}
							}catch(NumberFormatException e){
								cod = -1;
								System.err.println("Por favor, digite uma opção válida!");
								
							}
						}while(cod == -1 || c==null);
						
						//gerente da comunidade c
						gerente = userDAO.buscarPorId(c.getIdUsuarioGerente());
						
						//criando pedido de participacao ao gerente
						pedido = new PedidoComunidade();
						pedidoDAO = new PedidoComunidadeDAO();
						
						pedido.setIdUsuarioGerente(gerente.getIdUsuario());
						pedido.setIdUsuarioRemetente(user.getIdUsuario());
						pedido.setIdComunidadePedido(cod);
						pedido.setStatus_pedido("pendente");
						
						try {
							pedidoDAO.criarPedido(pedido);
						} catch (PedidoComunidadeException e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
						}
						
					}
					
					ctrlPrincipal.limparTela();
					
					telaComunidade(u);
					break;
				
				case 7:
					pedidoDAO = new PedidoComunidadeDAO();
					Usuario remetente = new Usuario();
					user.pedidosComunidade = pedidoDAO.buscarPedidosPorGerente(user.getIdUsuario(), "pendente");
					
					if(user.pedidosComunidade.size() == 0){
						System.out.println("Não há solicitações de usuários em sua comunidade!");
					}else{
						for(PedidoComunidade p: user.pedidosComunidade){
							System.out.println("Novo pedido:");
							
							remetente = userDAO.buscarPorId(p.getIdUsuarioRemetente());
							System.out.println("O usuário "+remetente.getNome()+" deseja ingressar em sua comunidade. ");
							System.out.println("1-Aceitar ou 2-Recusar");
							
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
								c = comunDAO.buscarPorId(p.getIdComunidadePedido());
								comunidade_usuarioDAO.adicionarMembro(c, remetente);
								
							}else{
								p.setStatus_pedido("recusado");
							}
							
							pedidoDAO.atualizarPedido(p);
						}
					}
					
					ctrlPrincipal.limparTela();
					
					telaComunidade(u);
					break;
				case 8:
					ctrlPrincipal = new ControladorPrincipal();
					ctrlPrincipal.limparTela();
					ctrlPrincipal.carregarTelaUsuario(u);
					break;
			}
		}

}
