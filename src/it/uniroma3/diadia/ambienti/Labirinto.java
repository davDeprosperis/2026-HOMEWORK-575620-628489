package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.Direzione.*;

/***
 * Questa classe ha la responsabilita' di creare e gestire la mappa del gioco
 *
 * @author docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Stanza
 * @see Direzione
 * @version 3.0
 */

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	/**
	 * Crea e configura il labirinto. Si occupa dela creazione di tutte le stanze,
	 * del loro collegamento e della definizione dei punti di inizio e di fine del
	 * gioco.
	 */
	public Labirinto() {
		this.init();
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void init() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente(NORD, biblioteca);
		atrio.impostaStanzaAdiacente(EST, aulaN11);
		atrio.impostaStanzaAdiacente(SUD, aulaN10);
		atrio.impostaStanzaAdiacente(OVEST, laboratorio);
		aulaN11.impostaStanzaAdiacente(EST, laboratorio);
		aulaN11.impostaStanzaAdiacente(OVEST, atrio);
		aulaN10.impostaStanzaAdiacente(NORD, atrio);
		aulaN10.impostaStanzaAdiacente(EST, aulaN11);
		aulaN10.impostaStanzaAdiacente(OVEST, laboratorio);
		laboratorio.impostaStanzaAdiacente(EST, atrio);
		laboratorio.impostaStanzaAdiacente(OVEST, aulaN11);
		biblioteca.impostaStanzaAdiacente(SUD, atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio;
		this.stanzaVincente = biblioteca;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	//aggiungo i metodi setter per far funzionare getLabirinto() in LabirintoBuilder
	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente=stanza;
	}
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale=stanza;
	}
	

}
