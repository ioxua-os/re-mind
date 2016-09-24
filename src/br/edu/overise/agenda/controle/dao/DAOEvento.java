package br.edu.overise.agenda.controle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.overise.agenda.modelo.geral.Evento;
import br.edu.overise.agenda.modelo.geral.Usuario;

public class DAOEvento {
	public static boolean cadastrar(Evento e) throws SQLException {
		// FORMATAÇÃO CERTA DE DATA?
		String horaI = parseHora(e.getHrInicio());
		String horaF = parseHora(e.getHrFinal());
		String dataI = parseData(e.getDataInicio());
		String dataF = parseData(e.getDataFinal());
		
		Statement s = Repositorio.getConnection().createStatement();
		return s.execute("INSERT INTO tbEvento(tituloEvento, descEvento, codUsuario, "
				+ "dtInicioEvento, hrInicioEvento, dtFinalEvento, hrFinalEvento) "
				+ "VALUES('" + e.getNomeEvento() + "', '" + e.getDescEvento() + "', " + e.getDonoEvento().getCod() + ", '" + dataI
				+ "', '" + horaI + "', '" + dataF + "', '" + horaF + "')");
		
	}
	
	public static boolean editar(Evento e) throws SQLException {
		String horaI = parseHora(e.getHrInicio());
		String horaF = parseHora(e.getHrFinal());
		String dataI = parseData(e.getDataInicio());
		String dataF = parseData(e.getDataFinal());
		
		Statement s = Repositorio.getConnection().createStatement();
		return 1 == s.executeUpdate("UPDATE tbEvento SET tituloEvento = '" + e.getNomeEvento() + "', descEvento = '" + e.getDescEvento() + "', "
				+ "codUsuario = " + e.getDonoEvento().getCod() + ", dtInicioEvento = '" + dataI + "', hrInicioEvento = '" + horaI + "', "
				+ "dtFinalEvento = '" + dataF + "', hrFinalEvento = '" + horaF + "' WHERE codEvento = " + e.getCodEvento());
	}
	
	public static boolean remover(Evento e) throws SQLException {
		Statement s = Repositorio.getConnection().createStatement();
		return 1 == s.executeUpdate("DELETE FROM tbEvento WHERE codEvento = " + e.getCodEvento());
	}

	public static List<Evento> getEventosPorUsuario(Usuario u) throws SQLException {
		List<Evento> retorno = new ArrayList<>();
		
		Statement s = Repositorio.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM tbEvento WHERE codUsuario = " + u.getCod());
		while(rs.next()){
			Evento f = new Evento(rs.getInt("codEvento"));
			f.setDescEvento(rs.getString("descEvento"));
			f.setNomeEvento(rs.getString("tituloEvento"));
			f.setHrInicio(parseHora(rs.getString("hrInicioEvento")));
			f.setHrFinal(parseHora(rs.getString("hrFinalEvento")));
			f.setDataFinal(parseData(rs.getString("dtFinalEvento")));
			f.setDataInicio(parseData(rs.getString("dtInicioEvento")));
			f.setDonoEvento(u);
			
			retorno.add(f);
		}
		s.close();
		rs.close();
		
		return retorno;
	}
	
	public static Evento getEventoPorNome(String n) throws SQLException {
		Statement s = Repositorio.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM tbEvento WHERE tituloEvento = '" + n + "'");
		
		while(rs.next()){
			Evento f = new Evento(rs.getInt("codEvento"));
			f.setDescEvento(rs.getString("descEvento"));
			f.setNomeEvento(rs.getString("tituloEvento"));
			f.setHrInicio(parseHora(rs.getString("hrInicioEvento")));
			f.setHrFinal(parseHora(rs.getString("hrFinalEvento")));
			f.setDataFinal(parseData(rs.getString("dtFinalEvento")));
			f.setDataInicio(parseData(rs.getString("dtFinalEvento")));
			
			f.setDonoEvento(DAOUsuario.getUsuarioPorCod(rs.getInt("codUsuario")));
			
			return f;
		}
		return null;
	}

