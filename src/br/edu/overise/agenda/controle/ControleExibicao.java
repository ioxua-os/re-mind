package br.edu.overise.agenda.controle;

import java.awt.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

import br.edu.overise.agenda.modelo.desktop.Dia;
import br.edu.overise.agenda.modelo.desktop.Exibivel;
import br.edu.overise.agenda.modelo.desktop.Mes;
import br.edu.overise.agenda.visual.Agenda;

/**
 * Classe responsável por manipular <tt>Dias</tt> e retorná-los de acordo com a necessidade.
 * @author Overise
 */
public class ControleExibicao {
	
	/**
	 * Método responsável por montar um <tt>ArrayList</tt> de <tt>Dia</tt> de acordo com um dado mês de um dado ano.
	 * @param mes O mês em questão
	 * @param ano O ano em questão
	 * @return Um <tt>ArrayList</tt> com <tt>n Dias</tt> representando o mês do ano em questão (<tt>n</tt> = o número de dias do mês).
	 * @see ArrayList
	 * @see Dia
	 */
	public static ArrayList<Exibivel> gerarMes(int mes, int ano){
		int qtdDias = YearMonth.of(ano, getMes(mes)).lengthOfMonth();
		//Dia[] retorno = new Dia[qtdDias];
		ArrayList<Exibivel> retorno = new ArrayList<Exibivel>();
		
		int y = (int)Configuracao.FIVE_ALTURA_TELA_PERCENT * 2;
		int x = 0;
		int fimTela = x + (int)Dia.LARGURA * 7 + 1;
		int constr = 0;

		LocalDate ld = Configuracao.DATA;
		ld = ld.withMonth(mes);
		ld = ld.withYear(ano);
		ld = ld.withDayOfMonth(1);
		
		for(int i = 0; i < qtdDias; i++){
			Dia d = new Dia(ld.plusDays(i));
			
			if(i == 0){
				x += (int)Dia.LARGURA * getDiaSemana(d.dataDia.getDayOfWeek().getValue());
				d.setLocation(x, y);
			}
			else d.setLocation(x, y);
			x += (int)Dia.LARGURA;
			
			if(constr != 0 && constr % 7 == 0){
				y += (int)Dia.ALTURA;
				x = 0;
				constr = 0;
			}
			else if(x + Dia.LARGURA > fimTela){
				y += (int)Dia.ALTURA;
				x = 0;
				constr = 0;
			}
			constr++;
			retorno.add(d);
		}

		return retorno;
	}

	public static ArrayList<Exibivel> gerarAno(int ano){
		ArrayList<Exibivel> retorno = new ArrayList<Exibivel>();

		for(int i = 0, m = 1; i < 3; i++)
			for(int j = 0; j < 4; j++, m++){
				Mes a = new Mes(m, ano);
				a.setLocation((int)(j * Mes.LARGURA), (int)((Configuracao.FIVE_ALTURA_TELA_PERCENT * 2) + (i * Mes.ALTURA)));
				
				retorno.add(a);
			}

		return retorno;
	}
	
	/**
	 * Método para padronizar os valores para dias da semana
	 * @param dia O número do dia da semana em questão (segundo <tt>Calendar</tt>)
	 * @see Calendar
	 * @see ControleDia#getMes(int)
	 * @return Um valor definido para cada dia da semana (0 para domingo, 1 p/ segunda e etc.)
	 */
	private static int getDiaSemana(int dia){
		switch(dia){
			case 7: return 0;
//			case 1: return 1;
//			case 2: return 2;
//			case 3: return 3;
//			case 4: return 4;
//			case 5: return 5;
//			case 6: return 6;
			default: return dia;
		}
	}
	
	/**
	 * Método para padronizar valores para os meses
	 * @see Calendar
	 * @see ControleDia#getDiaSemana(int)
	 * @param mes O número do mês em questão (segundo <tt>Calendar</tt>)
	 * @return Um valor definido para cada mês (1 p/ Janeiro, 2 p/ Fevereiro, 12 p/ Dezembro, etc.)
	 */
	private static int getMes(int mes){
		switch(mes){
//		case Calendar.JANUARY: return 1;
//		case Calendar.FEBRUARY: return 2;
//		case Calendar.MARCH: return 3;
//		case Calendar.APRIL: return 4;
//		case Calendar.MAY: return 5;
//		case Calendar.JUNE: return 6;
//		case Calendar.JULY: return 7;
//		case Calendar.AUGUST: return 8;
//		case Calendar.SEPTEMBER: return 9;
//		case Calendar.OCTOBER: return 10;
//		case Calendar.NOVEMBER: return 11;
//		case Calendar.DECEMBER: return 12;
		default: return mes;
		}
	}
}
