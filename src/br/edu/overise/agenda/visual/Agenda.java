package br.edu.overise.agenda.visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.ControleExibicao;
import br.edu.overise.agenda.controle.Visual;
import br.edu.overise.agenda.modelo.desktop.BotaoSlide;
import br.edu.overise.agenda.modelo.desktop.Dia;
import br.edu.overise.agenda.modelo.desktop.Exibivel;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.modelo.geral.Temporizador;

/**
 * TODO OLHA AQUI FDP.
 * ESSA MERDA TÁ BUGANDO: QUANDO VC TROCA DA VISUALIZAÇÃO DE ANO PRA MÊS, ELE PARA DE ALTERNAR ENTRE OS MESES, I.E., VISUALMENTE FALANDO,
 * PQ TODA A LÓGICA DE TROCA TÁ INDO, ELE SIMPLESMENTE NN REPINTA A TELA NAS TROCAS ENTRE MESES DPS Q VC MUDA DE VISUALIZAÇÃO, NÃO ME 
 * PERGUNTE PQ. FOCA NOS DAOS E FODA-SE ISSO POR ENQUANTO. MENTIRA, VC TEM DE RESOLVER ISSO SENÃO TÁ FERRADO.
 */

/**
 * Classe principal do sistema, responsável por juntar todas as suas "partes" e promover a interação com o usuário.
 * 
 * @author Overise
 * @see Hover
 * @see MenuLateral
 * @see PainelPerfil
 * @see PainelConfig
 */
public class Agenda extends JFrame implements ActionListener{
	private static final long serialVersionUID = -1227252571953124914L;
	
	/**
	 * Mês atual segundo <tt>Configuracao.DATA_MES</tt>
	 * @see Configuracao#DATA_MES
	 */
	static int MES_ATUAL = Configuracao.DATA_MES;

	/**
	 * Ano atual segundo <tt>Configuracao.DATA_ANO</tt>
	 * @see Configuracao#DATA_ANO
	 */
	static int ANO_ATUAL = Configuracao.DATA_ANO;
	
	static IPainel PAINEL_ATUAL;
	static Visual VISUAL = new Visual();
	static Timer TIMER_ENTRADA = new Timer();
	static Timer TIMER_SAIDA = new Timer();
	static Timer FADE = new Timer();
	// TODO COMO RESOLVER ESSA PORRA: EXTENDE TIMER, EXTENDE TIMERTASK, CHECA O TÉRMINO DO TIMER PRA ACABAR COM ESSE BUG VISUAL ¬¬
	
	static TimerTask TT = null; // TODO RESOLVE ESSA PORRA² DE BUG VISUAL PELAMOR DE DEUS

	static JPanel DISPLAY = new JPanel();
	static Hover HOVER = new Hover();
	static MenuLateral MENU = new MenuLateral();
	static BarraSuperior BARRA = new BarraSuperior();
	static PainelPerfil PERFIL = new PainelPerfil();
	static PainelNotificacoes NOTIF = new PainelNotificacoes();
	static PainelCores CORES = new PainelCores();
	
//	static JScrollPane SP = new JScrollPane(CORES, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	static PainelConfig CONFIG = new PainelConfig();
	static PainelTestes TESTES = new PainelTestes();
	static PainelDia DIA = new PainelDia();
	static PainelCalendario CALENDARIO = new PainelCalendario();
	
//	static JComponent[] TODOS_OS_PAINEIS = {
//		DISPLAY, HOVER, MENU, BARRA, PERFIL, NOTIF, CORES, CONFIG, TESTES, DIA, CALENDARIO
//	};

	/**
	 * <tt>ArrayList</tt> de <tt>Dia</tt> usada para mostrar os dias do mes atual.
	 * @see ArrayList
	 * @see Dia
	 * @see Agenda#mostrarMes()
	 */
	static ArrayList<Exibivel> exibicaoAtual;
	
	JFrame frame = this;
	public static Agenda frameS;
	static BotaoSlide bsNext, bsPrev;
	static JLabel lbTexto = new JLabel();
	
