package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Labirinto;

import java.util.ArrayList;

/**
 * Classe di testing per ComandoVai.
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see ComandoVai
 * @version 3.0
 */
public class ComandoVaiTest {

	private ComandoVai comando;
	private IO io;

	@BeforeEach
	public void setUp() {
		this.comando = new ComandoVai();
		this.io = new IOSimulator(new ArrayList<String>()); 
	}

	@Test
	public void testVaiDirezioneValida() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		
		Partita partita = new Partita(labirinto);
		int cfuIniziali = partita.getGiocatore().getCfu();
		
		this.comando.setParametro("nord");
		this.comando.esegui(partita, this.io);
		
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		
		Partita partita = new Partita(labirinto);
		int cfuIniziali = partita.getGiocatore().getCfu();
		
		this.comando.setParametro("sud");
		this.comando.esegui(partita, this.io);
		
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
		assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
	}

	@Test
	public void testVaiSenzaDirezione() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		
		Partita partita = new Partita(labirinto);
		int cfuIniziali = partita.getGiocatore().getCfu();
		
		this.comando.setParametro(null);
		this.comando.esegui(partita, this.io);
		
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
		assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
	}

	@Test
	public void testVaiStanzaBloccataSenzaAttrezzo() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaBloccataIniziale("Stanza Bloccata", "nord", "chiave")
				.addStanza("Stanza Successiva")
				.addAdiacenza("Stanza Bloccata", "Stanza Successiva", "nord")
				.getLabirinto();
		
		Partita partita = new Partita(labirinto);
		
		this.comando.setParametro("nord");
		this.comando.esegui(partita, this.io);
		
		assertEquals("Stanza Bloccata", partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiStanzaBloccataConAttrezzo() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaBloccataIniziale("Stanza Bloccata", "nord", "chiave")
				.addAttrezzo("chiave", 1) 
				.addStanza("Stanza Successiva")
				.addAdiacenza("Stanza Bloccata", "Stanza Successiva", "nord")
				.getLabirinto();
		
		Partita partita = new Partita(labirinto);
		
		this.comando.setParametro("nord");
		this.comando.esegui(partita, this.io);
		
		assertEquals("Stanza Successiva", partita.getStanzaCorrente().getNome());
	}
}