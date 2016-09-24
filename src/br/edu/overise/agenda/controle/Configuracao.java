package br.edu.overise.agenda.controle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.nio.channels.AsynchronousByteChannel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import br.edu.overise.agenda.controle.dao.DAOUsuario;
import br.edu.overise.agenda.controle.dao.Repositorio;
import br.edu.overise.agenda.modelo.geral.Usuario;
import br.edu.overise.agenda.visual.Agenda;
import br.edu.overise.agenda.visual.Splash;
import br.edu.overise.agenda.visual.TelaLogin;

/**
 * Classe que armazena diversos <tt>Double</tt> globais essenciais além de diversos objetos <tt>Font</tt> usados no layout do projeto
 * e a data atual do sistema, guardada dentro de um <tt>Calendar</tt> e também separada em dia, mês e ano (em <tt>Integer</tt>)
 * 
 * @author Overise
 *
 */
public class Configuracao {
	
	public static Agenda agenda;
	/*
	 * VARIÁVEIS DE TESTE:
	 * MOSTRAR_MES_ATUAL - SETA SE O MÊS ATUAL DEVE SER MOSTRADO AO TROCAR A VISÃO.
	 * DEBUG -  MOSTRA AS MENSAGENS USADAS PARA DEGUB AO LONGO DO DESENVOLVIMENTO
	 */

	public static final boolean POPULAR_BANCO = false;
	public static final boolean MOSTRAR_MES_ATUAL = false;
	
	/**
	 * Define se comandos usados no <tt>Debug</tt> do código serão executados.
	 */
	public static final boolean DEBUG = false;
	
	public static Usuario USER_LOGADO;

	public static final Font SEGOE_NEGRITO_MEDIO = new Font("Segoe UI", Font.BOLD, 16);
	public static final Font SEGOE_NEGRITO_GRANDE = new Font("Segoe UI", Font.BOLD, 22);
	public static final Font SEGOE_NEGRITO_GIGANTE = new Font("Segoe UI", Font.BOLD, 30);
	public static final Font SEGOE_NEGRITO_ABSURDA = new Font("Segoe UI", Font.BOLD, 72);
	public static final Font SEGOE_REGULAR_MEDIA = new Font("Segoe UI", Font.PLAIN, 16);
	public static final Font SEGOE_REGULAR_GRANDE = new Font("Segoe UI", Font.PLAIN, 22);
	public static final Font SEGOE_REGULAR_ABSURDA = new Font("Segoe UI", Font.PLAIN, 72);
	
	/**
	 * Fonte usada para símbolos
	 */
	public static final Font SEGOE_SYMBOL = new Font("Segoe UI Symbol", Font.BOLD, 48);
	
	/**
	 * Largura total da tela
	 */
	public static final double LARGURA_TELA = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	/**
	 * Altura total da tela
	 */
	public static final double ALTURA_TELA = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/**
	 * 1% da largura da tela (<tt>LARGURA_TELA</tt> / 100)
	 * 
	 * @see Configuracao#LARGURA_TELA
	 */
	public static final double LARGURA_TELA_PERCENT = LARGURA_TELA / 100;

	/**
	 * 1% da altura da tela (<tt>ALTURA_TELA</tt> / 100)
	 * 
	 * @see Configuracao#ALTURA_TELA
	 */
	public static final double ALTURA_TELA_PERCENT = ALTURA_TELA / 100;

	/**
	 * 5% da largura da tela (<tt>LARGURA_TELA_PERCENT</tt> * 5)
	 * 
	 * @see Configuracao#LARGURA_TELA_PERCENT
	 */
	public static final double FIVE_LARGURA_TELA_PERCENT = LARGURA_TELA_PERCENT * 5;

	/**
	 * 5% da altura da tela (<tt>ALTURA_TELA_PERCENT</tt> * 5)
	 * 
	 * @see Configuracao#ALTURA_TELA_PERCENT
	 */
	public static final double FIVE_ALTURA_TELA_PERCENT = ALTURA_TELA_PERCENT * 5;

