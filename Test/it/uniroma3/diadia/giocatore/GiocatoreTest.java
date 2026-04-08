package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Classe di testing
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Giocatore
 * @version 2.0
 */

class GiocatoreTest {
	private Giocatore giocatore;

	@BeforeEach
	
	void setUp() {
		this.giocatore = new Giocatore();
	}

	@Test
	
	void testCfuInizialiSono20() {
		assertEquals(20, this.giocatore.getCfu());
	}

	@Test
	
	void testSetCfu() {
		this.giocatore.setCfu(1);
		assertEquals(1, this.giocatore.getCfu());
	}

	@Test
	
	void testIlCostruttoreHaCreatoCorrettamenteUnaBorsa() {
		assertNotNull(this.giocatore.getBorsa());
	}

}
