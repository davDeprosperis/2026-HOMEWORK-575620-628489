package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che modella un comando non riconosciuto dal gioco.
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 * @see Comando
 * @see Partita
 * @see IO
 */
public class ComandoNonValido extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Comando sconosciuto o non valido!");
	}
	public String getNome() {
		return "non valido";
	}

}