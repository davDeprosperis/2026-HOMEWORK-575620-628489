package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static it.uniroma3.diadia.Direzione.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe di testing per Stanza
 * * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see Stanza
 * @version 3.0
 */
class StanzaTest {
	private Stanza stanzaConMartello;
	private Stanza stanzaVuota;

	@BeforeEach
	void setUp() {
		this.stanzaVuota = new Stanza("vuota");
		this.stanzaConMartello = new Stanza("con martello");
		this.stanzaConMartello.addAttrezzo(new Attrezzo("martello", 1));
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
	void testStanzaConUnAttrezzo_rimuovi() {
		Attrezzo daRimuovere = this.stanzaConMartello.getAttrezzo("martello");
		assertTrue(this.stanzaConMartello.removeAttrezzo(daRimuovere));
		assertFalse(this.stanzaConMartello.hasAttrezzo("martello"));
	}

	@Test
	void testGetAttrezzo_esiste() {
		Attrezzo daVerificare = this.stanzaConMartello.getAttrezzo("martello");
		assertEquals("martello", daVerificare.getNome());
	}

	@Test
	void testGetAttrezzo_nonEsiste() {
		Attrezzo daVerificare = this.stanzaConMartello.getAttrezzo("cacciavite");
		assertNull(daVerificare);
	}

	@Test
	void testDirezioniCorrette() {
		this.stanzaVuota.impostaStanzaAdiacente(EST, stanzaConMartello);
		assertTrue(this.stanzaVuota.getDirezioni().contains(EST));
		assertEquals(1, this.stanzaVuota.getDirezioni().size());
	}
}