package br.edu.overise.agenda.visual;
import java.awt.Dimension;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.dao.DAOEvento;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.modelo.desktop.PainelEsquerdo;
import br.edu.overise.agenda.modelo.geral.Evento;

/**
 * Painel usado para exibição das notificações
 * @author Overise
 */
public class PainelNotificacoes extends PainelEsquerdo {
	/** */
	private static final long serialVersionUID = -3073714546935059964L;

	List<JPanel> notificacoes = new ArrayList<>();

	JLabel l;
	
	public PainelNotificacoes() {
		super("N O T I F I C A Ç Õ E S");
		this.setBackground(Configuracao.COR_ATUAL);
		try {
			l = new JLabel("N E S S E  M Ê S");
			l.setForeground(Configuracao.CLOUDS);
			l.setFont(Configuracao.SEGOE_NEGRITO_GIGANTE);
			l.setSize(l.getPreferredSize());
			l.setLocation(ANCORA_DIREITA - l.getWidth() - 5, this.getTitulo().getY() + this.getTitulo().getHeight() + 5);
			this.add(l);
			
			Dimension d = new Dimension((int)(this.getWidth() - (this.ANCORA_ESQUERDA * 2)), (int)(Configuracao.ALTURA_TELA_PERCENT * 8));

			int alt = l.getY() + l.getHeight() + 5;
			
			for(Evento e : DAOEvento.getEventosPorUsuarioPorMes(Configuracao.USER_LOGADO, Agenda.MES_ATUAL)) {
				JPanel notif = new JPanel();
				notif.setSize(d);
				notif.setLayout(null);
				notif.setLocation((int)ANCORA_ESQUERDA, alt);
				notif.setBackground(Configuracao.CLOUDS);
				
				JLabel lb = new JLabel( DAOEvento.parseData(e.getDataInicio()) );
				lb.setFont(Configuracao.SEGOE_REGULAR_MEDIA);
				lb.setSize(lb.getPreferredSize());
				lb.setLocation(5, 5);
				lb.setForeground(Configuracao.MIDNIGHT);
				notif.add(lb);
				
				lb = new JLabel(e.getNomeEvento());
				lb.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
				lb.setSize(lb.getPreferredSize());
				lb.setLocation(notif.getWidth() - lb.getWidth() - 5, notif.getHeight() - lb.getHeight() - 5);
				lb.setForeground(Configuracao.MIDNIGHT);
				notif.add(lb);

				alt += (int)(notif.getHeight() * 1.23);
				
				notificacoes.add(notif);
				this.add(notif);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarMes() {
		notificacoes.stream().forEach((n) -> this.remove(n));

		try {
			Dimension d = new Dimension((int)(this.getWidth() - (this.ANCORA_ESQUERDA * 2)), (int)(Configuracao.ALTURA_TELA_PERCENT * 8));

			int alt = l.getY() + l.getHeight() + 5;
			
			for(Evento e : DAOEvento.getEventosPorUsuarioPorMes(Configuracao.USER_LOGADO, Agenda.MES_ATUAL, Agenda.ANO_ATUAL)) {
				JPanel notif = new JPanel();
				notif.setSize(d);
				notif.setLayout(null);
				notif.setLocation((int)ANCORA_ESQUERDA, alt);
				notif.setBackground(Configuracao.CLOUDS);
				
				JLabel lb = new JLabel( DAOEvento.parseData(e.getDataInicio()) );
				lb.setFont(Configuracao.SEGOE_REGULAR_MEDIA);
				lb.setSize(lb.getPreferredSize());
				lb.setLocation(5, 5);
				lb.setForeground(Configuracao.MIDNIGHT);
				notif.add(lb);
				
				lb = new JLabel(e.getNomeEvento());
				lb.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
				lb.setSize(lb.getPreferredSize());
				lb.setLocation(notif.getWidth() - lb.getWidth() - 5, notif.getHeight() - lb.getHeight() - 5);
				lb.setForeground(Configuracao.MIDNIGHT);
				notif.add(lb);

				alt += (int)(notif.getHeight() * 1.23);
				
				notificacoes.add(notif);
				this.add(notif);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
