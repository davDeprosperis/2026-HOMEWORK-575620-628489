package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe dedicata al comando "fine"
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 * @see Comando
 * @see Partita
 * @see IO
 * 
 */
public class ComandoFine extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
	public String getNome() {
		return "fine";
	}
	
}
