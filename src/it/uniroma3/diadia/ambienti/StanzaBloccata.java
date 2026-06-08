package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.Direzione;

/**
 * Classe StanzaBloccata
 * Una delle direzioni non puo' essere seguita a meno che nella stanza
 * non sia presente uno specifico attrezzo sbloccante.
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see Stanza
 * @see Direzione
 * @version 3.0
 */
public class StanzaBloccata extends Stanza {

	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;

	/**
	 * Crea una stanza bloccata.
	 * @param nome il nome della stanza
	 * @param direzioneBloccata la direzione che e' bloccata
	 * @param attrezzoSbloccante il nome dell'attrezzo necessario per sbloccarla
	 */
	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * Se la direzione e' quella bloccata e l'attrezzo sbloccante non e' presente,
	 * restituisce un riferimento alla stanza corrente.
	 * * @param direzione la direzione in cui si vuole andare
	 * @return la stanza adiacente, oppure la stanza stessa se bloccata
	 */
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (this.direzioneBloccata.equals(direzione) && !this.hasAttrezzo(this.attrezzoSbloccante)) {
			return this; 
		}
		return super.getStanzaAdiacente(direzione);
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * Se la stanza e' bloccata, aggiunge un messaggio informativo prima 
	 * della descrizione standard.
	 * * @return la descrizione della stanza
	 */
	@Override
	public String getDescrizione() {
		// Usiamo .name() per ottenere la stringa (es: "NORD") o .toString() se l'hai overridato in Direzione
		String avvisoBlocco = "Attenzione! La direzione " + this.direzioneBloccata.name() + " e' bloccata! Devi posare qui l'attrezzo: " + this.attrezzoSbloccante + "\n";
		
		if (!this.hasAttrezzo(this.attrezzoSbloccante)) {
			return avvisoBlocco + super.getDescrizione();
		}
		
		return super.getDescrizione();
	}
}