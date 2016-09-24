package br.edu.overise.agenda.modelo.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Timer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.Visual;
import br.edu.overise.agenda.visual.Agenda;
import br.edu.overise.agenda.visual.MenuLateral;

/**
 * Classe usada exclusivamente para botões deslizáveis lateralmente.
 * @author Overise
 */
public class BotaoSlide extends JButton{
	private static Visual VISUAL = new Visual();
	private static Timer ENTRADA = new Timer();
	private static Timer SAIDA = new Timer();
	private static Timer TEXT_ENTRADA = new Timer();
	private static Timer TEXT_SAIDA = new Timer();

	private int HIDDEN_X;
	private int SHOW_X;
	private int TEXT_HIDDEN_X;
	private int TEXT_SHOW_X;
	
	private Color hover = Configuracao.COR_ATUAL_COMPLEMENTAR;
	private Color press = Configuracao.ASBESTAS;
	private Color sleep = Configuracao.SILVER;

	private boolean hasIcon = false;
	private boolean hasChar = false;
	private boolean invertido = false;
	
	private Image icon;
	private char ch;
	
	private JButton btn = this;
	private JLabel lbTexto = new JLabel();
	String show;

	public BotaoSlide(){
		cliche();
	}
	public BotaoSlide(String t){
		super(t);
		cliche();
	}
	public BotaoSlide(BufferedImage i){
		super(new ImageIcon(i));
		icon = i;
		hasIcon = true;
		cliche();
	}
	public BotaoSlide(ImageIcon i){
		super(i);
		icon = i.getImage();
		hasIcon = true;
		cliche();
	}

	public BotaoSlide(char c){
		ch = c;
		hasChar = true;
		cliche();
		this.setSize(this.getPreferredSize());
	}
	
	public BotaoSlide(String t, boolean b){
		invertido = b;
		cliche();
		show = t;
		
		boolean isV = isValido(t);
		if(isV){
			lbTexto.setText(t);
			lbTexto.setSize(lbTexto.getPreferredSize());
			lbTexto.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		}
		
		if(b){
			HIDDEN_X = (int)(Configuracao.X - getWidth() / 3);
			SHOW_X = (int)Configuracao.X;
			if(isV){
				TEXT_HIDDEN_X = (int)Configuracao.X - lbTexto.getWidth();
				TEXT_SHOW_X = (int)Configuracao.X;
			}
		}
		else{
			HIDDEN_X = (int)(Configuracao.LARGURA_TELA - getWidth() / 3 * 2);
			SHOW_X = (int)Configuracao.LARGURA_TELA - getWidth();
			if(isV){
				TEXT_HIDDEN_X = (int)Configuracao.LARGURA_TELA + lbTexto.getWidth();
				TEXT_SHOW_X = (int)Configuracao.LARGURA_TELA - lbTexto.getWidth();
			}
		}
		setLocation(HIDDEN_X, (int)(Configuracao.ALTURA_TELA / 2 - getHeight() / 3));
		if(isV){
			lbTexto.setLocation(TEXT_HIDDEN_X, this.getHeight() / 2 - lbTexto.getHeight() / 2);
			lbTexto.setVisible(true);
		}
	}
	
	public BotaoSlide(boolean in){
		this("", in);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(getModel().isPressed()) g.setColor(press);
		else if(getModel().isRollover()) g.setColor(hover);
		else g.setColor(sleep);
		
		if(hasIcon) g.drawImage(icon, 0, 0, g.getColor(), null);
		else if(hasChar) addChar();
		else g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		super.paintComponent(g);
	}
	
	private void cliche(){
		this.setBorder(null);
		this.setBackground(Configuracao.COR_ATUAL_COMPLEMENTAR.darker());
		this.setMargin(null);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setSize(new Dimension((int)Configuracao.QUAD_LARGURA / 3, (int)Configuracao.QUAD_ALTURA));
		this.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		
		this.addMouseListener(new MouseAdapter() {
			@Override public void mouseExited(MouseEvent e) {
				int direcao = invertido? Visual.NEGATIVO : Visual.POSITIVO;
				SAIDA.scheduleAtFixedRate(VISUAL.slideX(btn, HIDDEN_X, direcao), 0, 4);
//				TEXT_SAIDA.scheduleAtFixedRate(VISUAL.slideX(lbTexto, TEXT_HIDDEN_X, direcao), 0, 3);
//				Agenda.desescrever();
			}
			@Override public void mouseEntered(MouseEvent e) {
				int direcao = invertido? Visual.POSITIVO : Visual.NEGATIVO;
				ENTRADA.scheduleAtFixedRate(VISUAL.slideX(btn, SHOW_X, direcao), 0, 5);
//				TEXT_ENTRADA.scheduleAtFixedRate(VISUAL.slideX(lbTexto, TEXT_SHOW_X, direcao), 0, 3);
//				Agenda.escrever(show, invertido);
			}
		});
		
	}

	private void addChar(){
		JLabel lbChar = new JLabel("" + ch);
		lbChar.setFont(Configuracao.SEGOE_SYMBOL);
		lbChar.setSize(70, 60);
		lbChar.setForeground(sleep);
		lbChar.setLocation((int)(getWidth() / 2 - lbChar.getWidth() / 2), (int)(getHeight() / 2 - lbChar.getHeight() / 2));
		
		lbChar.addMouseListener(new MouseAdapter() {
			@Override public void mouseExited(MouseEvent e) {
				lbChar.setForeground(sleep);
			}
			@Override public void mouseEntered(MouseEvent e) {
				lbChar.setForeground(hover);
			}
			@Override public void mouseClicked(MouseEvent e) {
				lbChar.setForeground(press);
				// TODO ACTIONs
			}
		});
		
		this.add(lbChar);
	}
	
	public void setCores(Color sleep, Color hover, Color press){
		this.sleep = sleep;
		this.hover = hover;
		this.press = press;
	}
	
	public void setCores(Color[] cores){
		this.sleep = cores[0];
		this.hover = cores[1];
		this.press = cores[2];
	}
	
	private boolean isValido(String t){
		return t != null && !t.equals("");
	}
}
