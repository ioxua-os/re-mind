package br.edu.overise.agenda.modelo.desktop;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.geral.Usuario;
import br.edu.overise.agenda.visual.PainelLogin;

public class MiniPerfil extends JPanel {
	public static final int LARGURA = (int)(Configuracao.QUAD_LARGURA * 2);
	public static final int ALTURA = (int)(Configuracao.QUAD_ALTURA * 2);
	
	Usuario user;
	
	public MiniPerfil(Usuario u, PainelLogin pai){
		this.user = u;
		Color princ = u.getCor();
		
		this.setSize(LARGURA, ALTURA);
		this.setLayout(null);
		this.setBackground(Configuracao.COR_ATUAL_COMPLEMENTAR);
		
		ImageIcon img = new ImageIcon("images/avatars/" + u.getAvatar() + ".png");
		JLabel lb = new JLabel(new ImageIcon( img.getImage().getScaledInstance(LARGURA, ALTURA, Image.SCALE_DEFAULT) ));
		lb.setBounds(0, 0, LARGURA, ALTURA);
		this.add(lb);
		
		this.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				Configuracao.USER_LOGADO = u;
				System.out.println("CLICOU EM " + u);
				pai.criarTelaSenha(u);
			}
			
			@Override public void mouseEntered(MouseEvent e) {
				lb.setBorder(BorderFactory.createLineBorder(Configuracao.INTRFC_CORES_COMPLEMENTARES.get(u.getCor()), 5));
			}
			
			@Override public void mouseExited(MouseEvent e) {
				lb.setBorder(null);
			}
			
			@Override public void mousePressed(MouseEvent e) {
				lb.setBorder(BorderFactory.createLineBorder(Configuracao.INTRFC_CORES_SUPLEMENTARES.get(u.getCor()), 5));
			}
			
			@Override public void mouseReleased(MouseEvent e) {
				lb.setBorder(BorderFactory.createLineBorder(Configuracao.INTRFC_CORES_COMPLEMENTARES.get(u.getCor()), 5));
			}
		});
	}
}
