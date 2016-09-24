package br.edu.overise.agenda.controle;

import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.modelo.desktop.IPainel;

/**
 * Classe responsável por animações e efeitos visuais a ser usados pelo projeto.
 * @author Yehoshua Oliveira
 */
public final class Visual {
	/**
	 * <tt>JPanel</tt> a ser animado atualmente
	 */
	public JPanel pane;
	
	/**
	 * Movimento negativo (para a esquerda ou para cima)
	 */
	public static final int NEGATIVO = -1;
	
	/**
	 * Movimento positivo (para a direita ou para baixo)
	 */
	public static final int POSITIVO = 1;

	/**
	 * Cria um <tt>TimerTask</tt> representando a animação em questão. No caso, um movimento no <tt>eixo X</tt> do <tt>IPainel</tt> em questão.
	 * @param painel O <tt>IPainel</tt> a ser animado
	 * @param destinoX A coordenada <tt>X</tt> correspondente à localização final do <tt>IPainel</tt> em questão.
	 * @param direcao A direção do movimento (Visual.NEGATIVO ou Visual.POSITIVO)
	 * @return Um <tt>TimerTask</tt> correspondente à animação.
	 * @see TimerTask
	 * @see IPainel
	 * @see Visual#NEGATIVO
	 * @see Visual#POSITIVO
	 */
	public TimerTask slideX(JComponent painel, int destinoX, int direcao, boolean esconder){
		return new TimerTask() {
			int controleX = pane.getX();
			@Override
			public void run() {
				if(esconder) painel.setVisible(true);
				if(controleX == destinoX) {
					if(!esconder) painel.setVisible(false);
					this.cancel();
				}
				painel.setLocation(controleX, painel.getY());
				controleX += direcao;
			}
		};
	}

	/**
	 * Cria um <tt>TimerTask</tt> representando a animação em questão. No caso, um movimento no <tt>eixo X</tt> do <tt>JButton</tt> em questão.
	 * @param botao O <tt>JButton</tt> a ser animado
	 * @param destinoX A coordenada <tt>X</tt> correspondente à localização final do <tt>JButton</tt> em questão.
	 * @param direcao A direção do movimento (Visual.NEGATIVO ou Visual.POSITIVO)
	 * @return Um <tt>TimerTask</tt> correspondente à animação.
	 * @see TimerTask
	 * @see JButton
	 * @see Visual#NEGATIVO
	 * @see Visual#POSITIVO
	 */
	public TimerTask slideX(JButton botao, int destinoX, int direcao){
		return new TimerTask() {
			int controleX = botao.getX();
			@Override
			public void run() {
				if(controleX == destinoX) this.cancel();
				botao.setLocation(controleX, botao.getY());
				controleX += direcao;
			}
		};
	}

	/**
	 * @teste Tentativa falha de manter um objeto in-tela por um tempo definido. Ignorar
	 * @param pai Pai do objeto em questão
	 * @param p O objeto em questão
	 * @return Um <tt>TimerTask</tt> correspondente à saída do objeto da visão após um tempo.
	 * @bug Simplesmente não mostra o objeto.
	 */
	public TimerTask stayForDefinite(JFrame pai, JLabel p){
		return new TimerTask() {
			@Override
			public void run() {
				pai.remove(p);
				pai.repaint();
			}
		};
	}

	/**
	 * @teste Segunda tentativa falha de manter um objeto in-tela por um tempo definido. Ignorar²
	 * @param pai Pai do objeto em questão
	 * @param p O objeto em questão
	 * @return Um <tt>TimerTask</tt> correspondente à saída do objeto da visão após um tempo.
	 * @bug Simplesmente não mostra o objeto.
	 */
	public TimerTask stayForTEST(JFrame pai, JLabel p){
		return new TimerTask() {
			@Override
			public void run() {
				p.setLocation(p.getX(), p.getY() - 1);
				if(p.getY() < 0){
					pai.remove(p);
					pai.repaint();
					this.cancel();
				}
			}
		};
	}
	
	
}