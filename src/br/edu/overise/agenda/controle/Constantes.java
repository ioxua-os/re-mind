package br.edu.overise.agenda.controle;

import java.time.DayOfWeek;

public enum Constantes {
	// TODO ALGUEM EM NOME DE CHESSUS ME ENSINA A USAR ISSO AQUI ;---;
	DOMINGO(DayOfWeek.SUNDAY.getValue()),
	SEGUNDA(DayOfWeek.MONDAY.getValue()),
	TERCA(DayOfWeek.TUESDAY.getValue()),
	QUARTA(DayOfWeek.WEDNESDAY.getValue()),
	QUINTA(DayOfWeek.THURSDAY.getValue()),
	SEXTA(DayOfWeek.FRIDAY.getValue()),
	SABADO(DayOfWeek.SATURDAY.getValue()),
	ASD;
	static{
	}
	public int value;
	Constantes(int v) {
		this.value = v;
	}
	private Constantes() { }
}
