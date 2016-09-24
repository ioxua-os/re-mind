package br.edu.overise.agenda.modelo.geral;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento {
	private int codEvento;
	private String nomeEvento, descEvento;
	private LocalDate dataInicioEvento, dataFinalEvento;
	private LocalTime hrInicio, hrFinal;
	private Usuario donoEvento;
	
	public Evento(){}
	
	public Evento(int i) {
		setCodEvento(i);
	}
	
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public String getDescEvento() {
		return descEvento;
	}
	public void setDescEvento(String descEvento) {
		this.descEvento = descEvento;
	}
	public LocalTime getHrInicio() {
		return hrInicio;
	}
	public void setHrInicio(LocalTime hrInicio) {
		this.hrInicio = hrInicio;
	}
	public void setHrInicio(int hr) {
		this.hrInicio = LocalTime.now()
				.withHour(hr)
				.withMinute(0)
				.withSecond(0)
				.withNano(0);
	}
	public LocalTime getHrFinal() {
		return hrFinal;
	}
	public void setHrFinal(LocalTime hrFinal) {
		this.hrFinal = hrFinal;
	}
	public void setHrFinal(int hr) {
		this.hrFinal = LocalTime.now()
				.withHour(hr)
				.withMinute(0)
				.withSecond(0)
				.withNano(0);
	}
	public int getCodEvento() {
		return codEvento;
	}
	public void setCodEvento(int codEvento) {
		this.codEvento = codEvento;
	}
	public Usuario getDonoEvento() {
		return donoEvento;
	}
	public void setDonoEvento(Usuario donoEvento) {
		this.donoEvento = donoEvento;
	}

	public LocalDate getDataFinal() {
		return dataFinalEvento;
	}

	public void setDataFinal(LocalDate dataFinalEvento) {
		this.dataFinalEvento = dataFinalEvento;
	}

	public LocalDate getDataInicio() {
		return dataInicioEvento;
	}

	public void setDataInicio(LocalDate dataInicioEvento) {
		this.dataInicioEvento = dataInicioEvento;
	}
	
	@Override public String toString() {
		return "Nome:\t\t" + getNomeEvento() + "\n" +
				"Descrição:\t" + getDescEvento() + "\n" +
				"Data Início:\t" + getDataInicio() + "\n" +
				"Hora Início:\t" + getHrInicio() + "\n" +
				"Data Final:\t" + getDataFinal() + "\n" +
				"Hora Final:\t" + getHrFinal() + "\n" +
				"Dono: {\n" + getDonoEvento() + "}";
	}
	
}
