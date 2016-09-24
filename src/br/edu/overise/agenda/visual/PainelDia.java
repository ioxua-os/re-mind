package br.edu.overise.agenda.visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.controle.dao.DAOEvento;
import br.edu.overise.agenda.modelo.desktop.BotaoOpcao;
import br.edu.overise.agenda.modelo.desktop.Dia;
import br.edu.overise.agenda.modelo.desktop.PainelEsquerdo;
import br.edu.overise.agenda.modelo.geral.Evento;

public class PainelDia extends PainelEsquerdo{
	private static final int LARGURA = (int)(Configuracao.LARGURA_TELA_PERCENT * 45);
	private static final int ALTURA = (int)(Configuracao.ALTURA_TELA_PERCENT * 60);
	private static final int HALF_LARGURA = (int) LARGURA / 2;
	private static final int ESPACAMENTO = (int)(Configuracao.ALTURA_TELA_PERCENT * 1.375);
	
	Dia diaAtual;

	String[] horarios = {
			"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00",
			"13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"
	};
	JComboBox<String> cbInicio = new JComboBox<String>(horarios);
	JComboBox<String> cbFinal = new JComboBox<String>(horarios);
	 
	JLabel lbDia = new JLabel();
	JTextField txEvento = new JTextField();
	JTextArea txNotas = new JTextArea();
	 
	DefaultListModel<String> dml = new DefaultListModel<String>();
	JList<String> lista = new JList<String>(dml);
	
	Evento selecionado;
	
	//List<Evento> listaEvento = new ArrayList<Evento>();
	
