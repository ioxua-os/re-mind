package br.edu.overise.agenda.visual;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.geral.Usuario;

/**
 * Classe responsável pela criação e exibição da Splash Screen da <tt>Agenda</tt>.
 * 
 * @see Agenda
 * @author Overise
 */
public class Splash extends JFrame{
	JFrame frame = this;
	
	/**
	 * Construtor da classe <tt>Splash</tt>.
	 * 
	 * @param tempoParaIniciar O tempo que a tela de splash deve ser mostrada
	 */
	public Splash(int tempoParaIniciar) {
		this.setSize((int)Configuracao.LARGURA_TELA, (int)Configuracao.ALTURA_TELA);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocation(0, 0);
		this.setLayout(null);
		
		JLabel lbImg = new JLabel(new ImageIcon("images/reMind1mini.png"));
		lbImg.setSize(lbImg.getPreferredSize());
		lbImg.setLocation(getWidth() / 2 - lbImg.getWidth() / 2, getHeight() / 2 - lbImg.getHeight() / 2);
		this.add(lbImg);
		
		this.setVisible(true);
		
		JPanel fundo = new JPanel();
		fundo.setBackground(Configuracao.COR_ATUAL);
		fundo.setBounds(this.getBounds());
		this.add(fundo);
		
		new Timer().schedule(new TimerTask() {
			int cont = 0;
			@Override
			public void run() {
				cont++;
				if(cont == tempoParaIniciar * 100){
					new PainelLogin();
//					new TelaLogin();
					new Timer().schedule(new TimerTask() {
						@Override public void run() {
							frame.dispose();
						}
					}, 75);
					this.cancel();
				}
			}
		}, 0, 1);
	}

	public Splash(int tempoParaIniciar, Usuario u) {
		this.setSize((int)Configuracao.LARGURA_TELA, (int)Configuracao.ALTURA_TELA);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocation(0, 0);
		this.setLayout(null);
		
		JLabel lbImg = new JLabel(new ImageIcon("images/reMind1mini.png"));
		lbImg.setSize(lbImg.getPreferredSize());
		lbImg.setLocation(getWidth() / 2 - lbImg.getWidth() / 2, getHeight() / 2 - lbImg.getHeight() / 2);
		this.add(lbImg);
		
		this.setVisible(true);
		
		JPanel fundo = new JPanel();
		fundo.setBackground(Configuracao.COR_ATUAL);
		fundo.setBounds(this.getBounds());
		this.add(fundo);
		
		new Timer().schedule(new TimerTask() {
			int cont = 0;
			@Override
			public void run() {
				cont++;
				if(cont == tempoParaIniciar * 100){
					Configuracao.agenda = new Agenda();
					new Timer().schedule(new TimerTask() {
						@Override public void run() {
							frame.dispose();
						}
					}, 75);
					this.cancel();
				}
			}
		}, 0, 1);
	}

}
