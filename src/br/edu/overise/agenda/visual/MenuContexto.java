package br.edu.overise.agenda.visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.BotaoContexto;
import br.edu.overise.agenda.modelo.desktop.Dia;

/**
 * Classe usada para customizar <tt>JPopupMenu</tt>, usada para a criação de menus de contexto.
 * @author Overise
 * @see JPopupMenu
 * @see BotaoContexto
 */
public class MenuContexto extends JPopupMenu{
	public MenuContexto(Dia d) {
		this.setBorder(BorderFactory.createLineBorder(Configuracao.MIDNIGHT, 1));
		
		JMenuItem item = new BotaoContexto("Visualizar dia");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Agenda.DIA.isVisible()) Agenda.DIA.setVisible(false);
				else{
					Agenda.DIA.mostrarDia(d);
					Agenda.abrir(Agenda.DIA);
				}
			}
		});
		this.add(item);
		
		this.setSize((int)(this.getWidth() * 1.25), this.getHeight());
	}
	
}