	public PainelDia() {
		super("D I A");
		
		this.transition = false;
		this.setSize(LARGURA, ALTURA);
		this.setLocation((int)((Configuracao.LARGURA_TELA + Configuracao.X) / 2 - this.getWidth() / 2), 
						(int)(Configuracao.ALTURA_TELA / 2 - this.getHeight() / 2));
		
		lbDia.setFont(Configuracao.SEGOE_NEGRITO_GRANDE);
		lbDia.setForeground(Configuracao.CLOUDS);
		this.add(lbDia);
		
		JLabel lbEvento = new JLabel("Evento:");
		lbEvento.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		lbEvento.setSize(lbEvento.getPreferredSize());
		lbEvento.setLocation(ANCORA_ESQUERDA, (int)(Configuracao.ALTURA_TELA_PERCENT * 17.5));
		lbEvento.setForeground(Configuracao.CLOUDS);
		this.add(lbEvento);
		
		// TODO TROCAR PRA UM COMBO BOX: "EVENTO" - Aniversário, Feriado etc.
		txEvento.setSize((int)(HALF_LARGURA - lbEvento.getWidth() - ANCORA_ESQUERDA - 5), 30);
		txEvento.setLocation(lbEvento.getX() + lbEvento.getWidth() + 5, lbEvento.getY() - 3);
		txEvento.setFont(Configuracao.SEGOE_REGULAR_MEDIA);
		this.add(txEvento);
		
		JLabel lbInicio = new JLabel("Início:");
		lbInicio.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		lbInicio.setForeground(Configuracao.CLOUDS);
		lbInicio.setSize(lbInicio.getPreferredSize());
		lbInicio.setLocation(ANCORA_ESQUERDA, txEvento.getY() + txEvento.getHeight() + ESPACAMENTO);
		this.add(lbInicio);
		
		cbInicio = new JComboBox<String>(horarios);
		cbInicio.setFont(Configuracao.SEGOE_REGULAR_MEDIA);
		cbInicio.setBorder(null);
		cbInicio.setSize(cbInicio.getPreferredSize());
		cbInicio.setLocation(lbInicio.getX() + lbInicio.getWidth() + 5, lbInicio.getY() - 3);
		this.add(cbInicio);

		cbFinal = new JComboBox<String>(horarios);
		cbFinal.setFont(Configuracao.SEGOE_REGULAR_MEDIA);
		cbFinal.setBorder(null);
		cbFinal.setSize(cbFinal.getPreferredSize());
		cbFinal.setLocation(HALF_LARGURA - cbFinal.getWidth(), cbInicio.getY());
		this.add(cbFinal);

		JLabel lbFinal = new JLabel("Final:");
		lbFinal.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		lbFinal.setForeground(Configuracao.CLOUDS);
		lbFinal.setSize(lbInicio.getPreferredSize());
		lbFinal.setLocation(cbFinal.getX() - lbFinal.getWidth() - 5, lbInicio.getY());
		this.add(lbFinal);
		
		BotaoOpcao btAgendar = new BotaoOpcao("Agendar");
		btAgendar.setBackground(Configuracao.CLOUDS);
		btAgendar.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		btAgendar.setSize(btAgendar.getPreferredSize());
		btAgendar.setLocation(HALF_LARGURA - btAgendar.getWidth(), (int)(ALTURA - btAgendar.getHeight() - getTitulo().getY()));
		btAgendar.setPress(Configuracao.COR_ATUAL);
		btAgendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO ADD DIRETO NO BANCO
				try {
					Evento e = new Evento();
					e.setDescEvento(txNotas.getText());
					e.setHrInicio(parseHora((String)cbInicio.getSelectedItem()));
					e.setHrFinal(parseHora((String)cbFinal.getSelectedItem()));
					e.setDataInicio(diaAtual.dataDia);
					e.setDataFinal(diaAtual.dataDia); // TODO SÉRIO ISSO IOXUA?!
					e.setNomeEvento(txEvento.getText());
					e.setDonoEvento(Configuracao.USER_LOGADO);

					dml.addElement(txEvento.getText());
					
					diaAtual.eventos.add(e);
					DAOEvento.cadastrar(e);
					mostrarDia(diaAtual);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		this.add(btAgendar);

		txNotas.setLocation(ANCORA_ESQUERDA, lbInicio.getY() + lbInicio.getHeight() + ESPACAMENTO);
		txNotas.setSize(HALF_LARGURA - ANCORA_ESQUERDA, (int)(ALTURA - getTitulo().getY() - btAgendar.getHeight() - txNotas.getY() - ESPACAMENTO));
		txNotas.setFont(Configuracao.SEGOE_REGULAR_MEDIA);
		this.add(txNotas);
		
		JLabel lbEventos = new JLabel("Eventos do dia:");
		lbEventos.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		lbEventos.setForeground(Configuracao.CLOUDS);
		lbEventos.setSize(lbEventos.getPreferredSize());
		lbEventos.setLocation(HALF_LARGURA + ESPACAMENTO, lbEvento.getY());
		this.add(lbEventos);

		BotaoOpcao btAlterar = new BotaoOpcao("Alterar");
		btAlterar.setBackground(Configuracao.CLOUDS);
		btAlterar.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		btAlterar.setSize(btAlterar.getPreferredSize());
		btAlterar.setLocation(HALF_LARGURA + ESPACAMENTO, btAgendar.getY());
		btAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO PEGAR DIRETO DO BANCO O EVENTO
				try {
					selecionado.setDescEvento(txNotas.getText());
					selecionado.setHrInicio(parseHora((String)cbInicio.getSelectedItem()));
					selecionado.setHrFinal(parseHora((String)cbFinal.getSelectedItem()));
					selecionado.setNomeEvento(txEvento.getText());
					
					DAOEvento.editar(selecionado);
					
					btAlterar.setEnabled(false);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					mostrarDia(diaAtual);
				}
				
			}
		});
		btAlterar.setEnabled(false);
		this.add(btAlterar);
		
		lista.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO PEGAR DIRETO DO BANCO O EVENTO
				try {
					selecionado = DAOEvento.getEventoPorNome(lista.getSelectedValue());
					
					if(selecionado != null) {
						txEvento.setText(selecionado.getNomeEvento());
						cbInicio.setSelectedItem(parseHora(selecionado.getHrInicio()));
						cbFinal.setSelectedItem(parseHora(selecionado.getHrFinal()));
						txNotas.setText(selecionado.getDescEvento());
						btAlterar.setEnabled(true);
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} );
		
		BotaoOpcao btDeletar = new BotaoOpcao("Apagar");
		btDeletar.setBackground(Configuracao.CLOUDS);
		btDeletar.setFont(Configuracao.SEGOE_NEGRITO_MEDIO);
		btDeletar.setSize(btDeletar.getPreferredSize());
		btDeletar.setLocation(btAlterar.getX() + btAlterar.getWidth() + ESPACAMENTO, btAlterar.getY());
		btDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DAOEvento.remover(selecionado);
					mostrarDia(diaAtual);
					btAlterar.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					limparCampos();
				}
			}
		});
		this.add(btDeletar);
		
		lista.setLocation(lbEventos.getX(), lbEventos.getY() + lbEventos.getHeight() + ESPACAMENTO);
		lista.setSize(HALF_LARGURA - ANCORA_ESQUERDA, (int)(ALTURA - getTitulo().getY() - btAlterar.getHeight() - lista.getY() - ESPACAMENTO));
		this.add(lista);
		
		this.setVisible(false);
	}
	
	public void mostrarDia(Dia d){
		try {
			this.diaAtual = d;
			this.lbDia.setText("<html>" + d.dataDia.getDayOfMonth() + " de " + d.getMes() + ", " + d.dataDia.getYear());
			this.lbDia.setSize(lbDia.getPreferredSize());
			this.lbDia.setLocation(ANCORA_ESQUERDA, this.getTitulo().getY() + this.getTitulo().getHeight() + 5);
			diaAtual.eventos = DAOEvento.getEventosPorUsuarioPorData(Configuracao.USER_LOGADO, diaAtual.dataDia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		limparCampos();
	}
	
	public void limparCampos() {
		txEvento.setText("");
		cbInicio.setSelectedIndex(0);
		cbFinal.setSelectedIndex(0);
		txNotas.setText("");

		lista.clearSelection();
		dml.clear();
		diaAtual.eventos.stream().forEach( (e) -> dml.addElement(e.getNomeEvento()) ); 
	}

	private String parseHora(LocalTime hr){
		return ( hr.getHour() < 10? hr.getHour() + "0" : hr.getHour() ) + ":00";
	}
	
	private int parseHora(String hr){
		return Integer.parseInt(hr.substring(0, 2));
	}
	
	private String parseHora(int hr){
		return ( hr < 10? hr + "0" : hr ) + ":00";
	}
	
	private MaskFormatter Mascara (String mask){
        try{
            MaskFormatter F_Mascara = new MaskFormatter();
            F_Mascara.setMask(mask);
            F_Mascara.setPlaceholderCharacter('-'); //Caracter para preenchimento 
            return F_Mascara;
        }
        catch (Exception e) {
        	e.printStackTrace();
        } 
        return null;
	}
}
