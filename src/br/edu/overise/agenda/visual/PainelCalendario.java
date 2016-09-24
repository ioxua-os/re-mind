package br.edu.overise.agenda.visual;
import java.util.Timer;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.modelo.desktop.PainelEsquerdo;

/**
 * Painel usado para exibição das opções de calendário
 * @author Overise
 */
public class PainelCalendario extends PainelEsquerdo{
	/** */
	private static final long serialVersionUID = -3073714546935059964L;

	public PainelCalendario(){
		super("E X I B I Ç Ã O");
		this.setBackground(Configuracao.COR_ATUAL);
	}
}
