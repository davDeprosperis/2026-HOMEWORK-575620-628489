package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo. Gli attrezzi possono trovarsi
 * all'interno delle stanze del labirinto oppure nella borsa del giocatore. Ogni
 * attrezzo ha un nome che lo identifica e un peso.
 *
 * @author docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see Stanza
 * @see Borsa
 * @version 2.0
 */

public class Attrezzo {

	private String nome;
	private int peso;

	/**
	 * Crea un oggetto di tipo Attrezzo, avente nome e peso
	 * 
	 * @param nome Il nome che identifica l'attrezzo
	 * @param peso Il peso dell'attrezzo espresso in kg
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * 
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * 
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * 
	 * @return la rappresentazione dell'attrezzo attraverso una stringa stringa
	 */
	public String toString() {
		return this.getNome() + " (" + this.getPeso() + "kg)";
	}

}