	/**
	 * Altura padrão de objetos quadrados no projeto, correspondente a 15% da <tt>ALTURA_TELA</tt>
	 * 
	 * @see Configuracao#ALTURA_TELA_PERCENT
	 * @see Configuracao#FIVE_ALTURA_TELA_PERCENT
	 */
	public static final double QUAD_ALTURA = FIVE_ALTURA_TELA_PERCENT * 3;

	/**
	 * Largura padrão de objetos quadrados no projeto, correspondente a 15% da <tt>LARGURA_TELA</tt>
	 * 
	 * @see Configuracao#LARGURA_TELA_PERCENT
	 * @see Configuracao#FIVE_LARGURA_TELA_PERCENT
	 */
	public static final double QUAD_LARGURA = FIVE_LARGURA_TELA_PERCENT * 1.75;

	/**
	 * Altura padrão para as opções usadas em <tt>MenuContexto</tt>, correspondente a 4 vezes o tamanho de <tt>SEGOE_REGULAR_MEDIA</tt>
	 * 
	 * @see MenuContexto
	 * @see Configuracao#SEGOE_REGULAR_MEDIA
	 */
	public static final double CONTEXT_ALTURA = Configuracao.SEGOE_REGULAR_MEDIA.getSize() * 4;

	/**
	 * Largura padrão para as opções usadas em <tt>MenuContexto</tt>, correspondente a 1.5 vezes o tamanho de <tt>QUAD_ALTURA</tt>
	 * 
	 * @see MenuContexto
	 * @see Configuracao#QUAD_ALTURA
	 */
	public static final double CONTEXT_LARGURA = Configuracao.QUAD_ALTURA * 1.5;

	/**
	 * Correspondente a 9% da <tt>LARGURA_TELA</tt>. Usado primariamente para posicionar objetos <tt>IPainel</tt> de forma a não sobreporem
	 * <tt>MenuLateral</tt>
	 * 
	 * @see Configuracao#LARGURA_TELA
	 * @see IPainel
	 * @see MenuLateral
	 */
	public static final double X = LARGURA_TELA_PERCENT * 9;

	/**
	 * Correspondente à largura de 91% da tela. Usado para posicionar objetos centralizados na tela sem afetar o <tt>MenuLateral</tt>
	 * 
	 * @see MenuLateral
	 */
	public static final double O_RESTO = LARGURA_TELA - X;

	/**
	 * Data atual do computador
	 */
	public static final LocalDate DATA = LocalDate.now();

	/**
	 * Dia atual do computador, segundo <tt>Configuracao.DATA</tt>
	 * [1,31]
	 * @see Configuracao#DATA
	 */
	public static final int DATA_DIA = DATA.getDayOfMonth();

	/**
	 * Mês atual do computador, segundo <tt>Configuracao.DATA</tt>
	 * [1,12]
	 * @see Configuracao#DATA
	 */
	public static final int DATA_MES = DATA.getMonthValue();

	/**
	 * Ano atual do computador, segundo <tt>Configuracao.DATA</tt>
	 * 
	 * @see Configuracao#DATA
	 */
	public static final int DATA_ANO = DATA.getYear();

	/**
	 * Visualização por horas do dia
	 * @see MODO_DE_EXIBICAO_ATUAL
	 */
	public static final int VISUALIZACAO_POR_DIA = 1;

	/**
	 * Visualização por dias do mês
	 * @see Configuracao#MODO_DE_EXIBICAO_ATUAL
	 */
	public static final int VISUALIZACAO_POR_MES = 2;

	/**
	 * Visualização por meses do ano
	 * @see Configuracao#MODO_DE_EXIBICAO_ATUAL
	 */
	public static final int VISUALIZACAO_POR_ANO = 3;

	/**
	 * Visualização por anos da década(?)
	 * @see Configuracao#MODO_DE_EXIBICAO_ATUAL
	 */
	public static final int VISUALIZACAO_POR_DEC = 4;
	
