package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;
import java.util.*;

/**
 * Classe dedicata al comando "aiuto"
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 * @see Comando
 * @see Partita
 * @see IO
 */

public class ComandoAiuto extends AbstractComando {
	@Override
	public void esegui(Partita partita, IO io) {
		for (String comando: AbstractComando.getComandiDisponibili()) {
			io.mostraMessaggio(comando + " ");
		}
		io.mostraMessaggio("");
	}
	public String getNome() {
		return "aiuto";
	}
	

	
}
