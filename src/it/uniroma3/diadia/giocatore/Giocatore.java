package it.uniroma3.diadia.giocatore;

/**
 * Questa classe ha il compito di rappresentare un giocatore, avente una borsa e
 * determinati cfu
 * 
 * @author docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Borsa
 * @version base
 */

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private Borsa borsa;
	private int cfu;

	/**
	 * Crea un nuovo giocatore con i valori iniziali di default. Assegna il numero
	 * massimo di CFU iniziali e dota il giocatore di una borsa vuota.
	 */
	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
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
