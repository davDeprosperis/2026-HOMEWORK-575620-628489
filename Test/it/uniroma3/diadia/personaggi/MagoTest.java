package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class MagoTest {

	private Partita partita;
	private Mago mago;

	@BeforeEach
	public void setUp() {
		this.partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("Atrio").getLabirinto());
		this.mago = new Mago("Merlino", "un mago saggio", new Attrezzo("bacchetta", 2));
	}

	@Test
	public void testAgisciDonaAttrezzo() {
		this.mago.agisci(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("bacchetta"));
	}

	@Test
	public void testAgisciSenzaAttrezzo() {
		this.mago.agisci(this.partita); // Dona la prima volta
		this.mago.agisci(this.partita); // La seconda volta non ha più nulla
		// La stanza deve contenere solo una bacchetta, non due
		assertEquals(1, this.partita.getStanzaCorrente().getAttrezzi().size());
	}

	@Test
	public void testRiceviRegaloDimezzaPeso() {
		Attrezzo regalo = new Attrezzo("incudine", 10);
		this.mago.riceviRegalo(regalo, this.partita);
		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("incudine"));
		// Verifichiamo che la magia abbia dimezzato il peso
		assertEquals(5, this.partita.getStanzaCorrente().getAttrezzo("incudine").getPeso());
	}
}