	/**
	 * Modo de exibição atualmente usado pelo sistema.
	 * @see Configuracao#VISUALIZACAO_POR_DIA
	 * @see Configuracao#VISUALIZACAO_POR_MES
	 * @see Configuracao#VISUALIZACAO_POR_ANO
	 * @see Configuracao#VISUALIZACAO_POR_DEC
	 */
	public static int MODO_DE_EXIBICAO_ATUAL = VISUALIZACAO_POR_MES;
	
	public static Map<Color, Color> INTRFC_CORES_COMPLEMENTARES = new HashMap<Color, Color>();
	public static Map<Color, Color> INTRFC_CORES_SUPLEMENTARES = new HashMap<Color, Color>();
	public static List<Color> CORES = new ArrayList<Color>();

	public static final Color CLOUDS = new Color(236, 240, 241);
	public static final Color SILVER = new Color(189, 195, 199);

	public static final Color CONCRETE = new Color(149, 165, 166);
	public static final Color ASBESTAS = new Color(127, 140, 141);
	
	public static final Color ASPHALT = new Color(52, 73, 94);
	public static final Color MIDNIGHT = new Color(44, 62, 80);

	public static final Color EMERALD = new Color(46, 204, 113);
	public static final Color NEPHRITIS = new Color(39, 174, 96);
	
	public static final Color VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO = new Color(30, 240, 62);
	public static final Color ABACATE_BALADEIRO = new Color(36, 227, 70);
	
	public static final Color WATERMELON = new Color(249, 0, 74);

	public static final Color TURQUOISE = new Color(26, 188, 156);
	public static final Color SEA_GREEN = new Color(22, 160, 133);
	
	public static final Color PETER_RIVER = new Color(52, 152, 219);
	public static final Color BELIZE_HOLE = new Color(41, 128, 185);

	public static final Color AMETHYST = new Color(155, 89, 182);
	public static final Color WISTERIA = new Color(142, 68, 173);

	public static final Color ALIZARIN = new Color(231, 76, 60);
	public static final Color POMEGRANATE = new Color(192, 57, 43);
	
	public static final Color CARROT = new Color(230, 126, 34);
	public static final Color PUMPKIN = new Color(211, 84, 0);
	
	public static final Color SUN_FLOWER = new Color(241, 196, 15);
	public static final Color ORANGE = new Color(243, 156, 18);
	
	public static final Color TRANSPARENT = new Color(250, 250, 250, 10);
	
	public static Color COR_ATUAL, COR_ATUAL_COMPLEMENTAR, COR_ATUAL_SUPLEMENTAR;
	
	public static List<ImageIcon> avatares = Arrays.asList(new ImageIcon[]{
			new ImageIcon("images/avatars/balloon.png"),
			new ImageIcon("images/avatars/burger.png"),
			new ImageIcon("images/avatars/coffee.png"),
			new ImageIcon("images/avatars/flowie.png"),
			new ImageIcon("images/avatars/headphone.png"),
			new ImageIcon("images/avatars/lonewolf.png"),
			new ImageIcon("images/avatars/meiofofo.png"),
			new ImageIcon("images/avatars/moon.png"),
			new ImageIcon("images/avatars/owl.png"),
			new ImageIcon("images/avatars/pathy.png"),
			new ImageIcon("images/avatars/pens.png"),
			new ImageIcon("images/avatars/pug.png"),
			new ImageIcon("images/avatars/sea.png"),
			new ImageIcon("images/avatars/sempreaqui.png"),
			new ImageIcon("images/avatars/vintage.png")
	});
	
	public static String getNomeImagem(ImageIcon i) {
		if(i == avatares.get(0)) return "balloon";
		else if(i == avatares.get(1)) return "burger";
		else if(i == avatares.get(2)) return "coffee";
		else if(i == avatares.get(3)) return "flowie";
		else if(i == avatares.get(4)) return "headphone";
		else if(i == avatares.get(5)) return "lonewolf";
		else if(i == avatares.get(6)) return "meiofofo";
		else if(i == avatares.get(7)) return "moon";
		else if(i == avatares.get(8)) return "owl";
		else if(i == avatares.get(9)) return "pathy";
		else if(i == avatares.get(10)) return "pens";
		else if(i == avatares.get(11)) return "pug";
		else if(i == avatares.get(12)) return "sea";
		else if(i == avatares.get(13)) return "sempreaqui";
		else if(i == avatares.get(14)) return "vintage";
		else return "";
	}
	
