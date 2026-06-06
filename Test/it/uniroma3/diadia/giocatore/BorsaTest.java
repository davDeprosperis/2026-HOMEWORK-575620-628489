package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;

/**
 * Classe di testing
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Borsa
 * @version 3.0
 */

class BorsaTest {
	private Borsa borsaVuota;
	private Borsa borsaPiena;
	private Attrezzo martello;
	private Attrezzo sasso;

	@BeforeEach

	void setUp() {
		this.borsaVuota = new Borsa(2);
		this.martello = new Attrezzo("martello", 1);
		this.sasso = new Attrezzo("sasso", 1);
		this.borsaPiena = new Borsa(2);
		this.borsaPiena.addAttrezzo(martello);
		this.borsaPiena.addAttrezzo(sasso);
	}

	@Test

	void testLaBorsaVuotaEVeramenteVuota() {
		assertTrue(this.borsaVuota.isEmpty());
	}

	@Test

	void testAggiungiMartelloAllaBorsaVuota() {
		borsaVuota.addAttrezzo(martello);
		assertEquals(martello, this.borsaVuota.getAttrezzo("martello"));
	}

	@Test

	void testProvaAdAggiungereUnOggettoNellaBorsaPiena() {
		assertFalse(borsaPiena.addAttrezzo(new Attrezzo("conchiglia", 1)));
	}

	@Test
	
	void testRimuoviMartelloDallaBorsaPiena() {
		assertEquals(this.martello, borsaPiena.removeAttrezzo("martello"));
		assertNull(this.borsaPiena.getAttrezzo("martello"));
	}

	@Test
	
	void testProvaARimuovereUnOggettoNonPresenteNellaBorsa() {
		assertNull(this.borsaPiena.removeAttrezzo("trapano"));
	}

	@Test
	
	void testProvaARimuovereUnOggettoDallaBorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo("martello"));
	}

	@Test
	
	void testProvaAdAggiungereUnOggettoTroppoPesante() {
		assertFalse(this.borsaVuota.addAttrezzo(new Attrezzo("peso", 3)));
	}
	@Test
	void testGetSortedSetOrdinatoPerPeso_StessoPeso() {
		assertEquals(2, this.borsaPiena.getSortedSetOrdinatoPerPeso().size());
		assertEquals(this.martello, this.borsaPiena.getSortedSetOrdinatoPerPeso().first());
	}
	
	

}
