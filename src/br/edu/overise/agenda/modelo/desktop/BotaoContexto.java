package br.edu.overise.agenda.modelo.desktop;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.visual.MenuContexto;

/**
 * Classe usada para customizar <tt>JMenuItem</tt>, usada por sua vez para as opções de um <tt>MenuContexto</tt>
 * @author Overise
 * @see JMenuItem
 * @see MenuContexto
 */
public class BotaoContexto extends JMenuItem{
	public BotaoContexto(String t){
		super(t);
		this.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
		this.setBorder(null);
		this.setForeground(Configuracao.COR_ATUAL);
	}
	
	@Override
	public void repaint() {
		this.setForeground(Configuracao.COR_ATUAL);
		super.repaint();
	}
}
