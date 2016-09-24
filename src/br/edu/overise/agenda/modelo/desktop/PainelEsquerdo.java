package br.edu.overise.agenda.modelo.desktop;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.visual.Agenda;

/**
 * Paineis laterais esquerdos, para informação principalmente (<tt>PainelPerfil</tt> e <tt>PainelConfig</tt> extendem isso)
 * @author Overise
 * @see PainelPerfil
 * @see PainelConfig
 */
public abstract class PainelEsquerdo extends IPainel{
	
	protected int ANCORA_ESQUERDA;
	protected int ANCORA_DIREITA;
	
	String titulo;
	private JLabel lbTitulo;
	
	public PainelEsquerdo(String t){
		super();
		
		this.LARGURA = Configuracao.LARGURA_TELA_PERCENT * 25;
		this.HIDDEN_X = 0 - this.getWidth();

		this.setSize((int)(LARGURA * 1.25), (int)Configuracao.ALTURA_TELA);
		
		this.ANCORA_ESQUERDA = (int)(Configuracao.LARGURA_TELA_PERCENT * 3.25);
		this.ANCORA_DIREITA = (int)LARGURA - (int)(Configuracao.LARGURA_TELA_PERCENT * 3.25);
		
		this.setLocation((int)HIDDEN_X, 0);
		this.invertido = true;
		this.setBackground(Configuracao.ASPHALT);
		
		lbTitulo = new JLabel(t);
		lbTitulo.setForeground(Configuracao.CLOUDS);
		lbTitulo.setFont(Configuracao.SEGOE_NEGRITO_GIGANTE);
		lbTitulo.setSize(t.length() * 22, (int)Configuracao.FIVE_ALTURA_TELA_PERCENT + 25);
		lbTitulo.setLocation(ANCORA_ESQUERDA, (int)Configuracao.ALTURA_TELA_PERCENT * 2);
		this.add(lbTitulo);
	}
	public JLabel getTitulo(){
		return lbTitulo;
	}
}
