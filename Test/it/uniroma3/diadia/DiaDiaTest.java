package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Classe di Test di Accettazione per simulare partite intere.
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 3.0
 */
public class DiaDiaTest {
	
	@BeforeEach
	public void setUp() {
		
	}


	@Test
	public void testPartitaEsplorazioneCompleta() {
        // Creiamo un labirinto con un osso a Est per far funzionare l'esplorazione
        Labirinto lab = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("Campus")
                .addAdiacenza("Atrio", "Campus", "est")
                .addAdiacenza("Campus", "Atrio", "ovest")
                .addAttrezzo("osso", 1) 
                .getLabirinto();

		List<String> comandi = Arrays.asList("guarda", "vai est", "guarda", "prendi osso", "vai ovest", "posa osso", "fine");
		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(lab, io);
		gioco.gioca(); 
		
		assertTrue(io.hasMessaggio("Grazie di aver giocato!"));
	}

	@Test
	public void testPartitaPersaPerCfu() {
		int cfu = Configuratore.getCfu(); 
		List<String> comandi = new ArrayList<>(); 
		for (int i = 0; i < cfu; i++) {
			if (i % 2 == 0) {
				comandi.add("vai est");
			} else {
				comandi.add("vai ovest");
			}
		}
        // Creiamo un labirinto dove le direzioni Est e Ovest esistono fisicamente
        Labirinto lab = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("StanzaEst")
                .addAdiacenza("Atrio", "StanzaEst", "est")
                .addAdiacenza("StanzaEst", "Atrio", "ovest")
                .getLabirinto();

		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(lab, io);
		gioco.gioca(); 
		
		assertTrue(io.hasMessaggio("Hai esaurito i CFU..."));
	}
	
	//test di simulazione per partite intere
	
	@Test
	public void testPartitaVintaConBuilder() {
	    Labirinto l = Labirinto.newBuilder()
	        .addStanzaIniziale("salotto")
	        .addStanzaVincente("camera")
	        .addAdiacenza("salotto", "camera", "nord")
	        .getLabirinto();
	    List<String> comandi = Arrays.asList("vai nord");
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia gioco = new DiaDia(l, io);
	    gioco.gioca();
	    assertTrue(io.hasMessaggio("Hai vinto!"));
	}

	@Test
	public void testPrendiAttrezzoEVinci() {
	    Labirinto l = Labirinto.newBuilder()
	        .addStanzaIniziale("salotto")
	        .addAttrezzo("chiave", 1)
	        .addStanzaVincente("camera")
	        .addAdiacenza("salotto", "camera", "nord")
	        .getLabirinto();
	    List<String> comandi = Arrays.asList("prendi chiave", "vai nord");
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia gioco = new DiaDia(l, io);
	    gioco.gioca();
	    assertTrue(io.hasMessaggio("Hai vinto!"));
	}

	@Test
	public void testPartitaPersa() {
	    Labirinto l = Labirinto.newBuilder()
	        .addStanzaIniziale("salotto")
	        .addStanza("cucina")
	        .addAdiacenza("salotto", "cucina", "est")
	        .addAdiacenza("cucina","salotto","ovest")
	        .addStanzaVincente("camera")
	        .getLabirinto();
	    int cfu = Configuratore.getCfu();
	    List<String> comandi = new ArrayList<>();
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