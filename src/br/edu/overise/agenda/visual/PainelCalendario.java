package br.edu.overise.agenda.visual;
import java.util.Timer;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.IPainel;
import br.edu.overise.agenda.modelo.desktop.PainelEsquerdo;

/**
 * Painel usado para exibi��o das op��es de calend�rio
 * @author Overise
 */
public class PainelCalendario extends PainelEsquerdo{
	/** */
	private static final long serialVersionUID = -3073714546935059964L;

	public PainelCalendario(){
		super("E X I B I � � O");
		this.setBackground(Configuracao.COR_ATUAL);
	}
}
