package br.edu.overise.agenda.teste;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;

/**
 * Simples classe de teste para visualizar os esquemas de cores do projeto.
 * @author Yehoshua Oliveira
 */
public class EsquemasDeCores extends JFrame{
	JPanel panComp = new JPanel();
	JPanel panCor = new JPanel();
	JPanel panSupl = new JPanel();

	String[] cores = {
			"Clouds", "Silver", "Concrete", "Asbestas", "Asphalt", "Midnight", "Emerald", "Nephritis", "Turquoise", "Sea Green", "Peter River",
			"Belize Hole", "Amethyst", "Wisteria", "Alizarin", "Pomegranate", "Carrot", "Pumpkin", "Sun Flower", "Orange", "VGDIDSDV",
			"Abacate Baladeiro"//, "Watermelon"
	};
	
	JComboBox<String> cbCores = new JComboBox<String>(cores);
	JLabel lbAtual = new JLabel();
	JLabel lbAnt = new JLabel();
	JLabel lbProx = new JLabel();
	
	int indexAtual = 0;
	
	public EsquemasDeCores() {
//		this.setSize((int)Configuracao.LARGURA_TELA, (int)Configuracao.ALTURA_TELA);
		this.setTitle("Esquemas de Cores");
		this.setSize(450, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setUndecorated(true);
//		this.setLocation(0, 0);
		this.setLayout(null);
		

		lbAtual.setFont(Configuracao.SEGOE_NEGRITO_GIGANTE);
		this.add(lbAtual);
		
		lbAnt.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		this.add(lbAnt);
		
		lbProx.setFont(lbAnt.getFont());
		this.add(lbProx);
		
		panComp.setBackground(Configuracao.MIDNIGHT);
		panComp.setBounds(0, 0, 150, 150);
		this.add(panComp);

		panCor.setBackground(Configuracao.ASPHALT);
		panCor.setSize(panComp.getSize());
		panCor.setLocation(150, 0);
		this.add(panCor);

		panSupl.setBackground(Configuracao.ASBESTAS);
		panSupl.setSize(panComp.getSize());
		panSupl.setLocation(300, 0);
		this.add(panSupl);
		
		
		pintar();
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					if(indexAtual > 0){
						indexAtual--;
						pintar();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(indexAtual < cores.length - 1){
						indexAtual++;
						pintar();
					}
					break;
				}
			}
		});
		
