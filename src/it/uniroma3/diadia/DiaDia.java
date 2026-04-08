package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * 
 * @see Partita
 * @see IOConsole
 * @see Comando
 * 
 * @version 2.0
 */

public class DiaDia {

	private static final String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private static final String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private IOConsole console;

	/**
	 * Crea una nuova istanza del gioco DiaDia. Inizializza la partita e imposta la
	 * console per l'interazione con l'utente.
	 * 
	 * @param console la console di I/O da utilizzare per la comunicazione
	 */

	public DiaDia(IOConsole console) {
		this.console = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione;

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		// se si vuole stampare anche le informazioni della stanza allo stato iniziale
		// this.console.mostraMessaggio(this.partita.getStanzaCorrente().toString());

		do
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.getNome() == null) {
			return false;
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.isFinita()) {
			if(this.partita.vinta()) {
				this.console.mostraMessaggio("Hai vinto!");
			}
			else {
				this.console.mostraMessaggio("Ha esaurito i CFU :(");
			}
			
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:`

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			this.console.mostraMessaggio(elencoComandi[i] + " ");
		this.console.mostraMessaggio("");
	}

	/**
	 * Cerca di prendere un determinato attrezzo, eventualmente rimuovendolo dalla
	 * stanza e aggiungendolo alla borsa.
	 * 
	 * @param attrezzo Una stringa che indica il nome di un attrezzo
	 */

	private void prendi(String attrezzo) {
		if (attrezzo == null) {
			this.console.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		Stanza stanzaAttuale = this.partita.getStanzaCorrente();
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if (stanzaAttuale.hasAttrezzo(attrezzo)) {
			Attrezzo attrezzoDaPrendere = this.partita.getStanzaCorrente().getAttrezzo(attrezzo);
			if (borsa.addAttrezzo(attrezzoDaPrendere)) {
				stanzaAttuale.removeAttrezzo(attrezzoDaPrendere);
				this.console.mostraMessaggio("L'attrezzo '" + attrezzo + "' e' stato spostato nella borsa :)");
			} else
				this.console.mostraMessaggio(
						"L'attrezzo è troppo pesante e non è stato possibile aggiungerlo alla borsa :(");
		} else
			this.console.mostraMessaggio("Attrezzo non presente nella stanza :(");
		this.console.mostraMessaggio(stanzaAttuale.toString());
		// aggiungo un messaggio per far vedere all'utente il contenuto della borsa
		this.console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
	}

	/**
	 * Cerca di posare un oggetto nella stanza, eventualente rimuovendolo dalla
	 * borsa e aggiungendolo alla stanza.
	 */

	private void posa(String attrezzo) {
		if (attrezzo == null) {
			this.console.mostraMessaggio("Cosa vuoi posare?");
			return;
		}
		Stanza stanzaAttuale = this.partita.getStanzaCorrente();
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(attrezzo)) {
			Attrezzo attrezzoDaPosare = borsa.getAttrezzo(attrezzo);
			if (stanzaAttuale.addAttrezzo(attrezzoDaPosare)) {
				borsa.removeAttrezzo(attrezzo);
				this.console.mostraMessaggio("L'attrezzo '" + attrezzo + "' e' stato posato nella stanza :)");
			} else
				this.console.mostraMessaggio("La stanza corrente ha troppi attrezzi, non è stato possibile posarlo :(");
		} else
			this.console.mostraMessaggio("Attrezzo non presente nella borsa :(");
		this.console.mostraMessaggio(stanzaAttuale.toString());
		// aggiungo un messaggio per far vedere all'utente il contenuto della borsa
		this.console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null) {
			this.console.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().diminuisciCfu();
		}
		this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		// aggiungo un messaggio per far vedere all'utente i cfu rimanenti
		this.console.mostraMessaggio("CFU rimanenti: " + this.partita.getGiocatore().getCfu());

	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
		this.partita.setFinita();
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}