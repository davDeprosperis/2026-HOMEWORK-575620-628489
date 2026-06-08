package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

/**
 * Questa classe ha il compito di rappresentare un giocatore, avente una borsa e
 * determinati cfu
 * * @author docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see Borsa
 * @version 2.0
 */
public class Giocatore {
	private Borsa borsa;
	private int cfu;

	/**
	 * Crea un nuovo giocatore. Assegna il numero massimo di CFU iniziali 
	 * leggendoli dal file properties e dota il giocatore di una borsa vuota.
	 */
	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = Configuratore.getCfu();
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Si occupa di diminuire di 1 i cfu dell'utente
	 */
	public void diminuisciCfu() {
		if (this.cfu > 0) {
	        this.cfu--;
	    }
	}

	public Borsa getBorsa() {
		return this.borsa;
	}
}