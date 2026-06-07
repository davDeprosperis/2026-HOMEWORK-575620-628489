package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.*;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_MORSO = "Grrr... Arf! Il cane ti ha morso! Hai perso un CFU.";

	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		// Recupera i CFU attuali del giocatore e ne sottrae 1
		int cfuAttuali = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfuAttuali - 1);
		
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if (attrezzo.getNome().equals("osso")) {
			// Accetta il cibo preferito e butta a terra un attrezzo
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("chiave", 1));
			msg = "Arf! Il cane scodinzola felice mangiando l'osso e lascia cadere una chiave a terra.";
		} else {
			// Morde e toglie un CFU per tutto il resto
			int cfuAttuali = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfuAttuali - 1);
			msg = "Grrr... Non è il suo cibo preferito! Il cane ti morde! Hai perso 1 CFU.";
		}
		return msg;
	}
}