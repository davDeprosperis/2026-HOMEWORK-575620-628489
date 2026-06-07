package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class AbstractComandoTest {

	private AbstractComando comandoFittizio;
	
	private class ComandoFittizio extends AbstractComando {
		@Override
		public void esegui(Partita partita, IO io) {
			// Comportamento vuoto: ci serve solo per testare la superclasse
		}

		@Override
		public String getNome() {
			return "fittizio";
		}
	}

	@BeforeEach
	public void setUp() {
		this.comandoFittizio = new ComandoFittizio();
	}

	@Test
	public void testGetParametroInizialmenteNull() {
		assertNull(this.comandoFittizio.getParametro());
	}

	@Test
	public void testSetEGetParametro() {
		this.comandoFittizio.setParametro("nord");
		assertEquals("nord", this.comandoFittizio.getParametro());
	}

	@Test
	public void testGetComandiDisponibiliNonVuoto() {
		List<String> comandi = AbstractComando.getComandiDisponibili();
		assertNotNull(comandi);
		assertFalse(comandi.isEmpty());
	}
	
	@Test
	public void testGetComandiDisponibiliContieneComandiBase() {
		List<String> comandi = AbstractComando.getComandiDisponibili();
		// Testiamo che almeno alcuni comandi fondamentali vengano trovati
		assertTrue(comandi.contains("aiuto"));
		assertTrue(comandi.contains("vai"));
	}
}