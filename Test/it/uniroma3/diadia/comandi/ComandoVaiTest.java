package it.uniroma3.diadia.comandi;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.IO;
import static it.uniroma3.diadia.Direzione.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
/**
 * Classe di testing per ComandoVai. *
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see ComandoVai
 * @version 2.0
 */
public class ComandoVaiTest {

	private Partita partita;
	private ComandoVai comando;
	private IO io;
	private Stanza atrio;
	private Stanza biblioteca;
	private int cfuIniziali;

	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.comando = new ComandoVai();
		this.io = new IOSimulator(new String[0]);
		this.atrio = new Stanza("Atrio");
		this.biblioteca = new Stanza("Biblioteca");
		this.atrio.impostaStanzaAdiacente(NORD, this.biblioteca);
		this.partita.setStanzaCorrente(this.atrio);
		this.cfuIniziali = this.partita.getGiocatore().getCfu();
	}

	@Test
	public void testVaiDirezioneValida() {
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita, this.io);
		assertEquals(this.biblioteca, this.partita.getStanzaCorrente());
		assertEquals(this.cfuIniziali - 1, this.partita.getGiocatore().getCfu());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		this.comando.setParametro("sud");
		this.comando.esegui(this.partita, this.io);
		assertEquals(this.atrio, this.partita.getStanzaCorrente());
		assertEquals(this.cfuIniziali, this.partita.getGiocatore().getCfu());
	}

	@Test
	public void testVaiSenzaDirezione() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita, this.io);
		assertEquals(this.atrio, this.partita.getStanzaCorrente());
		assertEquals(this.cfuIniziali, this.partita.getGiocatore().getCfu());
	}
	/*avevamo 2 opzioni: aggiungere un nuovo metodo in labirinto builder e invocarlo nella funzione test(>>), opppure 
	 * ipotizzare la stanza bloccata con e senza attrezzo direttamente nella funzione di test
	
	*/
	@Test
	public void testVaiStanzaBloccataSenzaAttrezzo() {
		StanzaBloccata stanzaBloccata=new StanzaBloccata("Stanza Bloccata",NORD,"chiave");
		Stanza stanzaSuccessiva=new Stanza("Stanza Successiva");
		stanzaBloccata.impostaStanzaAdiacente(NORD, stanzaSuccessiva);
		this.partita.setStanzaCorrente(stanzaBloccata);
		
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita, this.io);
		
		assertEquals(stanzaBloccata,this.partita.getStanzaCorrente());
	}
	@Test
	public void testVaiStanzaBloccataConAttrezzo() {
		StanzaBloccata stanzaBloccata=new StanzaBloccata("Stanza Bloccata", NORD, "chiave");
		Stanza stanzaSuccessiva=new Stanza("Stanza Successiva");
		stanzaBloccata.impostaStanzaAdiacente(NORD, stanzaSuccessiva);
		stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 1));
		
		this.partita.setStanzaCorrente(stanzaBloccata);
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita, this.io);
		
		assertEquals(stanzaSuccessiva,this.partita.getStanzaCorrente());
	}
}