	public static ImageIcon getImagemPorNome(String n) {
		if(n.equals("balloon")) return avatares.get(0);
		else if(n.equals("burger")) return avatares.get(1);
		else if(n.equals("coffee")) return avatares.get(2);
		else if(n.equals("flowie")) return avatares.get(3);
		else if(n.equals("headphone")) return avatares.get(4);
		else if(n.equals("lonewolf")) return avatares.get(5);
		else if(n.equals("meiofofo")) return avatares.get(6);
		else if(n.equals("moon")) return avatares.get(7);
		else if(n.equals("owl")) return avatares.get(8);
		else if(n.equals("pathy")) return avatares.get(9);
		else if(n.equals("pens")) return avatares.get(10);
		else if(n.equals("pug")) return avatares.get(11);
		else if(n.equals("sea")) return avatares.get(12);
		else if(n.equals("sempreaqui")) return avatares.get(13);
		else if(n.equals("vintage")) return avatares.get(14);
		else return null;
	}
	
	static{
		CORES.add(CLOUDS);
		CORES.add(SILVER);
		CORES.add(CONCRETE);
		CORES.add(ASBESTAS);
		CORES.add(ASPHALT);
		CORES.add(MIDNIGHT);
		CORES.add(EMERALD);
		CORES.add(NEPHRITIS);
		CORES.add(VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO);
		CORES.add(ABACATE_BALADEIRO);
		CORES.add(WATERMELON);
		CORES.add(TURQUOISE);
		CORES.add(SEA_GREEN);
		CORES.add(PETER_RIVER);
		CORES.add(BELIZE_HOLE);
		CORES.add(AMETHYST);
		CORES.add(WISTERIA);
		CORES.add(ALIZARIN);
		CORES.add(POMEGRANATE);
		CORES.add(CARROT);
		CORES.add(PUMPKIN);
		CORES.add(SUN_FLOWER);
		CORES.add(ORANGE);
		
		INTRFC_CORES_SUPLEMENTARES.put(CLOUDS, SILVER);
		INTRFC_CORES_SUPLEMENTARES.put(CONCRETE, ASBESTAS);
		INTRFC_CORES_SUPLEMENTARES.put(ASPHALT, MIDNIGHT);
		
		INTRFC_CORES_SUPLEMENTARES.put(TURQUOISE, SEA_GREEN);
		INTRFC_CORES_SUPLEMENTARES.put(EMERALD, NEPHRITIS);
		INTRFC_CORES_SUPLEMENTARES.put(PETER_RIVER, BELIZE_HOLE);
		INTRFC_CORES_SUPLEMENTARES.put(AMETHYST, WISTERIA);
		INTRFC_CORES_SUPLEMENTARES.put(SUN_FLOWER, ORANGE);
		INTRFC_CORES_SUPLEMENTARES.put(CARROT, PUMPKIN);
		INTRFC_CORES_SUPLEMENTARES.put(ALIZARIN, POMEGRANATE);
		
		INTRFC_CORES_SUPLEMENTARES.put(SILVER, CLOUDS);
		INTRFC_CORES_SUPLEMENTARES.put(ASBESTAS, CONCRETE);
		INTRFC_CORES_SUPLEMENTARES.put(MIDNIGHT, ASPHALT);
		
		INTRFC_CORES_SUPLEMENTARES.put(SEA_GREEN, TURQUOISE);
		INTRFC_CORES_SUPLEMENTARES.put(NEPHRITIS, EMERALD);
		INTRFC_CORES_SUPLEMENTARES.put(BELIZE_HOLE, PETER_RIVER);
		INTRFC_CORES_SUPLEMENTARES.put(WISTERIA, AMETHYST);
		INTRFC_CORES_SUPLEMENTARES.put(ORANGE, SUN_FLOWER);
		INTRFC_CORES_SUPLEMENTARES.put(PUMPKIN, CARROT);
		INTRFC_CORES_SUPLEMENTARES.put(POMEGRANATE, ALIZARIN);
		
		INTRFC_CORES_SUPLEMENTARES.put(VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO, ABACATE_BALADEIRO);
		INTRFC_CORES_SUPLEMENTARES.put(ABACATE_BALADEIRO, VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO);
		INTRFC_CORES_SUPLEMENTARES.put(WATERMELON, VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO);
		
		INTRFC_CORES_COMPLEMENTARES.put(VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO, WATERMELON);
		INTRFC_CORES_COMPLEMENTARES.put(ABACATE_BALADEIRO, WATERMELON);
		INTRFC_CORES_COMPLEMENTARES.put(WATERMELON, ABACATE_BALADEIRO);

		INTRFC_CORES_COMPLEMENTARES.put(ALIZARIN, EMERALD);
		INTRFC_CORES_COMPLEMENTARES.put(CARROT, TURQUOISE);
		INTRFC_CORES_COMPLEMENTARES.put(PETER_RIVER, SUN_FLOWER);
		
		INTRFC_CORES_COMPLEMENTARES.put(EMERALD, ALIZARIN);
		INTRFC_CORES_COMPLEMENTARES.put(TURQUOISE, CARROT);
		INTRFC_CORES_COMPLEMENTARES.put(SUN_FLOWER, PETER_RIVER);
		INTRFC_CORES_COMPLEMENTARES.put(AMETHYST, SUN_FLOWER); // ...

		INTRFC_CORES_COMPLEMENTARES.put(NEPHRITIS, POMEGRANATE);
		INTRFC_CORES_COMPLEMENTARES.put(SEA_GREEN, PUMPKIN);
		INTRFC_CORES_COMPLEMENTARES.put(ORANGE, BELIZE_HOLE);
		
		INTRFC_CORES_COMPLEMENTARES.put(POMEGRANATE, NEPHRITIS);
		INTRFC_CORES_COMPLEMENTARES.put(PUMPKIN, SEA_GREEN);
		INTRFC_CORES_COMPLEMENTARES.put(BELIZE_HOLE, ORANGE);
		INTRFC_CORES_COMPLEMENTARES.put(WISTERIA, SUN_FLOWER);// ...

		INTRFC_CORES_COMPLEMENTARES.put(MIDNIGHT, CLOUDS);
		INTRFC_CORES_COMPLEMENTARES.put(ASPHALT, SILVER);
		INTRFC_CORES_COMPLEMENTARES.put(CONCRETE, ASBESTAS);
		INTRFC_CORES_COMPLEMENTARES.put(CLOUDS, MIDNIGHT);
		INTRFC_CORES_COMPLEMENTARES.put(SILVER, ASPHALT);
		INTRFC_CORES_COMPLEMENTARES.put(ASBESTAS, CONCRETE);

		
//		setCor(ABACATE_BALADEIRO);
		
		// PRA TESTES!
		COR_ATUAL = BELIZE_HOLE;
		COR_ATUAL_COMPLEMENTAR = INTRFC_CORES_COMPLEMENTARES.get(COR_ATUAL);
		COR_ATUAL_SUPLEMENTAR = INTRFC_CORES_SUPLEMENTARES.get(COR_ATUAL);
	}

