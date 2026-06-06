package it.uniroma3.diadia.comandi;

/**
 * Interfaccia dedicata alla fabbrica di comandi
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @version 3.0
 * 
 */

public interface FabbricaDiComandi {
	public Comando costruisciComando(String istruzione) throws Exception; // (>> eccezioni)
}
