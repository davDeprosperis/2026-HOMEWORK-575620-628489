package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;

import java.util.ArrayList;

public class ComandoRegalaTest {

	private Partita partita;
	private ComandoRegala comando;
	private IOSimulator io;

	@BeforeEach
	public void setUp() {
		this.partita = new Partita(new LabirintoBuilder().addStanzaIniziale("Atrio").getLabirinto());
		this.comando = new ComandoRegala();
		this.io = new IOSimulator(new ArrayList<>());
	}

	@Test
	public void testRegalaSenzaPersonaggio() {
		// Metto un attrezzo nella borsa, ma non c'è nessuno nella stanza
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("mela", 1));
		this.comando.setParametro("mela");
		this.comando.esegui(this.partita, this.io);
		
		// Verifico che l'attrezzo sia rimasto nella borsa
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("mela"));
	}

	@Test
	public void testRegalaConPersonaggioAttrezzoPresente() {
		// Aggiungo il Mago e metto una mela nella borsa del giocatore
		this.partita.getStanzaCorrente().setPersonaggio(new Mago("Merlino", "un mago", new Attrezzo("bacchetta", 2)));
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("mela", 10));
		
		this.comando.setParametro("mela");
		this.comando.esegui(this.partita, this.io);
		
		// La mela non deve essere più nella borsa
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("mela"));
		
		// Il mago dimezza il peso e la mette nella stanza. Controlliamo se è caduta a terra col peso giusto (5)!
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("mela"));
		assertEquals(5, this.partita.getStanzaCorrente().getAttrezzo("mela").getPeso());
	}

	@Test
	public void testRegalaAttrezzoNonInBorsa() {
		this.partita.getStanzaCorrente().setPersonaggio(new Mago("Merlino", "un mago", null));
		
		this.comando.setParametro("spada"); // Non ho la spada in borsa
		this.comando.esegui(this.partita, this.io);
		
		// Assicuriamoci che la finta spada non sia comparsa magicamente nella stanza
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
}