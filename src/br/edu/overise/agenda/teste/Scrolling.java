package br.edu.overise.agenda.teste;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.edu.overise.agenda.controle.Configuracao;

public class Scrolling extends JFrame{
	public Scrolling(){
		this.setTitle("Teste de JScrollPane");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JPanel pane = new JPanel();
		pane.setSize(this.getWidth(), 1000);
		pane.setBackground(Configuracao.COR_ATUAL);
		JLabel asd = new JLabel("<html>OIE MUNDOI IUHSAISAUHS IASIU ASIBA SBAIBSAIUSIAI<br> AUSKA SIA SABS A SAJSAN SJHAI<br> IAU SIANS HABS IABS ABS IUASD ASUDN ASDNASJ <br>DUANS DUASUH ASO21Y 8974 2<br> AIUSDH AIUS DASD ADB KJASDUIAS");
		asd.setFont(Configuracao.SEGOE_NEGRITO_ABSURDA);
		asd.setBounds(5, 5, 150, 500);
		pane.add(asd);
		
		JScrollPane SP = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		SP.setSize(getWidth() - 5, getHeight() - 30);
		SP.setLocation(0, 0);
		SP.setWheelScrollingEnabled(true);
		SP.setBorder(null);
		this.add(SP);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Scrolling();
	}
}
