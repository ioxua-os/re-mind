package br.edu.overise.agenda.modelo.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.visual.Agenda;
import br.edu.overise.agenda.visual.MenuLateral;

/**
 * Classe genérica que extende <tt>JButton</tt> para a criação de botões no projeto, a fim de permitir uma maior customização.
 * @author Overise
 * @see JButton
 */
public class BotaoOpcao extends JButton {
	
	/**
	 * Cor para o efeito de <tt>hover</tt> do botão.
	 */
	private Color hover = Configuracao.CONCRETE;
	
	/**
	 * Cor para o efeito de <tt>clique</tt> do botão.
	 */
	private Color press = Configuracao.ASBESTAS;
	
	/**
	 * Cor padrão do botão.
	 */
	private Color sleep = Configuracao.SILVER;

	/**
	 * Controle indicando se o botão possui um <tt>ImageIcon</tt> (imagem) como conteúdo
	 * @see ImageIcon
	 */
	private boolean hasIcon = false;
	
	/**
	 * Controle indicando se o botão possui um <tt>caractere</tt> como conteúdo (usado em conjunto com <tt>Configuracao.SEGOE_SYMBOL</tt>)
	 * @see Configuracao#SEGOE_SYMBOL
	 */
	private boolean hasChar = false;
	
	private JLabel lbC;
	private Image icon;
	private char ch;
	private IPainel pane =  null;

	/**
	 * Construtor padrão do <tt>BotaoOpcao</tt>
	 * @see BotaoOpcao
	 */
	public BotaoOpcao(){
		cliche();
	}
	
	/**
	 * Construtor do <tt>BotaoOpcao</tt> envolvendo uma <tt>String</tt>
	 * @see BotaoOpcao
	 * @see String
	 * @param conteudo Título do botão
	 */
	public BotaoOpcao(String conteudo){
		super(conteudo);
		cliche();
	}
	
	/**
	 * Construtor do <tt>BotaoOpcao</tt> envolvendo um <tt>BufferedImage</tt>(imagem não formatada)
	 * @see BotaoOpcao
	 * @see BufferedImage
	 * @param i <tt>BufferedImage</tt> a ser exibida
	 */
	public BotaoOpcao(BufferedImage i){
		super(new ImageIcon(i));
		icon = i;
		hasIcon = true;
		cliche();
	}
	/**
	 * Construtor do <tt>BotaoOpcao</tt> envolvendo um <tt>ImageIcon</tt>
	 * @see BotaoOpcao
	 * @see ImageIcon
	 * @param i <tt>Imageicon</tt> em questão
	 */
	public BotaoOpcao(ImageIcon i){
		super(new ImageIcon(resize((BufferedImage) i.getImage(), (int)Configuracao.QUAD_LARGURA - 50, (int)Configuracao.QUAD_ALTURA - 50)));
		icon = i.getImage();
		hasIcon = true;
		cliche();
	}

	/**
	 * Construtor do <tt>BotaoOpcao</tt> envolvendo um caractere
	 * @see BotaoOpcao
	 * @param c Caractere a ser exibido
	 */
	public BotaoOpcao(char c){
		super("" + c);
		ch = c;
		hasChar = true;
		cliche();
		this.setSize(this.getPreferredSize());
	}
	
	/**
	 * Falha tentativa de fazer um <tt>JLabel</tt> herdar o <tt>ActionListener</tt> do botão.
	 * @param c Caractere a ser exibido
	 * @param pane <tt>IPainel</tt> a ser aberto pelo clique no <tt>JLabel</tt>
	 * @see JLabel
	 * @see ActionListener
	 * @see IPainel
	 */
	public BotaoOpcao(char c, IPainel pane){
		ch = c;
		hasChar = true;
		this.pane = pane;
		cliche();
		this.setSize(this.getPreferredSize());
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
	
	/**
	 * Método de formatação comum a todos os construtores
	 */
	private void cliche(){
		this.setBorder(null);
		this.setMargin(null);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		this.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);

		this.setSize((int)Configuracao.QUAD_LARGURA, (int)Configuracao.QUAD_ALTURA);

		Dimension d = this.getPreferredSize();
		d.setSize(d.getWidth() + (int)(Configuracao.QUAD_LARGURA / 3), d.getHeight() + (int)(Configuracao.QUAD_ALTURA / 4));
		this.setPreferredSize(d);
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
				if(pane != null) Agenda.abrir(pane);
			}
			@Override public void mouseReleased(MouseEvent e) {
				lbChar.setForeground(sleep);
			}
			@Override public void mousePressed(MouseEvent e) {
				lbChar.setForeground(press);
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
	
	public void setPress(Color cor){
		this.press = cor;
	}
	
	public Dimension getSlimSize() {
		Dimension d = this.getPreferredSize();
		d.setSize(d.getWidth() - 20, d.getHeight() - 16);
		return d;
	}
	
	/**
	 * Método para redimensionar num <tt>BufferedImage</tt> desejado com as dimensões desejadas.
	 * @param imagem Imagem a ser redimensionada
	 * @param largura Largura da nova imagem
	 * @param altura Altura da nova imagem
	 * @return Um <tt>BufferedImage</tt> já redimensionado
	 * @see BufferedImage
	 * @author David Kroukamp @ Stack Overflow
	 */
	public static BufferedImage resize(BufferedImage imagem, int largura, int altura) {
		BufferedImage bi = new BufferedImage(largura, altura, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = bi.createGraphics(); //(Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(imagem, 0, 0, largura, altura, null);
		g2d.dispose();
		return bi;
	}
}
