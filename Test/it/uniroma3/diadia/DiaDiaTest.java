package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import java.util.*;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.giocatore.Giocatore; 
/**
 * Classe di Test di Accettazione per simulare partite intere.
 * @author Davide De Prosperis, Matricola: 575620
 * @author Gabriele Crescenzi, Matricola: 628793
 * @version 3.0
 */
public class DiaDiaTest {
	
	@BeforeEach
	public void setUp() {
		
	}

	@Test
	public void testPartitaVinta() {
		List<String> comandi = List.of("vai nord"); 		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
		assertTrue(io.hasMessaggio("Hai vinto!"));
	}

	@Test
	public void testPartitaEsplorazioneCompleta() {
		List<String> comandi = List.of("guarda", "vai est", "guarda", "prendi osso", "vai ovest", "posa osso", "fine");
		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(); 
		
		assertTrue(io.hasMessaggio("Grazie di aver giocato!"));
	}

	@Test
	public void testPartitaPersaPerCfu() {
		int cfu = Giocatore.CFU_INIZIALI; 
		List<String> comandi = new ArrayList<>(); 
		for (int i = 0; i < cfu; i++) {
			if (i % 2 == 0) {
				comandi.add("vai est");
			} else {
				comandi.add("vai ovest");
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
	    List<String> comandi = List.of("vai nord");
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
	    List<String> comandi = List.of("prendi chiave", "vai nord");
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
	    List<String> comandi = new ArrayList<String>();
	    for (int i = 0; i < cfu; i++) {
	        if (i % 2 == 0) {
	            comandi.add("vai est");
	        } else {
	            comandi.add("vai ovest");
	        }
	    }
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia gioco = new DiaDia(l, io);
	    gioco.gioca();
	    assertTrue(io.hasMessaggio("Hai esaurito i CFU..."));
	}
}