	/**
	 * Método responsável por recolorir a tela da <tt>Agenda</tt>.
	 * 
	 * @param corPrincipal A cor a ser selecionada como a principal do sistema
	 */
	public static void setCor(Color corPrincipal){
		try {
			COR_ATUAL = corPrincipal;
			COR_ATUAL_COMPLEMENTAR = INTRFC_CORES_COMPLEMENTARES.get(corPrincipal);
			COR_ATUAL_SUPLEMENTAR = INTRFC_CORES_SUPLEMENTARES.get(corPrincipal);
			
			USER_LOGADO.setCor(corPrincipal);
			
			DAOUsuario.editar(USER_LOGADO);
			
//			Agenda.restartInterface();
			Configuracao.agenda.dispose();
			start(USER_LOGADO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>Método responsável por iniciar a <tt>Agenda</tt>, iniciando-se pela instância dum <tt>Splash</tt>,
	 * além de startar o banco de dados</p>
	 * 
	 * @see Agenda
	 * @see Splash
	 */
	public static void start(){
		Repositorio.getConnection();
		new Splash(3);
//		new TelaLogin();
//		if(Repositorio.isValido()) System.out.println("SUCESSO!");
	}
	
	public static void start(Usuario u){
		Repositorio.getConnection();
		new Splash(3, u);
//		new TelaLogin();
//		if(Repositorio.isValido()) System.out.println("SUCESSO!");
	}
	
	public static Color getCor(String n){
		n = n.toLowerCase();
		switch(n){
			case "clouds": return Configuracao.CLOUDS;
			case "silver": return Configuracao.SILVER;
			case "concrete": return Configuracao.CONCRETE;
			case "asbestas": return Configuracao.ASBESTAS;
			case "asphalt": return Configuracao.ASPHALT;
			case "midnight": return Configuracao.MIDNIGHT;
			case "emerald": return Configuracao.EMERALD;
			case "nephritis": return Configuracao.NEPHRITIS;
			case "turquoise": return Configuracao.TURQUOISE;
			case "sea green": return Configuracao.SEA_GREEN;
			case "peter river": return Configuracao.PETER_RIVER;
			case "belize hole": return Configuracao.BELIZE_HOLE;
			case "amethyst": return Configuracao.AMETHYST;
			case "wisteria": return Configuracao.WISTERIA;
			case "alizarin": return Configuracao.ALIZARIN;
			case "pomegranate": return Configuracao.POMEGRANATE;
			case "carrot": return Configuracao.CARROT;
			case "pumpkin": return Configuracao.PUMPKIN;
			case "sun flower": return Configuracao.SUN_FLOWER;
			case "orange": return Configuracao.ORANGE;
			case "vgdidsdv": return Configuracao.VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO;
			case "abacate baladeiro": return Configuracao.ABACATE_BALADEIRO;
			case "watermelon": return Configuracao.WATERMELON;
			default: return null;
		}
	}
	
	public static String getNome(Color c){
		if(c.equals(Configuracao.CLOUDS)) return "clouds";
		else if(c.equals(Configuracao.SILVER)) return "silver";
		else if(c.equals(Configuracao.CONCRETE)) return "concrete";
		else if(c.equals(Configuracao.ASBESTAS)) return "asbestas";
		else if(c.equals(Configuracao.ASPHALT)) return "asphalt";
		else if(c.equals(Configuracao.MIDNIGHT)) return "midnight";
		else if(c.equals(Configuracao.EMERALD)) return "emerald";
		else if(c.equals(Configuracao.NEPHRITIS)) return "nephritis";
		else if(c.equals(Configuracao.TURQUOISE)) return "turquoise";
		else if(c.equals(Configuracao.SEA_GREEN)) return "sea green";
		else if(c.equals(Configuracao.PETER_RIVER)) return "peter river";
		else if(c.equals(Configuracao.BELIZE_HOLE)) return "belize hole";
		else if(c.equals(Configuracao.AMETHYST)) return "amethyst";
		else if(c.equals(Configuracao.WISTERIA)) return "wisteria";
		else if(c.equals(Configuracao.ALIZARIN)) return "alizarin";
		else if(c.equals(Configuracao.POMEGRANATE)) return "pomegranate";
		else if(c.equals(Configuracao.CARROT)) return "carrot";
		else if(c.equals(Configuracao.PUMPKIN)) return "pumpkin";
		else if(c.equals(Configuracao.SUN_FLOWER)) return "sun flower";
		else if(c.equals(Configuracao.ORANGE)) return "orange";
		else if(c.equals(Configuracao.VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO)) return "vgdidsdv";
		else if(c.equals(Configuracao.ABACATE_BALADEIRO)) return "abacate baladeiro";
		else if(c.equals(Configuracao.WATERMELON)) return "watermelon";
		return "";
	}
}
