package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 */

public class Comando {

	private String nome;
	private String parametro;

	/**
	 * Crea un oggetto Comando analizzando la stringa inserita dall'utente.
	 * 
	 * @param istruzione Istruzione data dall'utente attraverso una stringa
	 */
	public Comando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);

		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next();

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
	}

	public String getNome() {
		return this.nome;
	}

	public String getParametro() {
		return this.parametro;
	}

	/**
	 * Verifica che l'inserimento dell'utente sia vuoto o privo di un nome valido
	 * 
	 * @return true se il comando inserito è sconosciuto, false altrimenti
	 */
	public boolean sconosciuto() {
		return (this.nome == null);
	}
}