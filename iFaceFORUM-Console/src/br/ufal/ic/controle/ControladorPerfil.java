package br.ufal.ic.controle;

import java.util.Scanner;
import br.ufal.ic.modelo.Usuario;
import br.ufal.ic.persistencia.UsuarioDAO;

public class ControladorPerfil {
	
	Scanner read = new Scanner(System.in);
	
	int opc, cod;
	String nome_usuario;
	String senha;
	String nome;
	String sobrenome;
	String email;
	int idade;
	int sexo;
	
	ControladorPrincipal ctrlPrincipal;
	
	Usuario user;
	UsuarioDAO userDAO = new UsuarioDAO();
	
	public void telaPerfil(Usuario u){
		
		System.out.println("-----------------");
		System.out.println("PERFIL DO USUÁRIO");
		System.out.println("-----------------");
		
		userDAO = new UsuarioDAO();
		user = userDAO.buscarPorNomeUsuario(u.getNome_usuario());
		
		System.out.println("\n1 - Visualizar perfil");
		System.out.println("2 - Atualizar perfil");
		System.out.println("3 - Voltar\n");
		
		do{
			try{
				opc = Integer.parseInt(read.next());
				if(opc < 1 || opc > 3){
					System.err.println("Por favor, digite apenas os números indicados!");
					opc = -1;
				}
			}catch(NumberFormatException e){
				opc =-1;
				System.err.println("Por favor, digite uma opção válida!");
				
			}
		}while( opc == -1);
		
		ctrlPrincipal = new ControladorPrincipal();
		
		ctrlPrincipal.limparTela();
		
		switch(opc){
			case 1: {
				System.out.println("\n--------------------------------");
				System.out.println("Informações do perfil do usuário");
				System.out.println("--------------------------------\n");
				System.out.println("Nome: "+user.getNome());
				System.out.println("Sobrenome: "+user.getSobrenome());
				System.out.println("Email: "+user.getEmail());
				System.out.println("Idade: "+user.getIdade());
				if(user.getSexo().equals("M")){
					System.out.println("Sexo: "+user.getSexo()+"asculino");
				}else{
					System.out.println("Sexo: "+user.getSexo()+"eminino");
				}
				System.out.println("Nome de usuário: "+user.getNome_usuario());
				System.out.println("Senha: "+user.getSenha());
				
				ctrlPrincipal = new ControladorPrincipal();
				ctrlPrincipal.limparTela();
				
				telaPerfil(u);
			} break;
			
			case 2: {
				System.out.println("\n---------------------------");
				System.out.println("Edição de perfil do usuário");
				System.out.println("---------------------------");
				System.out.println("\nSelecione o campo que deseja alterar: \n");
				do{
					System.out.println("\n1 - Nome | 2 - Sobrenome | 3 - Email | 4 - Idade | 5 - Sexo | 6 - Nome de usuário | 7 - Senha | 8 - Voltar");
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
					
					read.nextLine(); 
					
					switch(opc){
						case 1: System.out.printf("Novo nome: ");	
								nome = read.nextLine();
								user.setNome(nome);
								System.out.println("Nome alterado com sucesso!");
								break;
						case 2: System.out.printf("Novo sobrenome: ");	
								sobrenome = read.nextLine();
								user.setSobrenome(sobrenome);
								System.out.println("Sobrenome alterado com sucesso!");
								break;
						case 3: System.out.printf("Novo email: ");	
								email = read.next();
								user.setEmail(email);
								System.out.println("Email alterado com sucesso!");
								break;
						case 4: System.out.printf("Nova idade: ");
								do{
									try{
										idade = Integer.parseInt(read.next());
										if(idade<0){
											System.err.println("Informe uma idade positiva!");
											idade = -1;
										}
									}catch(NumberFormatException e){
										idade = -1;
										System.err.println("Informe uma idade válida!");
										read.reset();
									}
								}while(idade == -1);
								
								user.setIdade(idade);
								System.out.println("Idade alterada com sucesso!");
								break;
						case 5: System.out.println("Novo sexo: 1-M ou 2-F");
								do{
									try{
										 sexo = Integer.parseInt(read.next());
										if(sexo!=1 && sexo!=2){
											sexo = -1;
											System.err.println("Digite apenas os números indicados!");
										}
									}catch(NumberFormatException e){
										sexo = -1;
										System.err.println("Digite uma opção válida!");
									}
								}while(sexo == -1);
								
								if(sexo == 1) user.setSexo("M");
								else if(sexo == 2) user.setSexo("F");
								
								System.out.println("Sexo alterado com sucesso!");
								break;
						case 6: Usuario usuarioTeste = new Usuario();
						 		do{
									System.out.printf("Novo nome de usuário: ");
									nome_usuario = read.nextLine();
									usuarioTeste = userDAO.buscarPorNomeUsuario(nome_usuario);
									
									if(usuarioTeste != null){
										System.out.println("O nome de usuário já está em uso. Por favor, insira outro nome.");
									}
									
								}while(usuarioTeste!=null);
						 		
						 		user.setNome_usuario(nome_usuario);
						 		System.out.println("Nome de usuário alterado com sucesso!");
						 		break;
						case 7: System.out.printf("Nova senha: ");
								senha = read.nextLine();
								user.setSenha(senha);
								System.out.println("Senha alterada com sucesso!");
								break;					
					}
					
					
				}while(opc != 8);
				
				user.setNome((user.getNome()));
				user.setSobrenome(user.getSobrenome());
				user.setEmail(user.getEmail());
				user.setIdade(user.getIdade());
				user.setSexo(user.getSexo());
				user.setNome_usuario(user.getNome_usuario());
				user.setSenha(user.getSenha());
				
				userDAO.atualizar(user);
				
				ctrlPrincipal.limparTela();
				
				telaPerfil(user);
			} break;
			
			case 3: {
				ctrlPrincipal.limparTela();
				ctrlPrincipal.carregarTelaUsuario(user);
			} break;	
			
		}
	}
	
}
