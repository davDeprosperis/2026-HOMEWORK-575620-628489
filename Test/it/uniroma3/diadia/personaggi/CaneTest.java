package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaneTest {

	private Partita partita;
	private Cane cane;

	@BeforeEach
	public void setUp() {
		this.partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("Giardino").getLabirinto());
		this.cane = new Cane("Fuffi", "un cane ringhiante");
	}

	@Test
	public void testAgisciMorde() {
		int cfuIniziali = this.partita.getGiocatore().getCfu();
		this.cane.agisci(this.partita);
		assertEquals(cfuIniziali - 1, this.partita.getGiocatore().getCfu());
	}

	@Test
	public void testRiceviRegaloOsso() {
		Attrezzo osso = new Attrezzo("osso", 2);
		this.cane.riceviRegalo(osso, this.partita);
		// Se riceve l'osso, lascia cadere una chiave
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("chiave"));
	}

	@Test
	public void testRiceviRegaloAltro() {
	    Attrezzo sasso = new Attrezzo("sasso", 3);
	    this.cane.riceviRegalo(sasso, this.partita);
	    // il cane morde e non lascia cadere nulla
	    assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("sasso"));
	    assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("chiave"));
	}
	
	@Test
	public void testRiceviRegaloAltroToglieCfu() {
		int cfuIniziali = this.partita.getGiocatore().getCfu();
		Attrezzo sasso = new Attrezzo("sasso", 3);
		
		this.cane.riceviRegalo(sasso, this.partita);
		
		// Verifichiamo che il cane abbia morso togliendo 1 CFU
		assertEquals(cfuIniziali - 1, this.partita.getGiocatore().getCfu());
		// E che non abbia lasciato cadere nessun premio
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("chiave"));
	}
}