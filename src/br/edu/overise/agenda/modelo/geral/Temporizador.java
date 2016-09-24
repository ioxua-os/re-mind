package br.edu.overise.agenda.modelo.geral;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Visual;

/**
 * Falha tentativa de corrigir todos os bugs visuais que me atormentam
 * @author Overise
 * @see Agenda
 */
public class Temporizador extends Timer{
	ArrayList<JLabel> todo = new ArrayList<JLabel>();
	public void push(JFrame pai, JLabel j) throws Exception{
		todo.add(j);
//		if(todo.size() > 1)
//			for(int i = 0; i < todo.size(); i++) pai.remove(todo.get(i));
		schedule(new TimerTask() {
			@Override
			public void run() {
				pai.remove(j);
				pai.repaint();
			}
		}, 4000);
	}
}
