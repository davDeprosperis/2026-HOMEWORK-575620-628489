package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comando;

/**
 * Classe di testing
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Comando
 * @version 3.0
 */


class ComandoTest {

	@Test
	void testLetturaNomeDiUnComandoValidoDiDueParole() {
		assertEquals("vai", (new Comando("vai nord")).getNome());
		
	}
	
	@Test
	void testLetturaParametroDiUnComandoValidoDiDueParole() {
		assertEquals("nord", (new Comando("vai nord")).getParametro());
	}
	

	@Test
	void testUnComandoDiUnaParolaDeveContenereIlNome() {
		assertEquals("aiuto", (new Comando("aiuto")).getNome());
	}
	
	@Test
	void testUnComandoDiUnaParolaNonDeveContenereIlParametro() {
		assertNull(new Comando("aiuto").getParametro());
	}

	@Test
	void testUnComandoVuotoNonDeveContenereIlNome() {
		assertNull((new Comando("")).getNome());
	}
		
	@Test
	void testUnComandoVuotoNonDeveContenereIlParametro() {
		assertNull((new Comando("")).getParametro());
	}
	
	@Test
	void testAdUnComandoConSpaziInEccessoDeveEssereLettoCorrettamenteIlNome() {
		assertEquals("aiuto", (new Comando(" aiuto ")).getNome());
	}
	
	@Test
	void testAdUnComandoConSpaziInEccessoDeveEssereLettoCorrettamenteIlParametro() {
		assertEquals("nord", (new Comando(" vai   nord ")).getParametro());
	}
	
}
