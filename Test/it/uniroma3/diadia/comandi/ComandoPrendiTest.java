package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;

import java.util.ArrayList;

/**
 * Classe di testing
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see ComandoPrendi
 * @version 3.0
 */
public class ComandoPrendiTest {

	private Partita partita;
	private ComandoPrendi comando;
	private IO io;

	@BeforeEach
	public void setUp() {
		// Usiamo il Builder per creare il labirinto e posizionare subito l'attrezzo
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 1) 
				.getLabirinto();
		
		this.partita = new Partita(labirinto);
		this.comando = new ComandoPrendi();
		this.io = new IOSimulator(new ArrayList<String>());
	}

	@Test
	public void testPrendiAttrezzoPresente() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita, this.io);

		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testPrendiAttrezzoAssente() {
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita, this.io);

		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
}