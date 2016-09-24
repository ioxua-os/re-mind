package br.edu.overise.agenda.visual;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.modelo.desktop.PainelEsquerdo;

/**
 * Painel usado para exibição das notificações
 * @author Overise
 */
public class PainelPerfil extends PainelEsquerdo {
	/** */
	private static final long serialVersionUID = -3073714546935059964L;
	
	boolean salvando = false;
		
	public PainelPerfil(){
		super("M E U  P E R F I L");
		HIDDEN_X  = Configuracao.X - LARGURA;
		invertido = true;
		this.setLocation(0 - (int)LARGURA, 0);
		this.setBackground(Configuracao.COR_ATUAL);
		

		ImageIcon i = Configuracao.getImagemPorNome(Configuracao.USER_LOGADO.getAvatar());
		JLabel lb = new JLabel(new ImageIcon(i.getImage().getScaledInstance((int)Configuracao.QUAD_LARGURA * 2, (int)Configuracao.QUAD_ALTURA * 2, Image.SCALE_DEFAULT)));
		lb.setSize((int)Configuracao.QUAD_LARGURA * 2, (int)Configuracao.QUAD_ALTURA * 2);
		lb.setLocation(ANCORA_ESQUERDA, getTitulo().getHeight() + getTitulo().getY() + 5);
		this.add(lb);
		
		JLabel tx = new JLabel(Configuracao.USER_LOGADO.getNome());
		tx.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		tx.setForeground(Configuracao.CLOUDS);
		tx.setSize(tx.getPreferredSize());
		tx.setLocation(lb.getX(), lb.getY() + lb.getHeight() + 10);
		this.add(tx);
		
		Dimension d = new Dimension((int)(LARGURA - ANCORA_ESQUERDA * 2), 50);
		
		JTextField txLogin = new JTextField();
		txLogin.setSize(d);
		txLogin.setLocation(ANCORA_ESQUERDA, getTitulo().getHeight() + getTitulo().getY() + 5);
		txLogin.setEnabled(false);
		this.add(txLogin);
		
		JTextField txSenha = new JTextField();
		txSenha.setSize(d);
		txSenha.setLocation(ANCORA_ESQUERDA, txLogin.getHeight() + txLogin.getY() + 5);
		txSenha.setEnabled(false);
		this.add(txSenha);
		
		BotaoOpcao btMexer = new BotaoOpcao("Alterar");
		btMexer.setSize(btMexer.getSlimSize());
		btMexer.setLocation(ANCORA_ESQUERDA, txSenha.getY() + txSenha.getHeight() + 5);
		btMexer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!salvando) {
					txSenha.setEnabled(true);
					txLogin.setEnabled(true);
					btMexer.setText("Salvar");
					btMexer.setSize(btMexer.getSlimSize());
					salvando = true;
				}
				else {
					Configuracao.USER_LOGADO.setNome(txLogin.getText());
					Configuracao.USER_LOGADO.setSenha(txSenha.getText());
					txSenha.setEnabled(false);
					txLogin.setEnabled(false);
					btMexer.setText("Alterar");
					btMexer.setSize(btMexer.getSlimSize());
					salvando = false;
				}
			}
		});
	}
}
