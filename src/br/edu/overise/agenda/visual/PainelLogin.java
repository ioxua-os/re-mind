package br.edu.overise.agenda.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.derby.impl.sql.execute.ForeignKeyRIChecker;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.dao.DAOUsuario;
import br.edu.overise.agenda.controle.dao.Repositorio;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.MiniPerfil;
import br.edu.overise.agenda.modelo.geral.Usuario;

public class PainelLogin extends JFrame implements ActionListener {
	static final int LARGURA = (int)Configuracao.LARGURA_TELA;
	static final int ALTURA = (int)Configuracao.ALTURA_TELA;
	static final int HALF_LARGURA = (int)(LARGURA / 2);
	
	static final int LINHA_SUPERIOR = (int)(ALTURA / 2 - MiniPerfil.ALTURA - Configuracao.ALTURA_TELA_PERCENT + 30);
	static final int LINHA_INFERIOR = (int)(ALTURA / 2 + Configuracao.ALTURA_TELA_PERCENT + 30);
	static final int LINHA_CENTRAL = (int)(ALTURA / 2 - MiniPerfil.LARGURA / 2 + 30);

	static final int COLUNA_CENTRAL = HALF_LARGURA - MiniPerfil.LARGURA / 2;
	static final int QUATRO_COLUNAS_PRIMEIRA = HALF_LARGURA - (MiniPerfil.LARGURA * 2) - (int)(Configuracao.LARGURA_TELA_PERCENT * 1.35);
	static final int QUATRO_COLUNAS_SEGUNDA = HALF_LARGURA - MiniPerfil.LARGURA - (int)(Configuracao.LARGURA_TELA_PERCENT / 2);
	static final int QUATRO_COLUNAS_TERCEIRA = HALF_LARGURA + (int)(Configuracao.LARGURA_TELA_PERCENT / 2);
	static final int QUATRO_COLUNAS_QUARTA = HALF_LARGURA + MiniPerfil.LARGURA + (int)(Configuracao.LARGURA_TELA_PERCENT * 1.35);
	static final int TRES_COLUNAS_PRIMEIRA = COLUNA_CENTRAL - MiniPerfil.LARGURA - (int)Configuracao.LARGURA_TELA_PERCENT;
	static final int TRES_COLUNAS_SEGUNDA = COLUNA_CENTRAL;
	static final int TRES_COLUNAS_TERCEIRA = COLUNA_CENTRAL + MiniPerfil.LARGURA + (int)Configuracao.LARGURA_TELA_PERCENT;
	static final int DUAS_COLUNAS_PRIMEIRA = QUATRO_COLUNAS_SEGUNDA;
	static final int DUAS_COLUNAS_SEGUNDA = QUATRO_COLUNAS_TERCEIRA;
	
	static final int[] AUX_LINHA_DOIS = {
		DUAS_COLUNAS_PRIMEIRA, DUAS_COLUNAS_SEGUNDA
	};
	static final int[] AUX_LINHA_TRES = {
		TRES_COLUNAS_PRIMEIRA, TRES_COLUNAS_SEGUNDA, TRES_COLUNAS_TERCEIRA
	};
	static final int[] AUX_LINHA_QUATRO = {
		QUATRO_COLUNAS_PRIMEIRA, QUATRO_COLUNAS_SEGUNDA, QUATRO_COLUNAS_TERCEIRA, QUATRO_COLUNAS_QUARTA
	};
	
	BotaoOpcao btTelaCadastro, btRetornar, btCadastrar;
	JTextField txLogin;
	JPasswordField txSenhaA, txSenhaB;
	JLabel lbMensagem, imgSelecionada;
	
	List<JLabel> imagens = new ArrayList<>();
	List<MiniPerfil> perfis = new ArrayList<>();
	
	JPanel login, cadastro, senha;
	
	public PainelLogin(){
		
		this.setSize(LARGURA, ALTURA);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);

		login = new JPanel();
		login.setLayout(null);
		login.setBackground(Configuracao.BELIZE_HOLE);
		login.setBounds(this.getBounds());

