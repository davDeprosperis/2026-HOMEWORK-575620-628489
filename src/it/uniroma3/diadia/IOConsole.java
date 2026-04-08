package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe che gestisce l'interazione testuale con l'utente (Input/Output).
 * Centralizza le operazioni di lettura da tastiera e stampa a schermo,
 * permettendo di disaccoppiare la logica del gioco dalla console fisica.
 *
 * @author docente di POO
 * 
 * @version 2.0
 */

public class IOConsole {
	/**
	 * Si occupa di stampare a schermo un determinato messaggio
	 * 
	 * @param msg Messaggio che si vuole stampare
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	/**
	 * Si occupa di leggere una riga attraverso uno scanner di linee
	 * 
	 * @return una stringa contenente la riga appena letta
	 */
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}
