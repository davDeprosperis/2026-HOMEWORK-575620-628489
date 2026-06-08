package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe di testing
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see FabbricaDiComandiFisarmonica
 * @version 3.0
 */

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandi fabbrica;

	@BeforeEach
	public void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testCostruisciComando_SenzaParametro() throws Exception {
		Comando c = this.fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto", c.getNome());
		assertNull(c.getParametro());
	}

	@Test
	public void testCostruisciComando_ConParametro() throws Exception{
		Comando c = this.fabbrica.costruisciComando("vai nord");
		assertEquals("vai", c.getNome());
		assertEquals("nord", c.getParametro());
	}

	@Test
	public void testCostruisciComando_NonValido() throws Exception{
		Comando c = this.fabbrica.costruisciComando("corri");
		assertEquals("non valido", c.getNome());
	}
}