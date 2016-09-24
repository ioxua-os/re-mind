package br.edu.overise.agenda.visual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.Dia;

/**
 * <tt>JPanel</tt> superior da interface, a fim de mostrar os dias da semana, de domingo a sábado.
 * @author Overise
 * @see Agenda
 */
public class BarraSuperior extends JPanel{
	/** */
	private static final long serialVersionUID = -3665047286912745333L;
	
	static final double LARGURA = Configuracao.LARGURA_TELA_PERCENT * 92 + 1;
	static final double ALTURA = Configuracao.FIVE_ALTURA_TELA_PERCENT;
	
	private JLabel lbAtualmente;
	
	public BarraSuperior() {
		this.setSize((int)LARGURA, (int)ALTURA * 2);
		this.setLocation((int)Configuracao.X, 0);
		this.setLayout(null);
		this.setBackground(Configuracao.ASPHALT);
		
		lbAtualmente = new JLabel("ASDASd");
		lbAtualmente.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbAtualmente.setSize(lbAtualmente.getPreferredSize());
		lbAtualmente.setLocation((int)(this.getWidth() / 2 - lbAtualmente.getWidth() / 2),
								(int)(this.getHeight() / 2 - lbAtualmente.getHeight() / 2 - ALTURA / 2));
		lbAtualmente.setForeground(Configuracao.COR_ATUAL_COMPLEMENTAR);
		this.add(lbAtualmente);
		
		String[] dias = {
				"D O M", "S E G", "T E R", "Q U A", "Q U I", "S E X", "S A B"
		};
		
		JLabel day;
		for(int i = 0; i < dias.length; i++){
			day = new JLabel(dias[i]);
			day.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
			day.setSize(day.getPreferredSize());
			day.setLocation((int)(Dia.LARGURA / 2 - day.getWidth() / 2 + Dia.LARGURA * i), (int)(this.getHeight() / 2 - day.getHeight() / 2 + ALTURA / 2));
			day.setForeground(Configuracao.COR_ATUAL_COMPLEMENTAR);
			
			this.add(day);
		}
		
		BotaoOpcao btSair = new BotaoOpcao("SAIR");
		btSair.setSize((int)( Configuracao.LARGURA_TELA_PERCENT * 3.5 ), (int)ALTURA);
		btSair.setLocation(this.getWidth() - btSair.getWidth() - 20, 5);
		btSair.setCores(new Color[]{ Configuracao.SILVER, Configuracao.POMEGRANATE, Configuracao.ALIZARIN });
		btSair.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(btSair);
	}
	
	public void setarAtualmente(String s) {
		lbAtualmente.setText(s + " de " + Agenda.ANO_ATUAL);
		lbAtualmente.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbAtualmente.setSize(lbAtualmente.getPreferredSize());
		lbAtualmente.setLocation((int)(this.getWidth() / 2 - lbAtualmente.getWidth() / 2),
								(int)(this.getHeight() / 2 - lbAtualmente.getHeight() / 2 - ALTURA / 2));
	}
}
