package br.edu.overise.agenda.visual;
import java.util.Timer;

import br.edu.overise.agenda.controle.Configuracao;
import br.edu.overise.agenda.modelo.desktop.IPainel;

/**
 * Painel usado para fins de testes
 * @author Overise
 */
public class PainelTestes extends IPainel{
	/** */
	private static final long serialVersionUID = -3073714546935059964L;

	public PainelTestes(){
		super();
		this.setBackground(Configuracao.COR_ATUAL_SUPLEMENTAR);
	}
}
