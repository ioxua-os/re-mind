package br.edu.overise.agenda.teste;

import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.Visual;
import br.edu.overise.agenda.modelo.geral.Temporizador;

/**
 * Classe usada com o fim de tentar corrigir o bug visual "dos meses"
 * @see Agenda
 * @author Yehoshua Oliveira
 */
public class LabelSaindo extends JFrame {
	public LabelSaindo() throws Exception{

		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JLabel lbA = queroTestar("SAI!");
		this.add(lbA);
		
		Temporizador temp = new Temporizador();
//		temp.schedule(new Visual().stayForTEST(this, lbA), 4000);
		temp.push(this, lbA);
		
		lbA = queroTestar("ENTRA!");
		this.add(lbA);
		temp.push(this, lbA);
//		temp.schedule(new Visual().stayForTEST(this, lbB), 4000);
		
		
		this.setVisible(true);
	}
	
	private JLabel queroTestar(String t){
		JLabel lbA = new JLabel(t);
		lbA.setFont(Configuracao.SEGOE_NEGRITO_GIGANTE);
		lbA.setSize(lbA.getPreferredSize());
		lbA.setLocation(100, 100);
		return lbA;
	}
	
	public static void main(String[] args) throws Exception {
		new LabelSaindo();
	}
}

