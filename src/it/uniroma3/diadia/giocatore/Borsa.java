package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Classe che modella la borsa del giocatore.
 * Permette di aggiungere e rimuovere attrezzi, tenendo conto
 * di un peso massimo trasportabile.
 *
 * @author  docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @see    Attrezzo
 * @version 2.0
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	/**
     *Crea una borsa con una capacita' di carico default.
     */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	/**
     * Crea una borsa con una capacita' di carico definita.
     * * @param pesoMax Il limite di peso massimo trasportabile nella borsa
     */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
/**
 * Aggiunge, se possibile, un attrezzo alla borsa
 * 
 * @param attrezzo
 * @return true se l'attrezzo è stato aggiunto, false altrimenti
 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
/**
 * 
 * @return peso massimo trasportabile dalla borsa
 */
	public int getPesoMax() {
		return pesoMax;
	}
/**
 * Ha lo scopo di ritornare l'attrezzo avente il nome offerto come parametro
 * @param nomeAttrezzo Il nome dell'attrezzo
 * @return l'attrezzo avente nome cercato, se presente, altrimenti null
 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
/**
 * Ha lo scopo di ritornare il peso totale degli oggetti presenti nella borsa
 * @return peso della borsa
 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
/**
 * Verifica che la borsa sia vuota
 * 
 * @return true se la borsa e'vuota, false altrmenti
 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
/**
 * Cerca un oggetto all'interno della borsa
 * @param nomeAttrezzo Il nome dell'attrezzo da cercare
 * @return true se l'attrezzo e' presente nella borsa, false altrimenti
 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	
	/**
	 * Cerca di rimuovere un determinato attrezzo avente nome fornito come parametro
	 * ritornando eventualmente l'attrezzo appena rimosso.
	 * @param nomeAttrezzo Il nome dell'attrezzo che si vuole rimuovere
	 * @return l'attrezzo appena rimosso se la rimozione e' avvenuta, null altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for(int i=0;i<this.numeroAttrezzi;i++) {
			if (nomeAttrezzo.equals(this.attrezzi[i].getNome())) { //ho trovato l'attrezzo, ora lo devo solo eliminare
				a = this.attrezzi[i];
				for(int j=i;j<this.numeroAttrezzi-1;j++) {
					this.attrezzi[j]=this.attrezzi[j+1];
				}
				this.attrezzi[this.numeroAttrezzi-1]=null;
				this.numeroAttrezzi --;
				return a;
			}
		}
		return a;
	}

	/**
	 * Restituisce una rappresentazione testuale del contenuto della borsa.
	 * @return il contenuto della borsa (attrezzi e peso) sotto forma di stringa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}
