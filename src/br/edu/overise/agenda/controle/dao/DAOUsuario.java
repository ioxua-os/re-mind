package br.edu.overise.agenda.controle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.geral.Usuario;

public class DAOUsuario {
	
	public static List<Usuario> getTodosUsuarios() throws SQLException {
		List<Usuario> retorno = new ArrayList<>();
		
		Statement s = Repositorio.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM tbUsuario");
		while(rs.next()){
			Usuario u = new Usuario(rs.getInt("codUsuario"));
			u.setNome(rs.getString("nomeUsuario"));
			u.setSenha(rs.getString("senhaUsuario"));
			u.setAvatar(rs.getString("avatarUsuario"));
			u.setCor(Configuracao.getCor(rs.getString("corPreferida")));
			retorno.add(u);
		}
		s.close();
		
		return retorno;
	}

	public static boolean cadastrar(Usuario u) throws SQLException {
		String cor = u.getCor() == null? "belize hole" : Configuracao.getNome(u.getCor());
		Statement s = Repositorio.getConnection().createStatement();
		return s.execute("INSERT INTO tbUsuario(nomeUsuario, senhaUsuario, avatarUsuario, corPreferida) " +
					"VALUES('" + u.getNome() + "', '" + u.getSenha() + "', '" + u.getAvatar() + "', '" + cor + "')");
	}
	
	public static Usuario getUsuarioPorCod(int c) throws SQLException {
		Statement s = Repositorio.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM tbUsuario WHERE codUsuario = " + c);

		rs.next();
		Usuario u = new Usuario(rs.getInt("codUsuario"));
		u.setNome(rs.getString("nomeUsuario"));
		u.setSenha(rs.getString("senhaUsuario"));
		u.setAvatar(rs.getString("avatarUsuario"));
		u.setCor(Configuracao.getCor(rs.getString("corPreferida")));
		
		return u;
	}
	
	public static boolean editar(Usuario u) throws SQLException{
		Statement s = Repositorio.getConnection().createStatement();
		return 1 == s.executeUpdate("UPDATE tbUsuario SET nomeUsuario = '" + u.getNome() + "', " +
				"senhaUsuario = '" + u.getSenha() + "', avatarUsuario = '" + u.getAvatar() + 
				"', corPreferida = '" + Configuracao.getNome(u.getCor()) + "' WHERE codUsuario = " + u.getCod());
	}
	
	public static int remover(Usuario u) throws SQLException{
		Statement s = Repositorio.getConnection().createStatement();
		return s.executeUpdate("DELETE FROM tbUsuario WHERE codUsuario = " + u.getCod());
	}

	public static Usuario logar(String l, String s) throws SQLException {
		return getTodosUsuarios().stream()
				.filter(u -> u.getNome().equals(l) && u.getSenha().equals(s))
				.collect(Collectors.toList()).get(0);
	}
}
