package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
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
	// ESERCIZIO 5 HOMEWORK C: batterie di test per i metodi esercizio 3 e 4

	// getContenutoOrdinatoPerPeso()
	@Test
	void testGetContenutoOrdinatoPerPeso_OrdinamentoPerPeso() {
	    Borsa borsa = new Borsa(20);
	    Attrezzo piombo = new Attrezzo("piombo", 10);
	    Attrezzo piuma = new Attrezzo("piuma", 1);
	    borsa.addAttrezzo(piombo);
	    borsa.addAttrezzo(piuma);
	    List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();
	    assertEquals(piuma, lista.get(0));
	    assertEquals(piombo, lista.get(1));
	}

	@Test
	void testGetContenutoOrdinatoPerPeso_StessoPesoOrdinaPerNome() {
	    List<Attrezzo> lista = this.borsaPiena.getContenutoOrdinatoPerPeso();
	    assertEquals(this.martello, lista.get(0)); // "martello" < "sasso"
	    assertEquals(this.sasso, lista.get(1));
	}

	// getContenutoOrdinatoPerNome()
	@Test
	void testGetContenutoOrdinatoPerNome_OrdinamentoAlfabetico() {
	    Borsa borsa = new Borsa(20);
	    Attrezzo zaffiro = new Attrezzo("zaffiro", 3);
	    Attrezzo ago = new Attrezzo("ago", 5);
	    borsa.addAttrezzo(zaffiro);
	    borsa.addAttrezzo(ago);
	    SortedSet<Attrezzo> set = borsa.getContenutoOrdinatoPerNome();
	    assertEquals(ago, set.first());
	}

	@Test
	void testGetContenutoOrdinatoPerNome_DimensioneCorretta() {
	    SortedSet<Attrezzo> set = this.borsaPiena.getContenutoOrdinatoPerNome();
	    assertEquals(2, set.size());
	}

	// getContenutoRaggruppatoPerPeso()
	@Test
	void testGetContenutoRaggruppatoPerPeso_ChiaviCorrette() {
	    Borsa borsa = new Borsa(20);
	    borsa.addAttrezzo(new Attrezzo("piombo", 10));
	    borsa.addAttrezzo(new Attrezzo("piuma", 1));
	    Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();
	    assertTrue(mappa.containsKey(10));
	    assertTrue(mappa.containsKey(1));
	}

	@Test
	void testGetContenutoRaggruppatoPerPeso_StessoPesoNelloStessoSet() {
	    Map<Integer, Set<Attrezzo>> mappa = this.borsaPiena.getContenutoRaggruppatoPerPeso();
	    assertEquals(1, mappa.keySet().size());
	    assertEquals(2, mappa.get(1).size());   
	}

	//ESERCIZIO 4

	// getSortedSetOrdinatoPerPeso()
	@Test
	void testGetSortedSetOrdinatoPerPeso_OrdinamentoPerPeso() {
	    Borsa borsa = new Borsa(20);
	    Attrezzo piombo = new Attrezzo("piombo", 10);
	    Attrezzo piuma = new Attrezzo("piuma", 1);
	    borsa.addAttrezzo(piombo);
	    borsa.addAttrezzo(piuma);
	    SortedSet<Attrezzo> set = borsa.getSortedSetOrdinatoPerPeso();
	    assertEquals(piuma, set.first());
	    assertEquals(piombo, set.last());
	}

	@Test
	void testGetSortedSetOrdinatoPerPeso_DueAttrezziStessoPesoRimangonoDistinti() {
	    SortedSet<Attrezzo> set = this.borsaPiena.getSortedSetOrdinatoPerPeso();
	    assertEquals(2, set.size()); 
	}
	
	

}