	public Agenda(){
		frameS = this;
		
		this.setSize((int)Configuracao.LARGURA_TELA, (int)Configuracao.ALTURA_TELA);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(Configuracao.SILVER); // TODO NN ROLA ¬¬
		
		this.add(HOVER);
		
		this.add(MENU);
		
		PERFIL.setBackground(Configuracao.COR_ATUAL);
		this.add(PERFIL);
		CONFIG.setBackground(Configuracao.COR_ATUAL);
		this.add(CONFIG);
		TESTES.setBackground(Configuracao.COR_ATUAL);
		this.add(TESTES);
		NOTIF.setBackground(Configuracao.COR_ATUAL);
		this.add(NOTIF);
		
//		TODO OLHA. O SCROLLPANE EH TIPO UM JPANEL. VC INSERE ELE, NN O PANEL. ESSE É O PROBLEMA. Agenda.abrir() NN DEIXA ¬¬ RESOLVE.
//		SP.setSize(CORES.getWidth(), CORES.getHeight() - 20);
//		SP.setLocation(600, 0);
//		SP.setWheelScrollingEnabled(true);
//		SP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		SP.setBorder(null);
//		//SP.setVisible(false);
//		this.add(SP);

		CORES.setBackground(Configuracao.COR_ATUAL);
		CORES.colorir();
		this.add(CORES);
		CALENDARIO.setBackground(Configuracao.COR_ATUAL);
		this.add(CALENDARIO);
		this.add(DIA);
		

		bsNext = new BotaoSlide("NEXT", false);
		bsNext.addActionListener(this);
		bsPrev = new BotaoSlide("PREV", true);
		bsPrev.addActionListener(this);
		this.add(bsPrev);
		this.add(bsNext);
		
		this.add(BARRA);

		DISPLAY.setLocation((int)Configuracao.X, 0);
		DISPLAY.setSize((int)(Configuracao.LARGURA_TELA - Configuracao.X) + 1, (int)Configuracao.ALTURA_TELA);
		DISPLAY.setBackground(Configuracao.CLOUDS);
		DISPLAY.setLayout(null);
		this.add(DISPLAY);

		// TODO FAZER ISSO COM O BANCO DE DADOS (Repositorio.java)
		switch(Configuracao.MODO_DE_EXIBICAO_ATUAL){
		case Configuracao.VISUALIZACAO_POR_ANO:
			visualizar(ANO_ATUAL);
			break;
		case Configuracao.VISUALIZACAO_POR_MES:
			visualizar(MES_ATUAL, ANO_ATUAL);
			break;
		case Configuracao.VISUALIZACAO_POR_DEC: break;
		case Configuracao.VISUALIZACAO_POR_DIA: break;
		}
//		mudarVisualizacao(Configuracao.MODO_DE_EXIBICAO_ATUAL);
		
		BARRA.setarAtualmente(getMesAtual());
		
		this.setVisible(true);
	}
	
