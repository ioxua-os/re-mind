package br.edu.overise.agenda.visual;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.modelo.desktop.PainelEsquerdo;
import br.edu.overise.agenda.teste.EsquemasDeCores;

/**
 * Painel usado para exibição das notificações
 * @author Overise
 */
public class PainelCores extends PainelEsquerdo{// implements Scrollable{
	/** */
	private static final long serialVersionUID = -3073714546935059964L;

	List<BotaoOpcao> cores = new ArrayList<>();
	
	public PainelCores(){
		super("P A L E T A  D E  C O R E S");
		this.setLayout(null);
		
		
		this.setBackground(Configuracao.COR_ATUAL);
	
		this.setLocation((int)this.HIDDEN_X, 0);
		
		Dimension d = new Dimension((int)(this.getWidth() - (this.ANCORA_ESQUERDA * 2)), (int)(Configuracao.ALTURA_TELA_PERCENT * 3.5));
		
		int alt = this.getTitulo().getY() + this.getTitulo().getHeight() + 5;
		
		for(Color c : Configuracao.CORES){
			if(c.equals(Configuracao.COR_ATUAL)) continue;
			BotaoOpcao o = new BotaoOpcao();
			o.setSize(d);
			o.setLocation((int)ANCORA_ESQUERDA, alt);
			o.setCores(c, c.brighter(), c.darker());
			cores.add(o);
			this.add(o);
			alt += (int)(o.getHeight() * 1.23);
			o.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Configuracao.setCor(c);
				}
			});
		}

		this.setSize(this.getWidth(), this.getHeight() + alt);
		this.setPreferredSize(this.getSize());
		this.setVisible(true);
	}
	
	public void colorir() {
		cores.stream().forEach( (b) -> this.remove(b) );
		
		Dimension d = new Dimension((int)(this.getWidth() - (this.ANCORA_ESQUERDA * 2)), (int)(Configuracao.ALTURA_TELA_PERCENT * 3.5));
		
		int alt = this.getTitulo().getY() + this.getTitulo().getHeight() + 5;
		for(Color c : Configuracao.CORES){
			if(c.equals(Configuracao.COR_ATUAL) || c.equals(Configuracao.CLOUDS) || 
					c.equals(Configuracao.SILVER) || c.equals(Configuracao.CONCRETE) || 
					c.equals(Configuracao.ASBESTAS) || c.equals(Configuracao.ASPHALT) || 
					c.equals(Configuracao.MIDNIGHT)) continue;
			BotaoOpcao o = new BotaoOpcao();
			o.setSize(d);
			o.setLocation((int)ANCORA_ESQUERDA, alt);
			o.setCores(c, c.brighter(), c.darker());
			cores.add(o);
			this.add(o);
			alt += (int)(o.getHeight() * 1.23);
			o.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Configuracao.setCor(c);
				}
			});
		}
	}
}
