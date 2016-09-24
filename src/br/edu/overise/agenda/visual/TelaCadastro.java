package br.edu.overise.agenda.visual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import br.edu.overise.agenda.controle.Configuracao;

public class TelaCadastro extends JFrame{
	static JTextField txNome = new JTextField();
	static JTextField txLogin = new JTextField();
	static JPasswordField txSenha = new JPasswordField();
	static JPasswordField txConSenha = new JPasswordField();
	
	static JLabel lbNome = new JLabel("Nome:");
	static JLabel lbLogin = new JLabel("Login:");
	static JLabel lbSenha = new JLabel("Senha:");
	static JLabel lbConSenha = new JLabel("Confirmação:");
	
	static JButton btnConcluir = new JButton("Concluir");
	static JPanel fundo = new JPanel();
	public TelaCadastro(){
		JFrame frame = this;
		
		JLabel lbImg = new JLabel(new ImageIcon("images/reMind1mini.png"));
		lbImg.setSize(lbImg.getPreferredSize());
		lbImg.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 32),(int) Configuracao.ALTURA_TELA_PERCENT * 22);
		this.add(lbImg);
		
		JButton btVoltar = new JButton(new ImageIcon("images/voltar.png"));
		btVoltar.setSize(50 , 50);
		btVoltar.setLocation((int) (0),(int) 0);
		btVoltar.setBackground(new Color(239,50,50));
		btVoltar.setBorder(null);
		btVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
				new TelaLogin();
			}
		});
		
		this.add(btVoltar);
		
		this.setSize((int)Configuracao.LARGURA_TELA, (int)Configuracao.ALTURA_TELA);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocation(0, 0);
		this.setLayout(null);
		
		txNome.setSize(300, 25);
		txNome.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 43),(int) (Configuracao.ALTURA_TELA_PERCENT * 42));
		txNome.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		txNome.setForeground(Configuracao.EMERALD);
		txNome.setBorder(null);
		this.add(txNome);
		
		
		txLogin.setSize(300, 25);
		txLogin.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 43),(int) (Configuracao.ALTURA_TELA_PERCENT * 47));
		txLogin.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		txLogin.setForeground(Configuracao.EMERALD);
		txLogin.setBorder(null);
		this.add(txLogin);
		
		
		txSenha.setSize(300, 25);
		txSenha.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 43),(int) (Configuracao.ALTURA_TELA_PERCENT * 52));
		txSenha.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		txSenha.setForeground(Configuracao.EMERALD);
		txSenha.setBorder(null);
		this.add(txSenha);
		
		txConSenha.setSize(300, 25);
		txConSenha.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 43),(int) (Configuracao.ALTURA_TELA_PERCENT * 57));
		txConSenha.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		txConSenha.setForeground(Configuracao.EMERALD);
		txConSenha.setBorder(null);
		this.add(txConSenha);
		
		btnConcluir.setSize(125, 40);
		btnConcluir.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 49),(int) (Configuracao.ALTURA_TELA_PERCENT * 62));
		btnConcluir.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		btnConcluir.setBackground(Configuracao.CLOUDS);
		btnConcluir.setBorder(null);
		btnConcluir.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txSenha.getText().equals(txConSenha.getText()) && txNome.getText() != null && txLogin.getText() != "" && txSenha.getText() != "" && txConSenha.getText() != "" ){
					frame.dispose();
					new TelaLogin();
				} else {
					lbConSenha.setForeground(Configuracao.CLOUDS);
					txConSenha.setText("");
				}
				System.out.println();
			}
		});
		this.add(btnConcluir);
		
		lbNome.setSize(300, 25);
		lbNome.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 37),(int) (Configuracao.ALTURA_TELA_PERCENT * 42));
		lbNome.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbNome.setForeground(Configuracao.CLOUDS);
		lbNome.setBorder(null);
		this.add(lbNome);
		
		lbLogin.setSize(300, 25);
		lbLogin.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 37),(int) (Configuracao.ALTURA_TELA_PERCENT * 47));
		lbLogin.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbLogin.setForeground(Configuracao.CLOUDS);
		lbLogin.setBorder(null);
		this.add(lbLogin);
		
		lbSenha.setSize(300, 25);
		lbSenha.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 37),(int) (Configuracao.ALTURA_TELA_PERCENT * 52));
		lbSenha.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbSenha.setForeground(Configuracao.CLOUDS);
		lbSenha.setBorder(null);
		this.add(lbSenha);
		
		lbConSenha.setSize(300, 25);
		lbConSenha.setLocation((int) (Configuracao.LARGURA_TELA_PERCENT * 32),(int) (Configuracao.ALTURA_TELA_PERCENT * 57));
		lbConSenha.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbConSenha.setForeground(Configuracao.CLOUDS);
		lbConSenha.setBorder(null);
		this.add(lbConSenha);
		
		fundo.setBackground(new Color(239,50,50));
		fundo.setBounds(this.getBounds());
		this.add(fundo);
		
		this.setVisible(true);
	}
}
