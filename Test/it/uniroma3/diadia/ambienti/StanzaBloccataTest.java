package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import static it.uniroma3.diadia.Direzione.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe di testing per StanzaBloccata
 * * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see StanzaBloccata
 * @version 3.0
 */
public class StanzaBloccataTest {

	private StanzaBloccata stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo passepartout;

	/**
	 * Imposta l'ambiente di test creando una StanzaBloccata, 
	 * una stanza adiacente a cui arrivare e l'attrezzo sbloccante.
	 */
	@BeforeEach
	public void setUp() {
		this.stanza = new StanzaBloccata("Stanza Cassaforte", NORD, "passepartout");
		this.stanzaAdiacente = new Stanza("Uscita");
		this.stanza.impostaStanzaAdiacente(NORD, this.stanzaAdiacente);
		
		this.passepartout = new Attrezzo("passepartout", 1);
	}

	/**
	 * Verifica che il metodo restituisca la stanza corrente 
	 * se manca l'attrezzo sbloccante per la direzione chiusa.
	 */
	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		assertEquals(this.stanza, this.stanza.getStanzaAdiacente(NORD));
	}

	/**
	 * Verifica che il metodo restituisca la stanza adiacente 
	 * una volta posato l'attrezzo sbloccante sul pavimento.
	 */
	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		this.stanza.addAttrezzo(this.passepartout);
		assertEquals(this.stanzaAdiacente, this.stanza.getStanzaAdiacente(NORD));
	}

	/**
	 * Verifica che la descrizione segnali il blocco 
	 * se l'attrezzo non e' presente.
	 */
	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		assertTrue(this.stanza.getDescrizione().contains("bloccata"));
		assertTrue(this.stanza.getDescrizione().contains("passepartout"));
	}
}