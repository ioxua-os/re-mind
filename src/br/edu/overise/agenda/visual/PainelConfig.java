package br.edu.overise.agenda.visual;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.Dia;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.modelo.desktop.PainelEsquerdo;

/**
 * Painel de configurações do projeto
 * @author Overise
 */
public class PainelConfig extends PainelEsquerdo implements ActionListener{
			
	private JButton btnCor, btnB, btnC, btnD;
	
	public PainelConfig(){
		super("Configurações");
		
		this.setBackground(Configuracao.ASPHALT);
		
		btnCor = new BotaoOpcao("TROCAR COR");
		btnCor.setLocation(ANCORA_ESQUERDA, getTitulo().getY() + getTitulo().getHeight() + 10);
		btnCor.addActionListener(this);
		this.add(btnCor);
		
		btnB = new BotaoOpcao("CONFIG2");
		btnB.setLocation(ANCORA_DIREITA - btnB.getWidth(), btnCor.getY());
		btnB.addActionListener(this);
		this.add(btnB);

		int DISTANCIA_ENTRE_LINHAS = btnB.getX() - (btnCor.getX() + btnCor.getWidth());
		
		btnC = new BotaoOpcao("CONFIG3");
		btnC.setLocation(ANCORA_ESQUERDA, btnCor.getY() + btnCor.getHeight() + DISTANCIA_ENTRE_LINHAS);
		btnC.addActionListener(this);
		this.add(btnC);

		btnD = new BotaoOpcao("CONFIG4");
		btnD.setLocation(ANCORA_DIREITA - btnD.getWidth(), btnC.getY());
		btnD.addActionListener(this);
		this.add(btnD);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCor)){
			// TODO SLA OQ VC QUER COLOCAR AQUI..
		}
	}
}
