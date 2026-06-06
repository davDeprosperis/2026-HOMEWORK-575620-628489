package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.giocatore.Giocatore; 

/**
 * Classe di Test di Accettazione per simulare partite intere.
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @version 3.0
 */
public class DiaDiaTest {

	@Test
	public void testPartitaVinta() {
		String[] comandi = {"vai nord"}; 
		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
		assertTrue(io.hasMessaggio("Hai vinto!"));
	}

	@Test
	public void testPartitaEsplorazioneCompleta() {
		String[] comandi = {"guarda", "vai est", "guarda", "prendi osso", "vai ovest", "posa osso", "fine"};
		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
		assertTrue(io.hasMessaggio("Grazie di aver giocato!"));
	}

	@Test
	public void testPartitaPersaPerCfu() {
		int cfu = Giocatore.CFU_INIZIALI; 
		String[] comandi = new String[cfu + 1]; 
		for (int i = 0; i < cfu; i++) { //facciamo andare in loop a destra e sinistra per finire i cfu
			if (i % 2 == 0) {
				comandi[i] = "vai est";
			} else {
				comandi[i] = "vai ovest";
			}
		}
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
		assertTrue(io.hasMessaggio("Hai esaurito i CFU..."));
	}
	//test di simulazione per partite intere
	@Test
	public void testPartitaVintaConBuilder() {
	    Labirinto l = new LabirintoBuilder()
	        .addStanzaIniziale("salotto")
	        .addStanzaVincente("camera")
	        .addAdiacenza("salotto", "camera", "nord")
	        .getLabirinto();
	    String[] comandi = {"vai nord"};
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia gioco = new DiaDia(l, io);
	    gioco.gioca();
	    assertTrue(io.hasMessaggio("Hai vinto!"));
	}

	@Test
	public void testPrendiAttrezzoEVinci() {
	    Labirinto l = new LabirintoBuilder()
	        .addStanzaIniziale("salotto")
	        .addAttrezzo("chiave", 1)
	        .addStanzaVincente("camera")
	        .addAdiacenza("salotto", "camera", "nord")
	        .getLabirinto();
	    String[] comandi = {"prendi chiave", "vai nord"};
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia gioco = new DiaDia(l, io);
	    gioco.gioca();
	    assertTrue(io.hasMessaggio("Hai vinto!"));
	}

	@Test
	public void testPartitaPersa() {
	    Labirinto l = new LabirintoBuilder()
	        .addStanzaIniziale("salotto")
	        .addStanza("cucina")
	        .addAdiacenza("salotto", "cucina", "est")
	        .addAdiacenza("cucina","salotto","ovest")
	        .addStanzaVincente("camera")
	        .getLabirinto();
	    int cfu = Giocatore.CFU_INIZIALI;
	    String[] comandi = new String[cfu];
	    for (int i = 0; i < cfu; i++) {
	        if (i % 2 == 0) {
	            comandi[i] = "vai est";
	        } else {
	            comandi[i] = "vai ovest";
	        }
	    }
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia gioco = new DiaDia(l, io);
	    gioco.gioca();
	    assertTrue(io.hasMessaggio("Hai esaurito i CFU..."));
	}
}