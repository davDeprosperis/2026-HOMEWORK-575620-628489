package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;


import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

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
 * @version 3.0
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

	private Partita partita;
	private IO io;

	/**
	 * Crea una nuova istanza del gioco DiaDia. Inizializza la partita e imposta la
	 * console per l'interazione con l'utente.
	 * 
	 * @param io l'interfaccia IO da utilizzare per la comunicazione
	 */

	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita();
	}
	public DiaDia(Labirinto labirinto, IO io) {
	    this.io = io;
	    this.partita = new Partita(labirinto);
	}
	public void gioca() {
		String istruzione;

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 * altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		
		try {
			// Proviamo a costruire ed eseguire il comando
			comandoDaEseguire = factory.costruisciComando(istruzione);
			comandoDaEseguire.esegui(this.partita, this.io);
		} catch (Exception e) {
			// Se la fabbrica lancia un'eccezione, la catturiamo e avvisiamo l'utente
			this.io.mostraMessaggio("Comando non valido o errore di esecuzione.");
			return false; // Restituiamo false per far continuare il ciclo del gioco
		}

		if (this.partita.vinta())
			this.io.mostraMessaggio("Hai vinto!");
		if (this.partita.getGiocatore().getCfu() == 0)
			this.io.mostraMessaggio("Hai esaurito i CFU...");
            
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
	    IO io = new IOConsole();
	    Labirinto labirinto = new LabirintoBuilder()
	        .addStanzaIniziale("Atrio")
	        .addStanzaVincente("Biblioteca")
	        .addAdiacenza("Atrio", "Biblioteca", "nord")
	        .getLabirinto();
	    DiaDia gioco = new DiaDia(labirinto, io);
	    gioco.gioca();
	}
}