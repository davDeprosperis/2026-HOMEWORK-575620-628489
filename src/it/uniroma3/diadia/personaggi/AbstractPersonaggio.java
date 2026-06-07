package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome() + ".");
		if (!haSalutato) {
			risposta.append(" " + this.presentazione);
			this.haSalutato = true;
		} else {
			risposta.append(" Ci siamo gia' presentati!");
		}
		return risposta.toString();
	}

	// Metodo polimorfico che ogni personaggio implementerà a modo suo
	public abstract String agisci(Partita partita);
	
	/**
	 * Metodo che modella la ricezione di un regalo da parte del personaggio.
	 * Ogni personaggio reagirà in modo diverso.
	 */
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);

	@Override
	public String toString() {
		return this.getNome();
	}
}