	/**
	 * Função responsável por "abrir" um <tt>IPainel</tt> na <tt>Agenda</tt>, i.e., exibí-lo se valendo de uma animação de <tt>slide-in</tt>.
	 * @param pane <tt>IPainel</tt> a ser aberto pela <tt>Agenda</tt>
	 * @see IPainel
	 * @see Agenda
	 * @see Visual#slideX(IPainel, int, int)
	 */
	public static void abrir(IPainel pane){
		int direcao = 0;
		if(Agenda.PAINEL_ATUAL != null){
			if(Agenda.PAINEL_ATUAL.transition){
				direcao = Agenda.PAINEL_ATUAL.invertido? Visual.NEGATIVO : Visual.POSITIVO;
				TIMER_SAIDA.scheduleAtFixedRate(VISUAL.slideX(Agenda.PAINEL_ATUAL, (int)Agenda.PAINEL_ATUAL.HIDDEN_X, direcao, false), 0, 1);
			}
			else Agenda.PAINEL_ATUAL.setVisible(false);
		}
		Agenda.PAINEL_ATUAL = pane;
		Agenda.VISUAL.pane = pane;
		if(pane.transition){
			direcao = Agenda.PAINEL_ATUAL.invertido? Visual.POSITIVO : Visual.NEGATIVO;
			TIMER_ENTRADA.scheduleAtFixedRate(VISUAL.slideX(Agenda.PAINEL_ATUAL, (int)MenuLateral.LARGURA, direcao, true), 0, 1);
		}
		else pane.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bsNext){
			if(Configuracao.MODO_DE_EXIBICAO_ATUAL == Configuracao.VISUALIZACAO_POR_MES){
				incrMes();
				visualizar(MES_ATUAL, ANO_ATUAL);
			}
			else if(Configuracao.MODO_DE_EXIBICAO_ATUAL == Configuracao.VISUALIZACAO_POR_ANO)
				visualizar(++ANO_ATUAL);
		}
		else if(e.getSource() == bsPrev){
			if(Configuracao.MODO_DE_EXIBICAO_ATUAL == Configuracao.VISUALIZACAO_POR_MES){
				decrMes();
				visualizar(MES_ATUAL, ANO_ATUAL);
			}
			else if(Configuracao.MODO_DE_EXIBICAO_ATUAL == Configuracao.VISUALIZACAO_POR_ANO)
				visualizar(--ANO_ATUAL);
		}
	}
	
	/**
	 * Função responsável por limpar a <tt>Agenda</tt> atual dos <tt>Dia</tt>s exibidos atualmente.
	 * @see Agenda#dias
	 * @see Dia
	 */
	private void clean(){
		for(Exibivel p : exibicaoAtual) DISPLAY.remove((Component) p);
	}
	
	/**
	 * Função responsável por adicionar <tt>Dias</tt> a <tt>Agenda</tt> e ao <tt>Agenda.dias</tt> atuais.
	 * @param mes O número do mês a ser mostrado
	 * @param ano O ano correspondente ao mês a ser mostrado
	 * @see Agenda#dias
	 * @see Agenda
	 * @see Dia
	 * @see ControleDia#gerarMes(int, int)
	 */
	public void visualizar(int mes, int ano){
		Agenda.MES_ATUAL = mes;
		Agenda.ANO_ATUAL = ano;
		if(exibicaoAtual != null){
			clean();
			exibicaoAtual = null;
		}
		
		exibicaoAtual = ControleExibicao.gerarMes(mes, ano);
		for(Exibivel d : exibicaoAtual)
			DISPLAY.add((Component) d);

		frame.repaint();
		mostrarMes();
	}
	
	public void visualizar(int ano){
		Agenda.ANO_ATUAL = ano;
		if(exibicaoAtual != null){
			clean();
			exibicaoAtual = null;
		}
		
		exibicaoAtual = ControleExibicao.gerarAno(ano);
		for(Exibivel d : exibicaoAtual)
			DISPLAY.add((Component) d);
		
		frame.repaint();
//		mostrarAno();
	}

	/**
	 * Método responsável por incrementar o mês exibido baseando-se em <tt>Agenda.MES_ATUAL</tt>
	 * @see Agenda#MES_ATUAL
	 */
	private void incrMes(){
		if(MES_ATUAL == Month.DECEMBER.getValue()){
			MES_ATUAL = Month.JANUARY.getValue();
			ANO_ATUAL++;
		}
		else if(MES_ATUAL == Month.JANUARY.getValue()) MES_ATUAL = Month.FEBRUARY.getValue();
		else if(MES_ATUAL == Month.FEBRUARY.getValue()) MES_ATUAL = Month.MARCH.getValue();
		else if(MES_ATUAL == Month.MARCH.getValue()) MES_ATUAL = Month.APRIL.getValue();
		else if(MES_ATUAL == Month.APRIL.getValue()) MES_ATUAL = Month.MAY.getValue();
		else if(MES_ATUAL == Month.MAY.getValue()) MES_ATUAL = Month.JUNE.getValue();
		else if(MES_ATUAL == Month.JUNE.getValue()) MES_ATUAL = Month.JULY.getValue();
		else if(MES_ATUAL == Month.JULY.getValue()) MES_ATUAL = Month.AUGUST.getValue();
		else if(MES_ATUAL == Month.AUGUST.getValue()) MES_ATUAL = Month.SEPTEMBER.getValue();
		else if(MES_ATUAL == Month.SEPTEMBER.getValue()) MES_ATUAL = Month.OCTOBER.getValue();
		else if(MES_ATUAL == Month.OCTOBER.getValue()) MES_ATUAL = Month.NOVEMBER.getValue();
		else if(MES_ATUAL == Month.NOVEMBER.getValue()) MES_ATUAL = Month.DECEMBER.getValue();
		
		BARRA.setarAtualmente(getMesAtual());
		NOTIF.mostrarMes();
	}

	/**
	 * Método responsável por decrementar o mês exibido baseando-se em <tt>Agenda.MES_ATUAL</tt>
	 * @see Agenda#MES_ATUAL
	 */
	private void decrMes(){
		if(MES_ATUAL == Month.JANUARY.getValue()){
			MES_ATUAL = Month.DECEMBER.getValue();
			ANO_ATUAL--;
		}
		else if(MES_ATUAL == Month.DECEMBER.getValue()) MES_ATUAL = Month.NOVEMBER.getValue();
		else if(MES_ATUAL == Month.NOVEMBER.getValue()) MES_ATUAL = Month.OCTOBER.getValue();
		else if(MES_ATUAL == Month.OCTOBER.getValue()) MES_ATUAL = Month.SEPTEMBER.getValue();
		else if(MES_ATUAL == Month.SEPTEMBER.getValue()) MES_ATUAL = Month.AUGUST.getValue();
		else if(MES_ATUAL == Month.AUGUST.getValue()) MES_ATUAL = Month.JULY.getValue();
		else if(MES_ATUAL == Month.JULY.getValue()) MES_ATUAL = Month.JUNE.getValue();
		else if(MES_ATUAL == Month.JUNE.getValue()) MES_ATUAL = Month.MAY.getValue();
		else if(MES_ATUAL == Month.MAY.getValue()) MES_ATUAL = Month.APRIL.getValue();
		else if(MES_ATUAL == Month.APRIL.getValue()) MES_ATUAL = Month.MARCH.getValue();
		else if(MES_ATUAL == Month.MARCH.getValue()) MES_ATUAL = Month.FEBRUARY.getValue();
		else if(MES_ATUAL == Month.FEBRUARY.getValue()) MES_ATUAL = Month.JANUARY.getValue();

		BARRA.setarAtualmente(getMesAtual());
		NOTIF.mostrarMes();
	}

	/**
	 * Método responsável por mostrar o nome do mês e o ano atualmente exibidos (formato: "MES, ANO") durante um dado período de tempo num <tt>JLabel</tt>
	 * @bug Meses mostrados se acavalam ¬¬
	 * @see Agenda#getMes()
	 */
	private void mostrarMes(){
		if(Configuracao.MOSTRAR_MES_ATUAL){
			String t = getMesAtual() + ", " + ANO_ATUAL;
			lbTexto = new JLabel(t);
			lbTexto.setFont(Configuracao.SEGOE_NEGRITO_ABSURDA);
			lbTexto.setSize(lbTexto.getPreferredSize());
			lbTexto.setLocation((int)Configuracao.O_RESTO / 2 - lbTexto.getWidth() / 2, (int)Configuracao.ALTURA_TELA / 2 - lbTexto.getHeight() / 2);
			lbTexto.setForeground(Configuracao.MIDNIGHT);
//			lbTexto.setVisible(true);
			this.add(lbTexto);
			FADE.schedule(VISUAL.stayForDefinite(this, lbTexto), 1500);
//			FADE.scheduleAtFixedRate(VISUAL.stayForTEST(this, lbTexto), 0, 1)
		};
	}
	
	/**
	 * Método responsável pela determinação do nome do mês atual baseando-se em <tt>Agenda.MES_ATUAL</tt>
	 * @see Agenda#MES_ATUAL
	 * @return O nome em PT-BR do mês em questão
	 */
	public String getMesAtual(){
		if(MES_ATUAL == Month.JANUARY.getValue()) return "Janeiro";
		else if(MES_ATUAL == Month.FEBRUARY.getValue()) return "Fevereiro";
		else if(MES_ATUAL == Month.MARCH.getValue()) return "Março";
		else if(MES_ATUAL == Month.APRIL.getValue()) return "Abril";
		else if(MES_ATUAL == Month.MAY.getValue()) return "Maio";
		else if(MES_ATUAL == Month.JUNE.getValue()) return "Junho";
		else if(MES_ATUAL == Month.JULY.getValue()) return "Julho";
		else if(MES_ATUAL == Month.AUGUST.getValue()) return "Agosto";
		else if(MES_ATUAL == Month.SEPTEMBER.getValue()) return "Setembro";
		else if(MES_ATUAL == Month.OCTOBER.getValue()) return "Outubro";
		else if(MES_ATUAL == Month.NOVEMBER.getValue()) return "Novembro";
		else if(MES_ATUAL == Month.DECEMBER.getValue()) return "Dezembro";
		return "";
	}
	
	public static void setData(int mes, int ano){
		Agenda.MES_ATUAL = mes;
		Agenda.ANO_ATUAL = ano;
	}
	
	public static void restartInterface(){
		DISPLAY = new JPanel();
		HOVER = new Hover();
		MENU = new MenuLateral();
		BARRA = new BarraSuperior();
		PERFIL = new PainelPerfil();
		NOTIF = new PainelNotificacoes();
		CORES = new PainelCores();
		
		CONFIG = new PainelConfig();
		TESTES = new PainelTestes();
		DIA = new PainelDia();
		CALENDARIO = new PainelCalendario();
		
		frameS.repaint();
	}
	
	public static void mudarVisualizacao(int nova){
		// TODO TERMINAR ESSA DROGA
		Configuracao.MODO_DE_EXIBICAO_ATUAL = nova;
		switch(nova){
		case Configuracao.VISUALIZACAO_POR_ANO:
			frameS.remove(bsNext);
			frameS.remove(bsPrev);
			frameS.visualizar(ANO_ATUAL);
			break;
		case Configuracao.VISUALIZACAO_POR_MES:
			frameS.visualizar(MES_ATUAL, ANO_ATUAL);
			break;
		}
	}
}
