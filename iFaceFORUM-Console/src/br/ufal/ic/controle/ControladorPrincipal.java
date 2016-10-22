package br.ufal.ic.controle;

import java.util.Scanner;

import br.ufal.ic.exceptions.LoginJaExisteException;
import br.ufal.ic.exceptions.UsuarioNaoEncontradoException;
import br.ufal.ic.modelo.Usuario;
import br.ufal.ic.persistencia.UsuarioDAO;

public class ControladorPrincipal {

	Scanner read = new Scanner(System.in);

	int opc, cod;
	String nome_usuario;
	String senha;
	String nome;
	String sobrenome;
	String email;
	int idade;
	int sexo;

	ControladorPerfil ctrlPerfil = new ControladorPerfil();
	ControladorAmizade ctrlAmizade = new ControladorAmizade();
	ControladorComunidade ctrlComunidade = new ControladorComunidade();

	Usuario user;
	UsuarioDAO userDAO = new UsuarioDAO();

	public void limparTela() {
		for (int i = 0; i < 5; i++) {
			System.out.println();
		}
	}

	public void menuPrincipal() {

		System.out.println("------------------------");
		System.out.println("Seja bem vindo ao iFace!");
		System.out.println("------------------------");
		
		System.out.println("\nJá possui uma conta? 1-Sim ou 2-Não: ");

		while(true) {
			try {
				opc = Integer.parseInt(read.next());
				if (opc == 1) {
					loginUsuario();
				} else {
					if (opc == 2) {
						cadastrarUsuario();
					} else {
						System.err.println("Por favor, entre apenas com os números indicados!\n");
					}
				}
			} catch(NumberFormatException e){
				System.err.println("Informe apenas números!\n");
				read.reset();
			}
		}

	}
	
	int aux = 1;
	int verificador = 0;
	
	public void loginUsuario(){
		
		do{
			if(aux == 1){
				System.out.printf("\nNome de usuário: ");
				read.nextLine();
				nome_usuario = read.nextLine();
				System.out.printf("Senha: ");
				senha = read.nextLine();
			}else{
				System.out.printf("\nNome de usuário: ");
				nome_usuario = read.nextLine();
				
				System.out.printf("Senha: ");
				senha = read.nextLine();
			}
		
			user = new Usuario();
			user.setNome_usuario(nome_usuario);
			user.setSenha(senha);
			
			try {
				autenticarUsuario(user);
			} catch (Exception e) {
				verificador = -1;
				System.err.println(e.getMessage());
				read.reset();
			}	
		
		}while(verificador == -1);
	}

	public void autenticarUsuario(Usuario u) throws Exception{
		userDAO = new UsuarioDAO();
		user = userDAO.buscarPorNomeUsuario(u.getNome_usuario());
		
		if(user == null){
			aux = 2;
			throw new UsuarioNaoEncontradoException(u);
		} else {
			if (user.getSenha().equals(u.getSenha())) {
				System.out.println("\nCarregando seu perfil...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				limparTela();
				carregarTelaUsuario(user);
			} else {
				System.out.println("\nOps, usuario e senha não conferem");
				aux = 2;
				loginUsuario();
			}
		}
	}

	Usuario usuarioCad = new Usuario();
	
	public void cadastrarUsuario(){

		//Usuario usuarioTeste = new Usuario();
		
		System.out.printf("Nome: ");
		read.nextLine();
		nome = read.nextLine();

		System.out.printf("Sobrenome: ");
		sobrenome = read.nextLine();

		System.out.println("Email: (Campo Opcional: Digite --- se não quiser informá-lo)");
		
		email = read.next();

		System.out.println("Idade: (Campo Opcional: Digite 0 se não quiser informá-lo)");
		
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
		
		System.out.printf("Sexo: 1(M) ou 2(F): ");
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
		
		
		usuarioCad.setNome(nome);
		usuarioCad.setSobrenome(sobrenome);
		usuarioCad.setEmail(email);
		usuarioCad.setIdade(idade);
		if(sexo == 1) usuarioCad.setSexo("M");
		else if(sexo == 2) usuarioCad.setSexo("F");
		
		read.nextLine();
		
		do{
			try {
				read.reset();
				validaNomeUsuario();
			} catch (Exception e) {
				verificador = -1;
				System.err.println(e.getMessage());
				read.reset();
			}	
		}while(verificador == -1);
		
	}
	
	public void validaNomeUsuario() throws Exception{
		
		userDAO = new UsuarioDAO();
		Usuario usuario_teste = new Usuario();
		
		System.out.printf("\nInforme seu nome de usuário: ");
		nome_usuario = read.nextLine();
			
		usuario_teste = userDAO.buscarPorNomeUsuario(nome_usuario);
			
		if(usuario_teste != null){
			throw new LoginJaExisteException(nome_usuario);
		}
		
		System.out.printf("Informe sua senha: ");
		senha = read.nextLine();
			
		usuarioCad.setNome_usuario(nome_usuario);
		usuarioCad.setSenha(senha);

		userDAO.cadastrar(usuarioCad);
			
		limparTela();
		
		menuPrincipal();

	}
	
	public void carregarTelaUsuario(Usuario u) {
		aux = 1;
		userDAO = new UsuarioDAO();
		user = userDAO.buscarPorNomeUsuario(u.getNome_usuario());
		
		System.out.println("----------------------------------------------------------");
		System.out.println("Seja bem vindo, " + user.getNome() + " " + user.getSobrenome());
		System.out.println("----------------------------------------------------------");

		System.out.println("1 - Perfil");
		System.out.println("2 - Amigos");
		System.out.println("3 - Comunidades");
		System.out.println("4 - Remover conta");
		System.out.println("5 - Sair\n");
		
		do{
			try{
				opc = Integer.parseInt(read.next());
				if(opc<1 || opc>5){
					opc = -1;
					System.err.println("Digite números de 1 a 5!");
				}
			}catch(NumberFormatException e){
				opc = -1;
				System.err.println("Digite uma opção válida");
				read.reset();
			}
			
		}while(opc == -1);
		
		limparTela();
		
		switch (opc) {
			case 1:
				ctrlPerfil.telaPerfil(user);
				break;
			case 2:
				ctrlAmizade.telaAmigos(user);
				break;
			case 3:
				ctrlComunidade.telaComunidade(user);
				break;
			case 4:
				removerConta(user);
				break;
			case 5:
				limparTela();
				menuPrincipal();
				break;
		}

	}

	public void removerConta(Usuario u) {
		System.out.println("Deseja realmente remover sua conta: ");
		System.out.println("1-Sim | 2-Não");
		
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
		
		if (opc == 1) {
			userDAO.excluir(u);
			limparTela();
			menuPrincipal();
		} else {
			limparTela();
			carregarTelaUsuario(u);
		}
	}
}
