package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Classe di testing
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see Labirinto
 * @version 2.0
 */

class LabirintoTest {

	@Test
	void testStanzaInizialeAtrio() {
        // Avendo rimosso il costruttore cablato, generiamo noi una stanza per testare i getter
        Labirinto l = Labirinto.newBuilder().addStanzaIniziale("Atrio").getLabirinto();
		assertEquals("Atrio", l.getStanzaIniziale().getNome());	
	}
	
	@Test
	void testStanzaVincenteBiblioteca() {
        Labirinto l = Labirinto.newBuilder().addStanzaVincente("Biblioteca").getLabirinto();
		assertEquals("Biblioteca", l.getStanzaVincente().getNome());	
	}

}