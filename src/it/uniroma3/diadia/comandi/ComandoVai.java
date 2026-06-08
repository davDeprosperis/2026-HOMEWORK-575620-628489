package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;
/**
 * Classe dedicata al comando "vai". Permette al giocatore di cambiare stanza
 * 
 * @author Davide De Prosperis, Matricola: 575620
 *@author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 * @see Comando
 * @see Partita
 * @see Stanza
 * @see IO
 * @see Direzione
 */

public class ComandoVai extends AbstractComando {
	
	

	@Override
	public void esegui(Partita partita, IO io) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(this.getParametro() == null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		try {
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(this.getParametro().toUpperCase()));
						
		}
		catch(IllegalArgumentException e){
			io.mostraMessaggio("Direzione inesistente! scegli tra 'nord','est','sud','ovest'.");
			return;
		}
		
		if (prossimaStanza == null) {
		    io.mostraMessaggio("Di là c'è un muro! Non puoi andare in quella direzione."); 
		    return;		
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
	public String getNome() {
		return "vai";
	}
}