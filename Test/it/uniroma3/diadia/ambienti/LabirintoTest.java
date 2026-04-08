package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Classe di testing
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Labirinto
 * @version 2.0
 */

class LabirintoTest {

	@Test
	
	void testStanzaInizialeAtrio() {
		assertEquals("Atrio", new Labirinto().getStanzaIniziale().getNome());	
	}
	
	@Test
	
	void testStanzaVincenteBiblioteca() {
		assertEquals("Biblioteca", new Labirinto().getStanzaVincente().getNome());	
	}

}
