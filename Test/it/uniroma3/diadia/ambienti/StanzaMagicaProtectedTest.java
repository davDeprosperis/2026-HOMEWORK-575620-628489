package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe di testing per StanzaMagicaProtected
 * * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see StanzaMagicaProtected
 * @version 3.0
 */
public class StanzaMagicaProtectedTest {

	private StanzaMagicaProtected stanza;

	@BeforeEach
	public void setUp() {
		// Impostiamo la soglia a 1 per mantenere il test minimale
		this.stanza = new StanzaMagicaProtected("StanzaMagicaProtectedTest", 1);
	}

	@Test
	public void testAddAttrezzo_ComportamentoNormale_SottoSoglia() {
		Attrezzo a1 = new Attrezzo("spada", 3);
		
		this.stanza.addAttrezzo(a1); // Contatore = 1 (nessuna magia)
		
		assertTrue(this.stanza.hasAttrezzo("spada"));
		assertEquals(3, this.stanza.getAttrezzo("spada").getPeso());
	}

	@Test
	public void testAddAttrezzo_ComportamentoMagico_SopraSoglia() {
		Attrezzo a1 = new Attrezzo("spada", 3);
		Attrezzo a2 = new Attrezzo("scudo", 5);
		
		this.stanza.addAttrezzo(a1); // Contatore = 1 (nessuna magia)
		this.stanza.addAttrezzo(a2); // Contatore = 2 (supera la soglia 1, scatta la magia)

		assertFalse(this.stanza.hasAttrezzo("scudo"));
		assertTrue(this.stanza.hasAttrezzo("oducs"));
		assertEquals(10, this.stanza.getAttrezzo("oducs").getPeso());
	}
}