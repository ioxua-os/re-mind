package br.edu.overise.agenda.teste;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.dao.DAOEvento;
import br.edu.overise.agenda.controle.dao.DAOUsuario;
import br.edu.overise.agenda.controle.dao.Repositorio;
import br.edu.overise.agenda.modelo.geral.Evento;
import br.edu.overise.agenda.modelo.geral.Usuario;

public class TesteBancoDeDados {
	public static void main(String[] args) {
		try {
			Evento e = new Evento(0);
			LocalDate l = LocalDate.now().withYear(2200).withMonth(4).withDayOfMonth(14);
			LocalTime t = LocalTime.now().withHour(20).withMinute(35);
			
			e.setHrInicio(t);
			
			t = LocalTime.now().withHour(17).withMinute(22);
			
			e.setHrFinal(t);
			e.setDataInicio(l);

			l = LocalDate.now().withYear(2178).withMonth(5).withDayOfMonth(17);
			
			e.setDataFinal(l);
			e.setDescEvento("asdasdsad");
			e.setDonoEvento(new Usuario(1));
			e.setNomeEvento("TEST");
			DAOEvento.cadastrar(e);
			
			e.setNomeEvento("EDITADO");
			DAOEvento.editar(e);
			
			DAOEvento.getTodosEventos().stream().forEach(System.out::println);
			System.out.println("ASDASDASDASD");
			System.out.println(DAOEvento.getEventoPorNome("EDITADO"));
		} catch (SQLException e) {
			System.err.println("EXCEÇÃO NÃO TRATADA: " + e.getSQLState() + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String parseData(LocalDateTime l) {
		return null;
	}
}
