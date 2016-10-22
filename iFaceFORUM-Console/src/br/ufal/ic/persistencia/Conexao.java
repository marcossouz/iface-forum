package br.ufal.ic.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao { //Classe que faz a comunicação com o banco de dados
	
	public static Connection getConnection(){ //static: Não precisa instanciar um objeto Conexao para poder utilizar esse método, basta colocar o nome da classe ponto método.
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iFaceDB?useSSL=false", "root", "admin");//caminho do BD, login e senha, respectivamente.
			//System.out.println("Conectado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar: "+ e.getMessage());
		}
		
		return con;
	}
}
