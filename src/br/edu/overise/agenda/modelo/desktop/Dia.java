package br.edu.overise.agenda.modelo.desktop;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.dao.DAOEvento;
import br.edu.overise.agenda.modelo.geral.Evento;
import br.edu.overise.agenda.visual.Agenda;
import br.edu.overise.agenda.visual.MenuContexto;
import br.edu.overise.agenda.visual.MenuLateral;

/**
 * Classe representando um dia de um dado mês. Usado para exibição.
 * @author Overise
 * @see ControleDia
 */
public class Dia extends JPanel implements MouseListener, Exibivel{
	
	/**
	 * Largura padrão dos <tt>Dias</tt>, correspondente a 13% da <tt>Configuracao.LARGURA_TELA</tt>
	 * @see Dia
	 * @see Configuracao#LARGURA_TELA
	 * @see Configuracao#LARGURA_TELA_PERCENT
	 */
	public static final double LARGURA = Configuracao.LARGURA_TELA_PERCENT * 13 + 1;

	/**
	 * Altura padrão dos <tt>Dias</tt>, correspondente a 19% da <tt>Configuracao.ALTURA_TELA</tt>
	 * @see Dia
	 * @see Configuracao#ALTURA_TELA
	 * @see Configuracao#ALTURA_TELA_PERCENT
	 */
	public static final double ALTURA = Configuracao.ALTURA_TELA_PERCENT * 18 + 1;
	
	/**
	 * Correspondente a 5% de <tt>Dia.ALTURA</tt>
	 * @see Dia#ALTURA
	 */
	static final double FIVE_PERCENT_ALTURA = ALTURA / 100 * 5;
	
	/**
	 * Correspondente a metade de <tt>Dia.LARGURA</tt>
	 * @see Dia#LARGURA
	 */
	static final double HALF_LARGURA = LARGURA / 2;
	
	private MenuContexto context;
	
	/**
	 * Cor usada no fundo do <tt>Dia</tt>
	 * @see Dia
	 */
	private Color BACK;

	/**
	 * Cor usada no conteúdo do <tt>Dia</tt>
	 * @see Dia
	 */
	private Color DEST;
	
	private JLabel lbDt = new JLabel();
	private JPanel estado = new JPanel();

	public int dia, mes, ano;
	public LocalDate dataDia;
	public List<Evento> eventos = new ArrayList<Evento>();

	/**
	 * Esquema de cores usado para dias localizados no futuro em relação à data atual (<tt>Configuracao.DATA</tt>)
	 * @see Configuracao#DATA
	 */
	private Color[] futuro = new Color[] {
			Configuracao.CLOUDS,
			Configuracao.COR_ATUAL,
			Configuracao.CLOUDS.brighter(),
			Configuracao.COR_ATUAL.brighter()
	};

	/**
	 * Esquema de cores usado para dias correspondentes à data atual (<tt>Configuracao.DATA</tt>)
	 * @see Configuracao#DATA
	 */
	private Color[] atual = new Color[] {
			Configuracao.SILVER,
			Configuracao.COR_ATUAL_COMPLEMENTAR,
			Configuracao.SILVER.brighter(),
			Configuracao.COR_ATUAL_COMPLEMENTAR.brighter()
	};

	/**
	 * Esquema de cores usado para dias localizados no passado em relação à data atual (<tt>Configuracao.DATA</tt>)
	 * @see Configuracao#DATA
	 */
	private Color[] passado = new Color[] {
			Configuracao.CONCRETE,
			Configuracao.ASBESTAS,
			Configuracao.CONCRETE.brighter(),
			Configuracao.ASBESTAS.brighter()
	};
	
