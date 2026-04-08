package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe di testing
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Stanza
 * @version 2.0
 */

class StanzaTest {
	private Stanza stanzaConMartello;
	private Stanza stanzaVuota;
	private Stanza stanzaAlCompleto;

	@BeforeEach

	void setUp() {
		this.stanzaVuota = new Stanza("vuota");
		this.stanzaConMartello = new Stanza("con martello");
		this.stanzaConMartello.addAttrezzo(new Attrezzo("martello", 1));
		this.stanzaAlCompleto = new Stanza("al completo");
		for (int i = 0; i < 10; i++) {
			this.stanzaAlCompleto.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
		}
	}

	@Test
	void testLeStanzeVuoteNonHannoAttrezzi() {
		assertFalse(this.stanzaVuota.hasAttrezzo("martello"));
	}

	@Test

	void testStanzaConUnAttrezzo_trovato() {
		assertTrue(this.stanzaConMartello.hasAttrezzo("martello"));
	}

	@Test

	void testStanzaConUnAttrezzo_mancante() {
		assertFalse(this.stanzaConMartello.hasAttrezzo("conchiglia"));
	}

	@Test

	void testMassimo10Attrezzi() {
		assertFalse(this.stanzaAlCompleto.addAttrezzo(new Attrezzo("escluso", 1)));
	}

	@Test

	void testStanzaConUnAttrezzo_rimuovi() {
		Attrezzo daRimuovere = this.stanzaConMartello.getAttrezzo("martello");
		assertTrue(this.stanzaConMartello.removeAttrezzo(daRimuovere));
		assertFalse(this.stanzaConMartello.hasAttrezzo("martello"));
	}

	@Test

	void testGetAttrezzo_esiste() {
		Attrezzo daVerificare = this.stanzaAlCompleto.getAttrezzo("attrezzo0");
		assertEquals("attrezzo0", daVerificare.getNome());
	}

	@Test
	
	void testGetAttrezzo_nonEsiste() {
		Attrezzo daVerificare = this.stanzaAlCompleto.getAttrezzo("attrezzo11");
		assertNull(daVerificare);
	}

	@Test
	
	void testDirezioniCorrette() {
		this.stanzaVuota.impostaStanzaAdiacente("est", stanzaConMartello);
		assertEquals("est", stanzaVuota.getDirezioni()[0]);
	}
}
