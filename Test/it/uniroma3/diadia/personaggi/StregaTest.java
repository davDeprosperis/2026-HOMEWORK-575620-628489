package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StregaTest {

	private Partita partita;
	private Strega strega;

	@BeforeEach
	public void setUp() {
		// Creiamo un labirinto con una stanza ricca e una povera
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				
				.addStanza("StanzaRicca")
				.addAttrezzo("spada", 3)
				.addAttrezzo("scudo", 5)
				
				.addStanza("StanzaPovera")
				// Nessun attrezzo
				
				.addAdiacenza("Atrio", "StanzaRicca", "nord")
				.addAdiacenza("Atrio", "StanzaPovera", "sud")
				.getLabirinto();

		this.partita = new Partita(labirinto);
		this.strega = new Strega("Morgana", "una strega dispettosa");
	}

	@Test
	public void testAgisciSalutata() {
		this.strega.saluta(); // Il giocatore è educato
		this.strega.agisci(this.partita);
		// Deve finire nella stanza con PIÙ attrezzi
		assertEquals("StanzaRicca", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testAgisciNonSalutata() {
		// Il giocatore NON saluta
		this.strega.agisci(this.partita);
		// Deve finire nella stanza con MENO attrezzi
		assertEquals("StanzaPovera", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testRiceviRegaloDistrugge() {
		Attrezzo regalo = new Attrezzo("anello", 1);
		this.strega.riceviRegalo(regalo, this.partita);
		// L'attrezzo sparisce, non deve essere nella stanza
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("anello"));
	}
}