		cadastro = new JPanel();
		cadastro.setLayout(null);
		cadastro.setBackground(Configuracao.BELIZE_HOLE);
		cadastro.setBounds(this.getBounds());

		senha = new JPanel();
		senha.setLayout(null);
		senha.setBackground(Configuracao.BELIZE_HOLE);
		senha.setBounds(this.getBounds());
		
		try {
			criarLogin();
			this.add(login);
			
			criarCadastro();
			this.add(cadastro);
			
			this.add(senha);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.setVisible(true);
		}
		
	}
	
	
	public void criarTelaSenha(Usuario u) {
		JLabel lb = new JLabel("Qual sua senha?"); 
		lb.setFont(Configuracao.SEGOE_NEGRITO_ABSURDA);
		lb.setForeground(Configuracao.CLOUDS);
		lb.setSize(lb.getPreferredSize());
		lb.setLocation(login.getWidth() / 2 - lb.getWidth() / 2, (int)Configuracao.ALTURA_TELA_PERCENT * 10);
		senha.add(lb);
		
		ImageIcon i = Configuracao.getImagemPorNome(u.getAvatar());
		lb = new JLabel(new ImageIcon(i.getImage().getScaledInstance((int)Configuracao.QUAD_LARGURA * 2, (int)Configuracao.QUAD_ALTURA * 2, Image.SCALE_DEFAULT)));
		lb.setSize((int)Configuracao.QUAD_LARGURA * 2, (int)Configuracao.QUAD_ALTURA * 2);
		lb.setLocation(senha.getWidth() / 2 - lb.getWidth() / 2, senha.getHeight() / 2 - lb.getHeight() / 2 - (int)Configuracao.FIVE_ALTURA_TELA_PERCENT);
		senha.add(lb);
		
		JPasswordField txPass = new JPasswordField();
		txPass.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
		txPass.setSize((int)Configuracao.FIVE_LARGURA_TELA_PERCENT * 5, (int)Configuracao.FIVE_ALTURA_TELA_PERCENT);
		txPass.setLocation(senha.getWidth() / 2 - txPass.getWidth() / 2, lb.getY() + lb.getHeight() + 15);
		senha.add(txPass);
		
		BotaoOpcao btEntrar = new BotaoOpcao("Entrar");
		btEntrar.setSize(btEntrar.getSlimSize());
		btEntrar.setLocation(senha.getWidth() / 2 - btEntrar.getWidth() / 2, txPass.getY() + txPass.getHeight() + 15);
		btEntrar.setCores(new Color[]{ Configuracao.CLOUDS, Configuracao.SUN_FLOWER, Configuracao.ORANGE });
		
		JFrame frame = this;
		btEntrar.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				txPass.setBorder(null);
				if(Arrays.equals(txPass.getPassword(), new char[0]))
					txPass.setBorder(BorderFactory.createLineBorder(Configuracao.ALIZARIN, 4));
				else if(u.getSenha().equals( String.valueOf(txPass.getPassword()) )) {
					Configuracao.USER_LOGADO = u;
					Configuracao.agenda = new Agenda();
					frame.dispose();
				}
			}
		});
		senha.add(btEntrar);

		btRetornar = new BotaoOpcao("Voltar");
		btRetornar.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
		btRetornar.setSize(btRetornar.getSlimSize());
		btRetornar.setLocation(5, 5);
		btRetornar.setCores(new Color[]{ Configuracao.CLOUDS, Configuracao.SUN_FLOWER, Configuracao.ORANGE });
		btRetornar.addActionListener(this);
		senha.add(btRetornar);

		BotaoOpcao btSair = new BotaoOpcao("SAIR");
		btSair.setSize((int)( Configuracao.LARGURA_TELA_PERCENT * 3.5 ), (int)Configuracao.FIVE_ALTURA_TELA_PERCENT);
		btSair.setLocation(this.getWidth() - btSair.getWidth() - 20, 5);
		btSair.setCores(new Color[]{ Configuracao.SILVER, Configuracao.POMEGRANATE, Configuracao.ALIZARIN });
		btSair.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		senha.add(btSair);
		
		cadastro.setVisible(false);
		login.setVisible(false);
		senha.setVisible(true);
	}
	
	private void criarCadastro() {
		btRetornar = new BotaoOpcao("Voltar");
		btRetornar.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
		btRetornar.setSize(btRetornar.getSlimSize());
		btRetornar.setLocation(5, 5);
		btRetornar.setCores(new Color[]{ Configuracao.CLOUDS, Configuracao.SUN_FLOWER, Configuracao.ORANGE });
		btRetornar.addActionListener(this);
		cadastro.add(btRetornar);

		Dimension d = new Dimension((int)Configuracao.FIVE_LARGURA_TELA_PERCENT * 5, (int)Configuracao.FIVE_ALTURA_TELA_PERCENT); 

		txSenhaA = new JPasswordField();
		txSenhaA.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
		txSenhaA.setSize(d);
		txSenhaA.setLocation(cadastro.getWidth() / 2 - txSenhaA.getWidth() / 2, cadastro.getHeight() / 2 - txSenhaA.getHeight() / 2 - (int)Configuracao.FIVE_ALTURA_TELA_PERCENT * 5);
		cadastro.add(txSenhaA);
		
		txLogin = new JTextField(); 
		txLogin.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
		txLogin.setBorder(BorderFactory.createLineBorder(Configuracao.CLOUDS, 3));
		txLogin.setSize(d);
		txLogin.setLocation(txSenhaA.getX(), txSenhaA.getY() - txLogin.getHeight() - 15);
		cadastro.add(txLogin);

		txSenhaB = new JPasswordField();
		txSenhaB.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
		txSenhaB.setSize(d);
		txSenhaB.setLocation(txSenhaA.getX(), txSenhaA.getY() + txSenhaA.getHeight() + 15);
		cadastro.add(txSenhaB);

		criarImagens(cadastro.getWidth() / 2 - ( (txSenhaA.getWidth() / 3) * 5 + 50 ) / 2, txSenhaB.getY() + txSenhaB.getHeight() + 15, txSenhaA.getWidth() / 3);
	
		btCadastrar = new BotaoOpcao("Cadastrar");
		btCadastrar.setSize(btCadastrar.getSlimSize());
		btCadastrar.setLocation(cadastro.getWidth() / 2 - btCadastrar.getWidth() / 2, txSenhaB.getY() + txSenhaB.getHeight() + 15 + txSenhaA.getWidth() + 30);
		btCadastrar.addActionListener(this);
		btCadastrar.setCores(new Color[]{ Configuracao.CLOUDS, Configuracao.SUN_FLOWER, Configuracao.ORANGE });
		cadastro.add(btCadastrar);
		
		lbMensagem = new JLabel("");
		lbMensagem.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbMensagem.setForeground(Configuracao.ORANGE);
		lbMensagem.setSize(lbMensagem.getPreferredSize());
		lbMensagem.setLocation(txSenhaA.getX() + txSenhaA.getWidth() + 15, txSenhaA.getHeight() / 2 + txSenhaA.getY() - lbMensagem.getHeight() / 2);
		cadastro.add(lbMensagem);

		BotaoOpcao btSair = new BotaoOpcao("SAIR");
		btSair.setSize((int)( Configuracao.LARGURA_TELA_PERCENT * 3.5 ), (int)Configuracao.FIVE_ALTURA_TELA_PERCENT);
		btSair.setLocation(this.getWidth() - btSair.getWidth() - 20, 5);
		btSair.setCores(new Color[]{ Configuracao.SILVER, Configuracao.POMEGRANATE, Configuracao.ALIZARIN });
		btSair.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cadastro.add(btSair);
		
		cadastro.setVisible(false);
	}
	
	private void criarImagens(int x, int y, int s) {
		int stepx = s + 10,
			stepy = s + 10;
			
		for(int i = 0, k = 0; k < Configuracao.avatares.size(); i++) {
			for(int j = 0; j < 5; j++, k++) {
				JLabel lb = new JLabel(scaleIt(Configuracao.avatares.get(k), s, s));
				lb.setSize(s, s);
				lb.setLocation(x + j * stepx, y + i * stepy);
				lb.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						imagens.stream().forEach( (l) -> l.setBorder(null) );
						lb.setBorder(BorderFactory.createLineBorder(Configuracao.ORANGE, 4));
						imgSelecionada = lb;
					}
				});
				if(k == 7) {
					lb.setBorder(BorderFactory.createLineBorder(Configuracao.ORANGE, 4));
					imgSelecionada = lb;
				}
				cadastro.add(lb);
				imagens.add(lb);
			}
		}
	}
	
	private void criarLogin() throws SQLException {
		List<Usuario> users = DAOUsuario.getTodosUsuarios();
		if(users.size() > 0) {
			JLabel lb = new JLabel("Logue-se!"); // Insira todos os seus dados que vc anteriormente cadastrou, senha e usuário, para que nosso sistema verifique a veracidade da combinação e lhe permita penetrar no nosso aplicativo para que vc possa com toda a felicidade usufruir dos recursos que forem desenvolvidos pela equipe do Overise :D
			lb.setFont(Configuracao.SEGOE_NEGRITO_ABSURDA);
			lb.setForeground(Configuracao.CLOUDS);
			lb.setSize(lb.getPreferredSize());
			lb.setLocation(login.getWidth() / 2 - lb.getWidth() / 2, (int)Configuracao.ALTURA_TELA_PERCENT * 4);
			login.add(lb);

			addUsuarios(users);
			
			btTelaCadastro = new BotaoOpcao("Ou cadastre-se!");
			Dimension d = btTelaCadastro.getPreferredSize();
			d.setSize(d.getWidth() - 20, d.getHeight() - 16);
			btTelaCadastro.setSize(d);
			btTelaCadastro.setLocation(login.getWidth() / 2 - btTelaCadastro.getWidth() / 2, lb.getHeight() + lb.getY() + 10);
			btTelaCadastro.addActionListener(this);
			btTelaCadastro.setCores(new Color[]{ Configuracao.CLOUDS, Configuracao.ORANGE, Configuracao.SUN_FLOWER });
			login.add(btTelaCadastro);
			
			if(users.size() >= 8)
				btTelaCadastro.setVisible(false);

			BotaoOpcao btSair = new BotaoOpcao("SAIR");
			btSair.setSize((int)( Configuracao.LARGURA_TELA_PERCENT * 3.5 ), (int)Configuracao.FIVE_ALTURA_TELA_PERCENT);
			btSair.setLocation(this.getWidth() - btSair.getWidth() - 20, 5);
			btSair.setCores(new Color[]{ Configuracao.SILVER, Configuracao.POMEGRANATE, Configuracao.ALIZARIN });
			btSair.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			login.add(btSair);
		}
		else {
			JLabel lb = new JLabel("Opa! Parece que não há nada aqui :(");
			lb.setFont(Configuracao.SEGOE_REGULAR_GRANDE);
			lb.setForeground(Configuracao.CLOUDS);
			lb.setSize(lb.getPreferredSize());
			lb.setLocation(login.getWidth() / 2 - lb.getWidth() / 2, this.getHeight() / 2 - lb.getHeight() / 2 - 50);
			login.add(lb);

			btTelaCadastro = new BotaoOpcao("Cadastre-se!");
			Dimension d = btTelaCadastro.getPreferredSize();
			d.setSize(d.getWidth() - 20, d.getHeight() - 16);
			btTelaCadastro.setSize(d);
			btTelaCadastro.setLocation(login.getWidth() / 2 - btTelaCadastro.getWidth() / 2, lb.getHeight() + lb.getY() + 10);
			btTelaCadastro.addActionListener(this);
			btTelaCadastro.setCores(new Color[]{ Configuracao.CLOUDS, Configuracao.ORANGE, Configuracao.SUN_FLOWER });
			login.add(btTelaCadastro);
		}
	}
	
	private ImageIcon scaleIt(ImageIcon i, int w, int h) {
		return new ImageIcon(i.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
	}
	
	private void addUsuarios(List<Usuario> users){
		perfis.stream().forEach( m -> login.remove(m) );
		
		int q = users.size();
		if(q <= 4)
			criarLinha(users, LINHA_CENTRAL);
		else{
			criarLinha(users.subList(0, 4), LINHA_SUPERIOR);
			criarLinha(users.subList(4, q), LINHA_INFERIOR);
		}
	}
	
	private void criarLinha(List<Usuario> users, int alt){
		MiniPerfil mp;
		if(users.size() == 1){
			mp = new MiniPerfil(users.get(0), this);
			mp.setLocation(COLUNA_CENTRAL, alt);
			login.add(mp);
			perfis.add(mp);
		}
		else{
			int[] aux = users.size() == 2? AUX_LINHA_DOIS : users.size() == 3? AUX_LINHA_TRES : AUX_LINHA_QUATRO;
			for(Usuario u : users){
				mp = new MiniPerfil(u, this);
				mp.setLocation(aux[users.indexOf(u)], alt);
				login.add(mp);
				perfis.add(mp);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btTelaCadastro) {
			// TODO CADASTRAR
			login.setVisible(false);
			cadastro.setVisible(true);
		}
		else if(e.getSource() == btRetornar) {
			try {
				cadastro.setVisible(false);
				login.setVisible(true);
				senha.setVisible(false);
				addUsuarios(DAOUsuario.getTodosUsuarios());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == btCadastrar) {
			txLogin.setBorder(null);
			txSenhaA.setBorder(null);
			txSenhaB.setBorder(null);
			lbMensagem.setText("");
			Usuario u = new Usuario();
			if(txLogin.getText().equals("")) {
				lbMensagem.setText("Insira um nome de usuário");
				lbMensagem.setSize(lbMensagem.getPreferredSize());
				lbMensagem.setLocation(txSenhaA.getX() + txSenhaA.getWidth() + 15, txSenhaA.getHeight() / 2 + txSenhaA.getY() - lbMensagem.getHeight() / 2);
				txLogin.setBorder(BorderFactory.createLineBorder(Configuracao.ALIZARIN, 4));
			}
			else {
				u.setNome(txLogin.getText());
				if(Arrays.equals(txSenhaA.getPassword(), new char[0])) {
					lbMensagem.setText("Insira uma senha");
					lbMensagem.setSize(lbMensagem.getPreferredSize());
					lbMensagem.setLocation(txSenhaA.getX() + txSenhaA.getWidth() + 15, txSenhaA.getHeight() / 2 + txSenhaA.getY() - lbMensagem.getHeight() / 2);
					txSenhaA.setBorder(BorderFactory.createLineBorder(Configuracao.ALIZARIN, 4));
				}
				else if(Arrays.equals(txSenhaA.getPassword(), txSenhaB.getPassword())) {
					try {
						u.setSenha(String.valueOf(txSenhaA.getPassword()));
						for(int i = 0; i < Configuracao.avatares.size(); i++)
							if(imagens.get(i).getBorder() != null)
								u.setAvatar(Configuracao.getNomeImagem(Configuracao.avatares.get(i)));
						u.setCor(Configuracao.BELIZE_HOLE);
						DAOUsuario.cadastrar(u);
						txLogin.setText("");
						txSenhaA.setText("");
						txSenhaB.setText("");
						cadastro.setVisible(false);
						criarLogin();
						login.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					txSenhaA.setBorder(BorderFactory.createLineBorder(Configuracao.ALIZARIN, 4));
					txSenhaB.setBorder(BorderFactory.createLineBorder(Configuracao.ALIZARIN, 4));
					lbMensagem.setText("As senhas inseridas não coincidem");
					lbMensagem.setSize(lbMensagem.getPreferredSize());
					lbMensagem.setLocation(txSenhaA.getX() + txSenhaA.getWidth() + 15, txSenhaA.getHeight() / 2 + txSenhaA.getY() - lbMensagem.getHeight() / 2);
				}
			}
				
		}
	}
}
