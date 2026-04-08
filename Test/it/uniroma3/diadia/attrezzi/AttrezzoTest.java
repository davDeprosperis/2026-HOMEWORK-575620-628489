package it.uniroma3.diadia.attrezzi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Classe di testing
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Attrezzo
 * @version 2.0
 */

class AttrezzoTest {
	private Attrezzo martello;

	@BeforeEach
	
	void setUp() {
		this.martello = new Attrezzo("martello", 1);
	}

	@Test
	
	void testLetturaaNomeAttrezzo() {
		assertEquals("martello", this.martello.getNome());

	}

	@Test
	
	void testLetturaPesoAttrezzo() {
		assertEquals(1, this.martello.getPeso());
	}

	@Test
	
	void testStampaTutteLeInformazioni() {
		assertEquals("martello (1kg)", this.martello.toString());
	}

}
