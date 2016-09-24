package br.edu.overise.agenda.visual;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.IconeVariavel;

/**
 * Menu principal da <tt>Agenda</tt>.
 * @author Overise
 * @see Agenda
 */
public class MenuLateral extends JPanel{
	
	/**
	 * Largura do <tt>MenuLateral</tt>
	 */
	public static final double LARGURA = Configuracao.LARGURA_TELA_PERCENT * 9;

	/**
	 * Altura do <tt>MenuLateral</tt>
	 */
	static final double ALTURA = Configuracao.ALTURA_TELA;
	
	private final boolean hover = false;
	private final Color[] coresGenericas = {
			Configuracao.COR_ATUAL_COMPLEMENTAR,
			Configuracao.COR_ATUAL_COMPLEMENTAR.brighter(),
			Configuracao.COR_ATUAL_COMPLEMENTAR
	};
	
	/**
	 * Construtor principal do <tt>MenuLateral</tt>
	 * @see MenuLateral
	 */
	public MenuLateral(){
		this.setBounds(0, 0, (int)LARGURA, (int)ALTURA);
		this.setBackground(Configuracao.MIDNIGHT);
		this.setLayout(null);
		
		/*BotaoOpcao btnPerfil = new BotaoOpcao(new IconeVariavel("images/perfilTest.png"));
		/TODO COMO FAZER O JLABEL INTERNO RECEBER O ACTION LISTENER DO BORÃO? o.O
		/BotaoOpcao btnPerfil = new BotaoOpcao('\uE113', Agenda.TESTES);
		BotaoOpcao btnPerfil = new BotaoOpcao('\uE113');
		btnPerfil.setLocation(0, 0);
		btnPerfil.setCores(coresGenericas);
		hover(btnPerfil, "Meu perfil");
		btnPerfil.setSize((int)LARGURA, bi.getHeight());
		btnPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Agenda.abrir(Agenda.TESTES);
			}
		});
		this.add(btnPerfil);
*/
	

		//BOTÃO PERFIL
		JButton btnPerfil = new JButton(new ImageIcon("images/businessmanMini.png"));
		padronizar(btnPerfil);
		btnPerfil.setLocation((int)(Configuracao.LARGURA_TELA_PERCENT * 0.5), (int)Configuracao.FIVE_ALTURA_TELA_PERCENT);
		hover(btnPerfil, "Configurações");
		btnPerfil.addMouseListener(new MouseListener() {
			@Override public void mouseExited(MouseEvent e) { }
			@Override public void mouseEntered(MouseEvent e) { } 
			@Override public void mouseClicked(MouseEvent e) { }
			@Override public void mouseReleased(MouseEvent e) { }
			@Override public void mousePressed(MouseEvent e) { }
		});
		btnPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Agenda.abrir(Agenda.PERFIL);
			}
		});
		this.add(btnPerfil);
		
		//BOTÃO CORES
		JButton btnCores = new JButton(new ImageIcon("images/coresMini.png"));
		padronizar(btnCores);
		btnCores.setLocation(btnPerfil.getX(), (int)(((ALTURA - (Configuracao.FIVE_ALTURA_TELA_PERCENT * 2)) * 0.425) - btnCores.getHeight() / 2));
		hover(btnCores, "Configurações");
		btnCores.addMouseListener(new MouseListener() {
			@Override public void mouseExited(MouseEvent e) { }
			@Override public void mouseEntered(MouseEvent e) { } 
			@Override public void mouseClicked(MouseEvent e) { }
			@Override public void mouseReleased(MouseEvent e) { }
			@Override public void mousePressed(MouseEvent e) { }
		});
		btnCores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Agenda.abrir(Agenda.CORES);
			}
		});
		this.add(btnCores);
		
		//BOTÃO SINO
		JButton btnSino = new JButton(new ImageIcon("images/sinoMini.png"));
		padronizar(btnSino);
		btnSino.setLocation(btnPerfil.getX(), (int)(((ALTURA - (Configuracao.FIVE_ALTURA_TELA_PERCENT * 2)) * 0.675) - btnSino.getHeight() / 2));
		hover(btnSino, "Configurações");
		btnSino.addMouseListener(new MouseListener() {
			@Override public void mouseExited(MouseEvent e) { }
			@Override public void mouseEntered(MouseEvent e) { } 
			@Override public void mouseClicked(MouseEvent e) { }
			@Override public void mouseReleased(MouseEvent e) { }
			@Override public void mousePressed(MouseEvent e) { }
		});
		btnSino.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Agenda.abrir(Agenda.NOTIF);
			}
		});
		this.add(btnSino);
		

		// BOTÃO CALENDÁRIO
		JButton btnCalendario = new JButton(new ImageIcon("images/calendarioMini.png"));
		padronizar(btnCalendario);
		btnCalendario.setLocation(btnPerfil.getX(), (int)(ALTURA - Configuracao.FIVE_ALTURA_TELA_PERCENT - btnCalendario.getHeight()));
		hover(btnCalendario, "Configurações");
		btnCalendario.addMouseListener(new MouseListener() {
			@Override public void mouseExited(MouseEvent e) { }
			@Override public void mouseEntered(MouseEvent e) { } 
			@Override public void mouseClicked(MouseEvent e) { }
			@Override public void mouseReleased(MouseEvent e) { }
			@Override public void mousePressed(MouseEvent e) { }
		});
		btnCalendario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Agenda.abrir(Agenda.CALENDARIO);
			}
		});
		this.add(btnCalendario);
		
	}
	
	/**
	 * Padronização dos botões do <tt>MenuLateral</tt>
	 * @param btn Botão a se padronizar
	 */
	private void padronizar(JButton btn){
		btn.setBorder(null);
		btn.setFocusPainted(false);
		btn.setPreferredSize(new Dimension(100, 100));
		btn.setMinimumSize(new Dimension(75, 75));
		//btn.setBackground(this.getBackground());
		btn.setContentAreaFilled(false);
		btn.setSize(new Dimension(110, 110));
	}
	/**
	 * @deprecated
	 * @param btn Botão a ser alterado
	 * @param texto Texto a ser exibido em hover
	 */
	private void hover(JButton btn, String texto){
		if(hover) btn.addMouseListener(new MouseListener() {
			@Override
			public void mouseExited(MouseEvent e) { 
				Agenda.HOVER.esconder();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Agenda.HOVER.exibir(texto, btn);
			}
			@Override public void mouseReleased(MouseEvent e) { }
			@Override public void mousePressed(MouseEvent e) { }
			@Override public void mouseClicked(MouseEvent e) { }
		});
	}
}
