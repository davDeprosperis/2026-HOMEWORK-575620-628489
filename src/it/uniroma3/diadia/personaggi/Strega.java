package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Direzione;
import java.util.List;
import java.util.ArrayList;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATA = "Sei stato molto educato a salutarmi! Come premio, ti teletrasporto in una stanza ricca di oggetti!";
	private static final String MESSAGGIO_NON_SALUTATA = "Maleducato! Non si interagisce con una signora senza prima presentarsi! Ti esilio in un posto desolato!";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		
		// Recuperiamo le stanze adiacenti usando i metodi già esistenti in Stanza
		List<Stanza> stanzeAdiacenti = new ArrayList<>();
		for (Direzione dir : stanzaCorrente.getDirezioni()) {
			stanzeAdiacenti.add(stanzaCorrente.getStanzaAdiacente(dir));
		}

		if (stanzeAdiacenti.isEmpty()) {
			return "La mia magia qui non funziona, non ci sono stanze adiacenti in cui mandarti!";
		}

		Stanza stanzaDestinazione = null;

		if (this.haSalutato()) {
			// Comportamento Buono: cerca la stanza con PIÙ attrezzi
			int maxAttrezzi = -1;
			for (Stanza s : stanzeAdiacenti) {
				int numeroAttrezzi = s.getAttrezzi().size(); 
				if (numeroAttrezzi > maxAttrezzi) {
					maxAttrezzi = numeroAttrezzi;
					stanzaDestinazione = s;
				}
			}
			if (stanzaDestinazione != null) {
				partita.setStanzaCorrente(stanzaDestinazione);
			}
			return MESSAGGIO_SALUTATA;
			
		} else {
			// Comportamento Cattivo: cerca la stanza con MENO attrezzi
			int minAttrezzi = Integer.MAX_VALUE;
			for (Stanza s : stanzeAdiacenti) {
				int numeroAttrezzi = s.getAttrezzi().size();
				if (numeroAttrezzi < minAttrezzi) {
					minAttrezzi = numeroAttrezzi;
					stanzaDestinazione = s;
				}
			}
			if (stanzaDestinazione != null) {
				partita.setStanzaCorrente(stanzaDestinazione);
			}
			return MESSAGGIO_NON_SALUTATA;
		}
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "HIHIHI! Sei uno sciocco! Questo attrezzo è mio ora! Sparisce nel nulla...";
		// Non aggiungiamo l'attrezzo alla stanza, sparisce effettivamente nel vuoto.
	}
}