package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;

/**
 * Classe dedicata al comando "guarda". Stampa le informazioni sulla stanza
 * corrente e sullo stato della partita (CFU del giocatore e contenuto della
 * borsa).
 * 
 * @author Davide De Prosperis, Matricola: 575620
 *@author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 * @see Comando
 * @see Partita
 * @see IO
 */
public class ComandoGuarda extends AbstractComando {

	/**
	 * Stampa la descrizione della stanza corrente e le informazioni del giocatore.
	 * 
	 * @param partita La partita da cui recuperare stanza e giocatore
	 * @param io      interfaccia dedicata alla console
	 */
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Stato giocatore: CFU = " + partita.getGiocatore().getCfu());
		io.mostraMessaggio("Borsa: " + partita.getGiocatore().getBorsa().toString());
	}

	public String getNome() {
		return "guarda";
	}
}