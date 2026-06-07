package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

    @Override
    public void esegui(Partita partita, IO io) {
        if (this.getParametro() == null) {
            io.mostraMessaggio("Cosa vuoi regalare?");
            return;
        }

        // 1. C'è qualcuno nella stanza?
        AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
        if (personaggio == null) {
            io.mostraMessaggio("Non c'è nessuno a cui regalare qualcosa in questa stanza!");
            return;
        }

        Borsa borsa = partita.getGiocatore().getBorsa();
        
        // 2. Hai davvero questo attrezzo nella borsa?
        if (borsa.hasAttrezzo(this.getParametro())) {
        	
            // Prendo l'attrezzo e lo rimuovo dalla mia borsa
            Attrezzo attrezzoDaRegalare = borsa.getAttrezzo(this.getParametro());
            borsa.removeAttrezzo(this.getParametro());
            
            // 3. Consegno l'attrezzo al personaggio e stampo la sua reazione polimorfica
            String risposta = personaggio.riceviRegalo(attrezzoDaRegalare, partita);
            io.mostraMessaggio(risposta);
            
        } else {
            io.mostraMessaggio("L'attrezzo '" + this.getParametro() + "' non è presente nella tua borsa.");
        }
    }

    @Override
    public String getNome() {
        return "regala";
    }
}