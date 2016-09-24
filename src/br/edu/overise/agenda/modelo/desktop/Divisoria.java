package br.edu.overise.agenda.modelo.desktop;

import java.awt.Container;

import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;

public class Divisoria extends JPanel{

	private int fimDaDivisao;
	
	public Divisoria(Container pai, int margem, int altura){
		this.setBackground(Configuracao.COR_ATUAL_COMPLEMENTAR);
		this.setSize(pai.getWidth() - margem, altura);
		this.setLocation(margem / 2, pai.getY() + pai.getHeight() + margem / 2);
		pai.add(this);
		
		this.fimDaDivisao = this.getY() + altura + margem / 2;
	}
	
}
