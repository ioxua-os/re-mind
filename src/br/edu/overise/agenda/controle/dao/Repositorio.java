package br.edu.overise.agenda.controle.dao;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.derby.tools.ij;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.geral.Usuario;

public class Repositorio {
	private static Connection conn = null;
	private static String nomeBanco = "bdAgenda";
	
	public static Connection getConnection(){
		if(conn != null)
			return conn;
    	String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try {
    		Class.forName(driver).newInstance();
			conn = DriverManager.getConnection("jdbc:derby:" + nomeBanco + ";create=false");
			DatabaseMetaData dt = conn.getMetaData();
			if(dt.getSchemas().next()){
				ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM tbExistencia");
				if(rs.next()){
					System.out.println("BANCO EXISTE");
					rs.close();
					return conn;
				}
				rs.close();
			}
		} catch (SQLException e) {
			if(e.getSQLState().equals("XJ004")){
				try {
					conn = DriverManager.getConnection("jdbc:derby:" + nomeBanco + ";create=true");
					System.out.println("BANCO NN EXISTE, SENDO CRIADO");
					if(montarBanco())
						return conn;
					return null;
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
					System.out.println(e1.getNextException().getMessage());
					System.err.println("BANCO NÃO PODE SER CRIADO\nEXCEÇÃO NÃO TRATADA");
				}
			}
			else if(e.getSQLState().equals("42X05")){
				System.err.println("TABELAS NÃO EXISTENTES, CRIANDO-AS");
				if(montarBanco())
					return conn;
				return null;
			}
			else if(e.getSQLState().equals("XJ040"))
				System.err.println("OUTRA INSTÂNCIA DE ReMind ESTÁ EM EXECUÇÃO\nPOR FAVOR FECHE AMBAS ANTES DE PROSSEGUIR");
			else
				System.err.println("EXCEÇÃO SQL NÃO TRATADA: " + e.getSQLState() + "\n" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("CLASSE " + driver + " NÃO ENCONTRADA");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err.println("CLASSE " + driver + " NÃO PODE SER INSTANCIADA\n(PODE-SE TRATAR DE UMA CLASSE ABSTRATA, INTERFACE etc.");
		} catch (IllegalAccessException e) {
			System.err.println("CLASSE ATUAL (" + Repositorio.class.getName() + ") NÃO TEM ACESSO A CLASSE " + driver);
		}
        return null;
	}
	
	private static boolean montarBanco(){
		try {
			Statement s = conn.createStatement();
			s.execute("CREATE TABLE tbExistencia(existe INT NOT NULL)");
			s.execute("INSERT INTO tbExistencia(existe) VALUES (1)");
			s.execute("CREATE TABLE tbUsuario(" + 
					"codUsuario INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), " +   
					"nomeUsuario VARCHAR(100) NOT NULL, " +
					//"loginUsuario VARCHAR(100) NOT NULL, " +
					"senhaUsuario VARCHAR(100) NOT NULL," +
					"avatarUsuario VARCHAR(50)," +
					"corPreferida VARCHAR(20))");
			
			s.execute("CREATE TABLE tbEvento(" +
					"codEvento INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), " +
					"tituloEvento VARCHAR(100) NOT NULL, " +
					"dtInicioEvento DATE NOT NULL, " +
					"hrInicioEvento TIME NOT NULL, " +
					"dtFinalEvento DATE NOT NULL, " +
					"hrFinalEvento TIME NOT NULL, " +
					"descEvento VARCHAR(200), " +
					"codUsuario INT NOT NULL)");
			
			if(Configuracao.POPULAR_BANCO) {
				String[] nomes = { "Ioxua", "Jamil", "Pathie", "Bluno", "Luhcas", "Rique" };
				String[] avatares = { "balloon", "moon", "pathy", "burger", "headphone", "coffee" };
				String[] cores = { "peter river", "belize hole", "amethyst", "pomegranate", "turquoise", "sun flower" };
				int i = 0;
				do {
					Usuario u = new Usuario(i);
					u.setNome(nomes[i]);
					u.setSenha("senha" + nomes[i].toLowerCase());
					u.setAvatar(avatares[i]);
					u.setCor(Configuracao.getCor(cores[i]));
					DAOUsuario.cadastrar(u);
					i++;
				} while(i < 6);
			}
			
			return true;
		} catch (SQLException e) {
			System.err.println("EXCEÇÃO NÃO TRATADA:" + e.getSQLState() + "\n" + e.getMessage());
		}
		return false;
	}
	
	public static boolean closeConnection(){
        try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException e) { 
			if (e.getErrorCode() == 50000 && "XJ015".equals(e.getSQLState())) {
				System.out.println("BANCO DESCONECTOU NORMALMENTE");
			}
			else System.err.println("TENTATIVA DE DESCONEXÃO FALHOU");
		}
		return false;
	}
	
}