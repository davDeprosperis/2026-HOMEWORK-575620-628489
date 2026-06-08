package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.Direzione;
import java.util.*;

/**
 * Questa classe ha la responsabilita' di creare e gestire la mappa del gioco
 *
 * @author docente di POO
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @see Stanza
 * @see Direzione
 * @version 3.0
 */
public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	/**
	 * Costruttore privato (Esercizio 19): impedisce l'istanziazione diretta.
     * Ora si passa obbligatoriamente dal Builder!
	 */
	private Labirinto() {
	}

    /**
     * Factory method statico e pubblico (Esercizio 19).
     * @return una nuova istanza del builder
     */
    public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
    
	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
	}
    
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
	}

    // ======================================================================
    // CLASSE STATICA NIDIFICATA: LabirintoBuilder
    // ======================================================================
    public static class LabirintoBuilder {
        private Map<String, Stanza> stanze;
        private Stanza stanzaIniziale;
        private Stanza stanzaVincente;
        private Stanza ultimaStanzaAggiunta;

        public LabirintoBuilder() {
            this.stanze = new HashMap<>(); // Usiamo tranquillamente il diamond!
        }

        public LabirintoBuilder addStanzaIniziale(String nome) {
            if (!this.stanze.containsKey(nome)) {
                this.stanze.put(nome, new Stanza(nome));
            }
            this.stanzaIniziale = this.stanze.get(nome);
            this.ultimaStanzaAggiunta = this.stanzaIniziale;
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nome) {
            if (!this.stanze.containsKey(nome)) {
                this.stanze.put(nome, new Stanza(nome));
            }
            this.stanzaVincente = this.stanze.get(nome);
            this.ultimaStanzaAggiunta = this.stanzaVincente;
            return this;
        }

        public LabirintoBuilder addStanza(String nome) {
            Stanza nuovaStanza = new Stanza(nome);
            this.ultimaStanzaAggiunta = nuovaStanza;
            this.stanze.put(nome, nuovaStanza);
            return this;
        }
        
        public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
            Stanza nuovaStanza = new StanzaBloccata(nome, Direzione.valueOf(direzioneBloccata.toUpperCase()), attrezzoSbloccante);
            this.ultimaStanzaAggiunta = nuovaStanza;
            this.stanze.put(nome, nuovaStanza);
            return this;
        }

        public LabirintoBuilder addStanzaBloccataIniziale(String nome, String direzioneBloccata, String attrezzoSbloccante) {
            this.stanzaIniziale = new StanzaBloccata(nome, Direzione.valueOf(direzioneBloccata.toUpperCase()), attrezzoSbloccante);
            this.ultimaStanzaAggiunta = this.stanzaIniziale;
            this.stanze.put(nome, this.stanzaIniziale);
            return this;
        }

        public LabirintoBuilder addAttrezzo(String nome, int peso) {
            Attrezzo nuovoAttrezzo = new Attrezzo(nome, peso);
            this.ultimaStanzaAggiunta.addAttrezzo(nuovoAttrezzo);
            return this;
        }
        
        public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio) {
            if (this.ultimaStanzaAggiunta != null && personaggio != null) {
                this.ultimaStanzaAggiunta.setPersonaggio(personaggio);
            }
            return this;
        }

        public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, String direzione) {
            Stanza s1 = this.stanze.get(nomeStanza1);
            Stanza s2 = this.stanze.get(nomeStanza2);
            
            if (s1 != null && s2 != null) {
                s1.impostaStanzaAdiacente(Direzione.valueOf(direzione.toUpperCase()), s2);
            }
            
            return this;
        }

        public Labirinto getLabirinto() {
            Labirinto nuovoLabirinto = new Labirinto(); // Ora è pulito, senza le vecchie stanze cablate
            nuovoLabirinto.setStanzaIniziale(this.stanzaIniziale);
            nuovoLabirinto.setStanzaVincente(this.stanzaVincente);
            return nuovoLabirinto;
        }
        
        public LabirintoBuilder posizionaAttrezzoInStanza(String nomeStanza, String nomeAttrezzo, int peso) {
            Stanza stanza = this.stanze.get(nomeStanza);
            if (stanza != null) {
                stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
            }
            return this;
        }
        
        public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
            Stanza nuovaStanza = new StanzaBuia(nome, attrezzoPerVedere);
            this.ultimaStanzaAggiunta = nuovaStanza;
            this.stanze.put(nome, nuovaStanza);
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
            Stanza nuovaStanza = new StanzaMagica(nome, soglia);
            this.ultimaStanzaAggiunta = nuovaStanza;
            this.stanze.put(nome, nuovaStanza);
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nome) {
            return addStanzaMagica(nome, 3);
        }
        
        public LabirintoBuilder posizionaPersonaggioInStanza(String nomeStanza, AbstractPersonaggio p) {
            Stanza stanza = this.stanze.get(nomeStanza);
            if (stanza != null && p != null) {
                stanza.setPersonaggio(p);
            }
            return this;
        }
    }
}