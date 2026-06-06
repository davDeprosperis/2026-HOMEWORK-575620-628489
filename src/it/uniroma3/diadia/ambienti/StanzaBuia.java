package it.uniroma3.diadia.ambienti;

/**
 * Classe StanzaBuia -
 * Se non e' presente l'attrezzo luminoso richiesto, la sua descrizione 
 * sara' "qui c'è un buio pesto".
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Stanza
 * @version 3.0
 */
public class StanzaBuia extends Stanza {

	private String attrezzoPerVedere;

	/**
	 * Crea una stanza buia.
	 * 
	 * @param nome il nome della stanza
	 * @param attrezzoPerVedere il nome dell'attrezzo necessario per illuminarla
	 */
	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoPerVedere = attrezzoPerVedere;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * Se l'attrezzo luminoso e' presente, restituisce la descrizione standard,
	 * altrimenti restituisce un messaggio di buio.
	 * 
	 * @return la descrizione della stanza
	 */
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(this.attrezzoPerVedere)) {
			return super.getDescrizione(); 
		} else {
			return "qui c'è un buio pesto"; 
		}
	}
}