package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoRegala extends AbstractComando {
    

    @Override
    public void esegui(Partita partita, IO io) {
        if(this.getParametro()==null) {
        	io.mostraMessaggio("Cosa vuoi regalare?");
        	return;
        }
        Stanza stanzaAttuale=partita.getStanzaCorrente();
        Borsa borsa=partita.getGiocatore().getBorsa();
        if(borsa.hasAttrezzo(this.getParametro())) {
        	Attrezzo attrezzoDaRegalare=borsa.getAttrezzo(this.getParametro());
        	if(stanzaAttuale.addAttrezzo(attrezzoDaRegalare)) {
        		borsa.removeAttrezzo(this.getParametro());
        		io.mostraMessaggio("L'attrezzo '"+this.getParametro()+"' non è presente nella borsa");
        	}else {
        		io.mostraMessaggio("L'attrezzo '"+this.getParametro()+"'è stato regalato");
        	}
        	io.mostraMessaggio(stanzaAttuale.toString());
        }
    }

   public String getNome() {
	   return "regala";
   }
}