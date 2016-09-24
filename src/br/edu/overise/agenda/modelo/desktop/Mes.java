package br.edu.overise.agenda.modelo.desktop;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.visual.Agenda;
import br.edu.overise.agenda.visual.MenuContexto;

public class Mes extends JPanel implements Exibivel {

	/**
	 * Largura padrão dos <tt>Mess</tt>, correspondente a 23% da <tt>Configuracao.LARGURA_TELA</tt>
	 * @see Mes
	 * @see Configuracao#LARGURA_TELA
	 * @see Configuracao#LARGURA_TELA_PERCENT
	 */
	public static final double LARGURA = Configuracao.LARGURA_TELA_PERCENT * 23 + 1;

	/**
	 * Altura padrão dos <tt>Mess</tt>, correspondente a 33% da <tt>Configuracao.ALTURA_TELA</tt>
	 * @see Mes
	 * @see Configuracao#ALTURA_TELA
	 * @see Configuracao#ALTURA_TELA_PERCENT
	 */
	public static final double ALTURA = Configuracao.ALTURA_TELA_PERCENT * 30 + 1;

	/**
	 * Correspondente a metade de <tt>Dia.LARGURA</tt>
	 * @see Dia#LARGURA
	 */
	static final double HALF_LARGURA = LARGURA / 2;

	/**
	 * Cor usada no fundo do <tt>Mes</tt>
	 * @see Mes
	 */
	public Color BACK = Configuracao.CLOUDS;
	
	public Color HOVER = BACK.brighter(),
				CLICK = BACK.darker();

	/**
	 * Cor usada no conteúdo do <tt>Mes</tt>
	 * @see Mes
	 */
	private Color DEST = Configuracao.ASPHALT;

	private JLabel lbDt = new JLabel();
	
	public int mes, ano;
	
	Mes frame = this;
	
	public Mes(int m, int a){
		this.mes = m;
		this.ano = a;
		cliche(getMes(m));
	}

	/**
	 * Método usado para padronizar os <tt>Dias</tt>
	 */
	private void cliche(String t){
		this.setSize((int)LARGURA, (int)ALTURA);
		this.setLayout(null);
		this.setBackground(BACK);

		lbDt.setText(t);
		lbDt.setFont(Configuracao.SEGOE_REGULAR_ABSURDA);
		lbDt.setForeground(DEST);
		lbDt.setSize(lbDt.getPreferredSize());
		lbDt.setLocation((int)(HALF_LARGURA - lbDt.getWidth() / 2), (int)(this.getHeight() / 2 - lbDt.getHeight() / 2));
		this.add(lbDt);
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.setBackground(frame.HOVER);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				frame.setBackground(frame.CLICK);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setBackground(frame.BACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setBackground(frame.HOVER);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO PSEUDO ACTION LISTENER
				Agenda.mudarVisualizacao(Configuracao.VISUALIZACAO_POR_MES);
//				Configuracao.agenda.visualizar(mes, ano);
//				Agenda.setData(mes, ano);
				System.out.println(mes + "  " + ano);
				Agenda.restartInterface();
			}
		});
		
		this.setVisible(true);
	}
	

	public String getMes(int mes){
		
		if(mes == Month.JANUARY.getValue()) return "Janeiro";
		else if(mes == Month.FEBRUARY.getValue()) return "Fevereiro";
		else if(mes == Month.MARCH.getValue()) return "Março";
		else if(mes == Month.APRIL.getValue()) return "Abril";
		else if(mes == Month.MAY.getValue()) return "Maio";
		else if(mes == Month.JUNE.getValue()) return "Junho";
		else if(mes == Month.JULY.getValue()) return "Julho";
		else if(mes == Month.AUGUST.getValue()) return "Agosto";
		else if(mes == Month.SEPTEMBER.getValue()) return "Setembro";
		else if(mes == Month.OCTOBER.getValue()) return "Outubro";
		else if(mes == Month.NOVEMBER.getValue()) return "Novembro";
		else if(mes == Month.DECEMBER.getValue()) return "Dezembro";
		return null;
	}
}
