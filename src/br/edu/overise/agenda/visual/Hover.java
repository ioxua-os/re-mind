package br.edu.overise.agenda.visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Window;
import java.awt.peer.MouseInfoPeer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.overise.agenda.controle.Configuracao;

/**
 * @deprecated 
 * @author Overise
 * Classe usada para mostrar os nomes de cada opção no <tt>MenuLateral</tt>
 * @see MenuLateral
 */
public class Hover extends JPanel{
	/** */
	private static final long serialVersionUID = 2100646142811294043L;
	String text = "";
	JLabel lblTexto = new JLabel();
	
	static final int LARGURA = 140;
	static final int ALTURA = 40;
	
	public Hover(){
		this.setBackground(Configuracao.COR_ATUAL_COMPLEMENTAR);
		this.setLayout(null);
		this.setSize(LARGURA, ALTURA);
		this.add(lblTexto);
		
		lblTexto.setForeground(Configuracao.CLOUDS);
		lblTexto.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		lblTexto.setLocation(5, 5);
		lblTexto.setSize(LARGURA - 10, ALTURA - 10);
		
		this.setVisible(false);
	}
	public void exibir(String texto, Component c){
		lblTexto.setText(texto);
		
		int y = c.getY();
		int h = c.getHeight();
		int w = c.getWidth();
		
		if(c.getX() <= MenuLateral.LARGURA)
			this.setLocation((int)MenuLateral.LARGURA + 4, y + h - this.getHeight() - 1);
		else 
			this.setLocation(w + 4, y + h - this.getHeight() - 1);
		
		this.setVisible(true);
	}
	public void esconder(){
		this.setVisible(false);
	}
}
