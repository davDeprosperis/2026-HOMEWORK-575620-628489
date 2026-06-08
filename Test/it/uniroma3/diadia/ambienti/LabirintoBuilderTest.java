package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LabirintoBuilderTest {
	
	@Test
	void testMonolocale() {
		Labirinto l = Labirinto.newBuilder().addStanzaIniziale("salotto").addStanzaVincente("salotto").getLabirinto();
		assertEquals("salotto", l.getStanzaIniziale().getNome());
		assertEquals("salotto", l.getStanzaVincente().getNome());
	}

	@Test
	void testBilocale_StanzaInizialeEVincente() {
		Labirinto l = Labirinto.newBuilder().addStanzaIniziale("salotto").addStanzaVincente("camera").getLabirinto();
		assertEquals("salotto", l.getStanzaIniziale().getNome());
		assertEquals("camera", l.getStanzaVincente().getNome());
	}

	@Test
	void testAddAdiacenza() {
		Labirinto l = Labirinto.newBuilder().addStanzaIniziale("salotto").addStanzaVincente("camera")
				.addAdiacenza("salotto", "camera", "nord").getLabirinto();
		assertEquals("camera", l.getStanzaIniziale().getStanzaAdiacente(it.uniroma3.diadia.Direzione.NORD).getNome());
	}

	@Test
	void testAddAttrezzo() {
		Labirinto l = Labirinto.newBuilder().addStanzaIniziale("salotto").addStanzaVincente("camera")
				.addAttrezzo("letto", 10).getLabirinto();
		assertTrue(l.getStanzaVincente().hasAttrezzo("letto"));
	}
}