	/**
	 * Método construtor da classe <tt>dia</tt>
	 * @param d Número do dia (no mês) correspondente ao novo <tt>Dia</tt>
	 * @param m Número do mês correspondente ao novo <tt>Dia</tt>
	 * @param a Número do ano correspondente ao novo <tt>Dia</tt>
	 */
	public Dia(int d, int m, int a){
		try {
			dia = d;
			mes = m;
			ano = a;
			lbDt.setText("" + d);
			cliche();
			eventos = DAOEvento.getEventosPorUsuarioPorData(Configuracao.USER_LOGADO, dataDia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Dia(LocalDate ld){
		try {
			this.dataDia = ld;
			lbDt.setText("" + ld.getDayOfMonth());
			cliche();
			eventos = DAOEvento.getEventosPorUsuarioPorData(Configuracao.USER_LOGADO, dataDia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Método usado para padronizar os <tt>Dias</tt>
	 */
	private void cliche(){
		this.setSize((int)LARGURA, (int)ALTURA);
		this.setLayout(null);
		this.setCores();
		this.setBackground(BACK);
		this.addMouseListener(this);
		
//		JPanel estado = new JPanel();
		estado.setBackground(DEST);
		estado.setSize((int)LARGURA, (int)FIVE_PERCENT_ALTURA);
		estado.setLocation(0, (int)(ALTURA - FIVE_PERCENT_ALTURA));
		this.add(estado);

		lbDt.setFont(Configuracao.SEGOE_REGULAR_ABSURDA);
		lbDt.setForeground(DEST);
		lbDt.setSize(lbDt.getPreferredSize());
		lbDt.setLocation((int)(HALF_LARGURA - lbDt.getWidth() / 2), 0);
		this.add(lbDt);
		
		this.context = new MenuContexto(this);
	}
	
	/**
	 * Método responsável por determinar se o <tt>Dia</tt> em questão está no futuro, presente ou passado em relação a <tt>Configuracao.DATA</tt>
	 * @return 1 se for no futuro;
	 *		   0 se correspondente;
	 *		   -1 se no passado.
	 */
	private int getEsquema(){
		if(dataDia.isEqual(Configuracao.DATA)) return 0;
		else if(dataDia.isAfter(Configuracao.DATA)) return 1;
		else return -1;
	}

	private void setCores(){
		switch(getEsquema()){
		case 1:
			BACK = futuro[0];
			DEST = futuro[1];
			break;
		case 0:
			BACK = atual[0];
			DEST = atual[1];
			break;
		case -1:
			BACK = passado[0];
			DEST = passado[1];
			break;
		}
	}

	public JLabel getLbDt() {
		return lbDt;
	}
	
	@Override
	public String toString() {
		String r = "";
		r += "X: " + this.getX();
		r += " Y: " + this.getY();
		r += " W: " + this.getWidth();
		r += " H: " + this.getHeight();
		r += " D: " + this.dia;
		r += "\n";
		
		return r;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		showPopup(e);
	}

	@Override
	public void mousePressed(MouseEvent e) { }
	
	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
	@Override
	public void mouseClicked(MouseEvent e) { }

	private void showPopup(MouseEvent e){
		if(e.isPopupTrigger()){
			context.show(e.getComponent(), e.getX(), e.getY());
		}
	}
	
	public String getMes(){
		mes = mes == 0? dataDia.getMonthValue() : mes;
		if(mes == 1) return "Janeiro";
		else if(mes == 2) return "Fevereiro";
		else if(mes == 3) return "Março";
		else if(mes == 4) return "Abril";
		else if(mes == 5) return "Maio";
		else if(mes == 6) return "Junho";
		else if(mes == 7) return "Julho";
		else if(mes == 8) return "Agosto";
		else if(mes == 9) return "Setembro";
		else if(mes == 10) return "Outubro";
		else if(mes == 11) return "Novembro";
		else if(mes == 12) return "Dezembro";
		return null;
	}

	public void editarEvento(Evento e) {
		for(Evento ev : eventos)
			if(ev.getCodEvento() == e.getCodEvento()) {
				eventos.remove(ev);
				eventos.add(e);
			}
	}
}
