package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.IO;

/**
 * Classe dedicata al comando "posa". Permette al giocatore di togliere un
 * attrezzo dalla borsa e posarlo nella stanza corrente.
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 * @see Partita
 * @see Comando
 * @see Stanza
 * @see Attrezzo
 * @see Borsa
 * @see IO
 */
public class ComandoPosa extends AbstractComando {
	

	/**
	 * Cerca di posare un oggetto nella stanza, eventualente rimuovendolo dalla
	 * borsa e aggiungendolo alla stanza.
	 * 
	 * @param partita La partita in corso da cui estrarre stanza e giocatore
	 * @param io     interfaccia dedicata alla console
	 */

	@Override
	public void esegui(Partita partita, IO io) {
		if (this.getParametro() == null) {
			io.mostraMessaggio("Cosa vuoi posare?");
			return;
		}

		Stanza stanzaAttuale = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();

		if (borsa.hasAttrezzo(this.getParametro())) {
			Attrezzo attrezzoDaPosare = borsa.getAttrezzo(this.getParametro());
			if (stanzaAttuale.addAttrezzo(attrezzoDaPosare)) {
				borsa.removeAttrezzo(this.getParametro());
				io.mostraMessaggio("L'attrezzo '" + this.getParametro() + "' e' stato posato nella stanza :)");
			} else {
				io.mostraMessaggio("La stanza corrente ha troppi attrezzi, non è stato possibile posarlo :(");
			}
		} else {
			io.mostraMessaggio("Attrezzo non presente nella borsa :(");
		}

		io.mostraMessaggio(stanzaAttuale.toString());
	}
	public String getNome() {
		return "posa";
	}

}