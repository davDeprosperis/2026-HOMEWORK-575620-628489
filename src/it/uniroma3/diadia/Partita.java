package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see Stanza
 * @see Giocatore
 * @version 2.0
 */
public class Partita {

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;

	/**
	 * Crea una nuova partita usando il builder per generare un labirinto di default.
	 */
	public Partita() {
        // Al posto di new Labirinto() passiamo tramite il Builder per evitare l'errore
		this.labirinto = Labirinto.newBuilder()
                            .addStanzaIniziale("Atrio")
                            .addStanzaVincente("Biblioteca")
                            .getLabirinto();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore = new Giocatore();
	}
	
	public Partita(Labirinto labirinto) {
	    this.labirinto = labirinto;
	    this.stanzaCorrente = labirinto.getStanzaIniziale();
	    this.finita = false;
	    this.giocatore = new Giocatore();
	}

	public void setLabirinto(Labirinto labirinto) {
	    this.labirinto = labirinto;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente.equals(this.labirinto.getStanzaVincente());
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
}