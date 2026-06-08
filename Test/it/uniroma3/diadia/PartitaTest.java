package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe di testing
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see Partita
 * @version 2.0
 */

class PartitaTest {
	private Partita partita;

	@BeforeEach

	void setUp() {
		this.partita = new Partita();
	}

	@Test
	void testPartitaAllInizioNonEFinita() {
		assertFalse(this.partita.isFinita());
	}

	@Test
	void testSeStoNellaStanzaVincenteLaPartitaEVinta() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}

	@Test
	void testSeLaPartitaEVintaAlloraEFinita() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}

	@Test
	void testLaPartitaEFinita_nonVinta() {
		this.partita.setFinita();
		assertFalse(this.partita.vinta());
		assertTrue(this.partita.isFinita());
	}

	@Test
	void testLaPartitaEFinita_CfuEsauriti() {
		this.partita.getGiocatore().setCfu(0);
		assertFalse(this.partita.vinta());
		assertTrue(this.partita.isFinita());
	}
}
