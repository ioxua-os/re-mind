package br.edu.overise.agenda.visual;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.overise.agenda.controle.Configuracao;

public class TelaLogin extends JFrame {
	
	static JLabel lbLogin = new JLabel("Login:");
	static JLabel lbSenha = new JLabel("Senha:");
	
	static JTextField txLogin = new JTextField();
	static JPasswordField txSenha = new JPasswordField();
	
	static JButton btnEntra = new JButton("Entrar");
	static JButton btnCadastrar = new JButton("Cadastrar");
	
	static JPanel fundo = new JPanel();
	
	static JFrame frame;
	public TelaLogin(){
//		Usuario user = new Usuario();
		this.setSize((int)Configuracao.LARGURA_TELA, (int)Configuracao.ALTURA_TELA);
	
		this.setUndecorated(true);
		
		frame = this;

		lbLogin.setSize(150,75);
		lbLogin.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 37),(int) Configuracao.ALTURA_TELA_PERCENT * 50);
		lbLogin.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbLogin.setForeground(Configuracao.CLOUDS);
		this.add(lbLogin);
		
		lbSenha.setSize(150, 75);
		lbSenha.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 37),(int) Configuracao.ALTURA_TELA_PERCENT * 54);
		lbSenha.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbSenha.setForeground(Configuracao.CLOUDS);
		this.add(lbSenha);
		
		JLabel lbImg = new JLabel(new ImageIcon("images/reMind1mini.png"));
		lbImg.setSize(lbImg.getPreferredSize());
		lbImg.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 35), (int)Configuracao.ALTURA_TELA_PERCENT * 28);
		this.add(lbImg);
		
		
		JLabel lbimgBorda = new JLabel(new ImageIcon("images/Borda.png"));
		lbimgBorda.setSize(lbimgBorda.getPreferredSize());
		lbimgBorda.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 31), (int)Configuracao.ALTURA_TELA_PERCENT * 22);
		this.add(lbimgBorda);
			
		txLogin.setSize(300, 25);
		txLogin.setLocation((int)(Configuracao.LARGURA_TELA_PERCENT * 45), (int)(Configuracao.ALTURA_TELA_PERCENT * 48.5));
		txLogin.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		txLogin.setForeground(Configuracao.EMERALD);
		txLogin.setBorder(null);
		this.add(txLogin);
		
		txSenha.setSize(300, 25);
		txSenha.setLocation((int)(Configuracao.LARGURA_TELA_PERCENT * 45), (int)(Configuracao.ALTURA_TELA_PERCENT * 53.5));
		txSenha.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		txSenha.setForeground(Configuracao.MIDNIGHT);
		txSenha.setBorder(null);
		this.add(txSenha);
		
		btnEntra.setSize(125, 40);
		btnEntra.setLocation((int)(Configuracao.LARGURA_TELA_PERCENT * 45), (int)Configuracao.ALTURA_TELA_PERCENT * 64);
		btnEntra.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		btnEntra.setForeground(Configuracao.MIDNIGHT);
		btnEntra.setBackground(Configuracao.CLOUDS);
		btnEntra.setBorder(null);
		this.add(btnEntra);
		
		btnCadastrar.setSize(125, 40);
		btnCadastrar.setLocation((int)(Configuracao.LARGURA_TELA_PERCENT * 56), (int)Configuracao.ALTURA_TELA_PERCENT * 64);
		btnCadastrar.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		btnCadastrar.setForeground(Configuracao.MIDNIGHT);
		btnCadastrar.setBackground(Configuracao.CLOUDS);
		btnCadastrar.setBorder(null);
		this.add(btnCadastrar);
		
		
		btnEntra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// VERIFICAÇÃO DE LOGIN
				if(txLogin.getText().equals("admin")  && txSenha.getText().equals("admin")){
					Configuracao.agenda = new Agenda();
					frame.dispose();
				} 
				else
					JOptionPane.showMessageDialog(null, txLogin.getText() + "   " + txSenha.getText());
			}
		});
		
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				new TelaCadastro();
			}
		});
		fundo.setBackground(new Color(61,186,158));
		fundo.setBounds(this.getBounds());
		this.add(fundo);
		
		this.setVisible(true);
	}
}