//		cbCores.setSize(cbCores.getPreferredSize());
//		cbCores.setLocation(getWidth() / 2 - cbCores.getWidth() / 2, getHeight() - cbCores.getHeight() * 2 - 10);
//		cbCores.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				Color c = getCor((String)e.getItem());
//				panCor.setBackground(c);
//				panComp.setBackground(Configuracao.INTRFC_CORES_COMPLEMENTARES.get(c));
//				panSupl.setBackground(Configuracao.INTRFC_CORES_SUPLEMENTARES.get(c));
//			}
//		});
//		this.add(cbCores);
		
		JPanel fundo = new JPanel();
		fundo.setSize(this.getSize());
		fundo.setBackground(Configuracao.SILVER);
		fundo.setLocation(0, 0);
		this.add(fundo);
		
		this.setVisible(true);
	}
	
	private void pintar(){
		String s = cores[indexAtual];
		Color a = getCor(s);
		panCor.setBackground(a);
		panComp.setBackground(Configuracao.INTRFC_CORES_COMPLEMENTARES.get(a));
		panSupl.setBackground(Configuracao.INTRFC_CORES_SUPLEMENTARES.get(a));
		
		lbAtual.setText(s);
		lbAtual.setSize(lbAtual.getPreferredSize());
		lbAtual.setLocation(getWidth() / 2 - lbAtual.getWidth() / 2, getHeight() - lbAtual.getHeight() - 50);
		
		lbAnt.setText(getNome(panComp.getBackground()));
		lbAnt.setSize(lbAnt.getPreferredSize());
		lbAnt.setLocation(0, panComp.getHeight() - lbAnt.getHeight());
		
		lbProx.setText(getNome(panSupl.getBackground()));
		lbProx.setSize(lbProx.getPreferredSize());
		lbProx.setLocation(getWidth() - lbProx.getWidth() - 8, lbAnt.getY());

	}

	private Color getCor(String n){
		switch(n){
			case "Clouds": return Configuracao.CLOUDS;
			case "Silver": return Configuracao.SILVER;
			case "Concrete": return Configuracao.CONCRETE;
			case "Asbestas": return Configuracao.ASBESTAS;
			case "Asphalt": return Configuracao.ASPHALT;
			case "Midnight": return Configuracao.MIDNIGHT;
			case "Emerald": return Configuracao.EMERALD;
			case "Nephritis": return Configuracao.NEPHRITIS;
			case "Turquoise": return Configuracao.TURQUOISE;
			case "Sea Green": return Configuracao.SEA_GREEN;
			case "Peter River": return Configuracao.PETER_RIVER;
			case "Belize Hole": return Configuracao.BELIZE_HOLE;
			case "Amethyst": return Configuracao.AMETHYST;
			case "Wisteria": return Configuracao.WISTERIA;
			case "Alizarin": return Configuracao.ALIZARIN;
			case "Pomegranate": return Configuracao.POMEGRANATE;
			case "Carrot": return Configuracao.CARROT;
			case "Pumpkin": return Configuracao.PUMPKIN;
			case "Sun Flower": return Configuracao.SUN_FLOWER;
			case "Orange": return Configuracao.ORANGE;
			case "VGDIDSDV": return Configuracao.VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO;
			case "Abacate Baladeiro": return Configuracao.ABACATE_BALADEIRO;
			case "Watermelon": return Configuracao.WATERMELON;
			default: return null;
		}
	}
	
	private String getNome(Color c){
		if(c.equals(Configuracao.CLOUDS)) return "Clouds";
		else if(c.equals(Configuracao.SILVER)) return "Silver";
		else if(c.equals(Configuracao.CONCRETE)) return "Concrete";
		else if(c.equals(Configuracao.ASBESTAS)) return "Asbestas";
		else if(c.equals(Configuracao.ASPHALT)) return "Asphalt";
		else if(c.equals(Configuracao.MIDNIGHT)) return "Midnight";
		else if(c.equals(Configuracao.EMERALD)) return "Emerald";
		else if(c.equals(Configuracao.NEPHRITIS)) return "Nephritis";
		else if(c.equals(Configuracao.TURQUOISE)) return "Turquoise";
		else if(c.equals(Configuracao.SEA_GREEN)) return "Sea Green";
		else if(c.equals(Configuracao.PETER_RIVER)) return "Peter River";
		else if(c.equals(Configuracao.BELIZE_HOLE)) return "Belize Hole";
		else if(c.equals(Configuracao.AMETHYST)) return "Amethyst";
		else if(c.equals(Configuracao.WISTERIA)) return "Wisteria";
		else if(c.equals(Configuracao.ALIZARIN)) return "Alizarin";
		else if(c.equals(Configuracao.POMEGRANATE)) return "Pomegranate";
		else if(c.equals(Configuracao.CARROT)) return "Carrot";
		else if(c.equals(Configuracao.PUMPKIN)) return "Pumpkin";
		else if(c.equals(Configuracao.SUN_FLOWER)) return "Sun Flower";
		else if(c.equals(Configuracao.ORANGE)) return "Orange";
		else if(c.equals(Configuracao.VERDE_GRAMA_DO_INICIO_DO_SOLSTICIO_DE_VERAO)) return "VGDIDSDV";
		else if(c.equals(Configuracao.ABACATE_BALADEIRO)) return "Abacate Baladeiro";
		else if(c.equals(Configuracao.WATERMELON)) return "Watermelon";
		return "";
	}
	
	public static void main(String[] args) {
		new EsquemasDeCores();
	}
}
