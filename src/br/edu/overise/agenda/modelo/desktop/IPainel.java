package br.edu.overise.agenda.modelo.desktop;

import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.Visual;
import br.edu.overise.agenda.visual.Agenda;

/**
 * Superclasse comum a todos os paineis customizáveis do projeto.
 * @author Overise
 */
public abstract class IPainel extends JPanel{
	// TODO TROCA ESSE NOME ¬¬
	
	public double HIDDEN_X = Configuracao.LARGURA_TELA;
	public double LARGURA = Configuracao.LARGURA_TELA_PERCENT * 92 + 1;
	public boolean invertido;
	public boolean transition = true;
	
	public IPainel(){
		this.setSize((int)LARGURA, (int)Configuracao.ALTURA_TELA);
		this.setLocation((int)Configuracao.LARGURA_TELA, 0);
		this.setLayout(null);
		this.invertido = false;
		
		this.setBackground(Configuracao.COR_ATUAL_COMPLEMENTAR);
	}
}
