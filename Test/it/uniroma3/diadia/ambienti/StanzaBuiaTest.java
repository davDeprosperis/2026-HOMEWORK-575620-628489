package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe di testing per StanzaBuia
 * * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see StanzaBuia
 * @version 3.0
 */
public class StanzaBuiaTest {

	private StanzaBuia stanza;
	private Attrezzo lanterna;

	@BeforeEach
	public void setUp() {
		this.stanza = new StanzaBuia("Cantina", "lanterna");
		this.lanterna = new Attrezzo("lanterna", 1);
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		assertEquals("qui c'è un buio pesto", this.stanza.getDescrizione());
	}

	@Test
	public void testGetDescrizioneConAttrezzo() {
		this.stanza.addAttrezzo(this.lanterna);
		assertNotEquals("qui c'è un buio pesto", this.stanza.getDescrizione());
		assertTrue(this.stanza.getDescrizione().contains("Cantina"));
	}
}