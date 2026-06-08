package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe IOSimulator - implementazione di IO per i test di accettazione. Simula
 * un utente iniettando comandi predefiniti tramite un array e memorizzando i
 * messaggi stampati dal gioco per poterli verificare.
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see IO
 * @version 2.0
 */
public class IOSimulator implements IO {

	private LinkedList<String>righeLette;
	private List<String>messaggiProdotti;
	

	/**
	 * Costruisce un simulatore con un array di comandi da eseguire.
	 * 
	 * @param righeDaLeggere l'array dei comandi preimpostati
	 */
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeLette = new LinkedList<>(righeDaLeggere);
		this.messaggiProdotti = new ArrayList<>();
	}

	/**
	 * Legge il prossimo comando dall'array fornito al costruttore.
	 */

	@Override
	public String leggiRiga() {
	    if (!this.righeLette.isEmpty()) {
	        return this.righeLette.removeFirst();
	    }
	    return null;
	}

	/**
	 * Memorizza un messaggio in modo da poterlo controllare nei test.
	 */

	@Override
	public void mostraMessaggio(String messaggio) {
	    this.messaggiProdotti.add(messaggio);
	}

	/**
	 * Verifica se tra i messaggi stampati dal gioco ce n'e' uno che contiene la
	 * stringa passata come parametro.
	 * 
	 * @param messaggioDaCercare la stringa da cercare nei messaggi
	 * @return true se trovata, false altrimenti
	 */

	public boolean hasMessaggio(String messaggioDaCercare) {
	for(String messaggio:messaggiProdotti) {
		if(messaggio.contains(messaggioDaCercare)) {
			return true;
		}
	}
	return false;
	}
}