	public static List<Evento> getEventosPorUsuarioPorData(Usuario u, LocalDate data) throws SQLException {
		return getEventosPorUsuario(u).stream()
					.filter(e -> data.isEqual(e.getDataInicio()))
					.collect(Collectors.toList());
	}

	public static List<Evento> getEventosPorUsuarioPorData(Usuario u, LocalDateTime data) throws SQLException {
		return getEventosPorUsuarioPorData(u, data.toLocalDate());
	}
	
	public static List<Evento> getEventosPorUsuarioPorData(Usuario u, int dia, int mes, int ano) throws SQLException {
		return getEventosPorUsuarioPorData(u, LocalDate.of(ano, mes, dia));
	}

	public static List<Evento> getEventosPorUsuarioPorMes(Usuario u, Month mes) throws SQLException {
		return getEventosPorUsuario(u).stream()
					.filter( e -> e.getDataInicio().getMonth().getValue() == mes.getValue() )
					.collect(Collectors.toList());
	}

	public static List<Evento> getEventosPorUsuarioPorMes(Usuario u, int mes) throws SQLException {
		return getEventosPorUsuario(u).stream()
					.filter( e -> e.getDataInicio().getMonth().getValue() == mes )
					.collect(Collectors.toList());
	}
	
	public static List<Evento> getEventosPorUsuarioPorMes(Usuario u, int mes, int ano) throws SQLException {
		return getEventosPorUsuario(u).stream()
					.filter( e -> e.getDataInicio().getMonth().getValue() == mes && e.getDataInicio().getYear() == ano )
					.collect(Collectors.toList());
	}

	public static List<Evento> getTodosEventos() throws SQLException {
		List<Evento> retorno = new ArrayList<>();
		
		Statement s = Repositorio.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM tbEvento");
		while(rs.next()) {
			Evento f = new Evento(rs.getInt("codEvento"));
			f.setDescEvento(rs.getString("descEvento"));
			f.setNomeEvento(rs.getString("tituloEvento"));
			f.setHrInicio(parseHora(rs.getString("hrInicioEvento")));
			f.setHrFinal(parseHora(rs.getString("hrFinalEvento")));
			
			Statement s2 = Repositorio.getConnection().createStatement();
			ResultSet rs2 = s2.executeQuery("SELECT * FROM tbUsuario WHERE codUsuario = " + rs.getInt("codUsuario")); // TODO MTO PESADO?
			
			while(rs2.next()) {
				Usuario u = new Usuario(rs2.getInt("codUsuario"));
				u.setNome(rs2.getString("nomeUsuario"));
				u.setSenha(rs2.getString("senhaUsuario"));
				
				f.setDonoEvento(u);
			}
			
			rs2.close();
			s2.close();
			
			retorno.add(f);
		}
		s.close();
		rs.close();
		
		return retorno;
	}
	
	private static void mostrarUsuarios() throws SQLException {
		Statement s = Repositorio.getConnection().createStatement();
		System.out.println("---------- TODOS OS USERS -------------");
		ResultSet rs = s.executeQuery("SELECT * FROM tbUsuario");
		while(rs.next()) {
			Usuario u = new Usuario(rs.getInt("codUsuario"));
			u.setNome(rs.getString("nomeUsuario"));
			u.setSenha(rs.getString("senhaUsuario"));
			
			System.out.println(u.toString());
		}
		System.out.println("-----------------------");
	}

	private static LocalDate parseData(String d) {
		return LocalDate.of(Integer.parseInt(d.substring(0, 4)), Integer.parseInt(d.substring(5, 7)), Integer.parseInt(d.substring(8)));
	}
	
	private static LocalTime parseHora(String h) {
		return LocalTime.of(Integer.parseInt(h.substring(0, 2)), Integer.parseInt(h.substring(3, 5)));
	}

	private static String parseHora(LocalTime l) {
		return ( l.getHour() > 10? l.getHour() : "0" + l.getHour() ) + ":"
				+ ( l.getMinute() > 10? l.getMinute() : "0" + l.getMinute() );
	}
	
	public static String parseData(LocalDate l) { // TODO dd.mm.yyyy   hh:mm:ss
		return l.getDayOfMonth() + "." + l.getMonthValue() + "." + l.getYear();
	}
}
