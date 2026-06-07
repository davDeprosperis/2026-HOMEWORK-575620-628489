package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.personaggi.*;
import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Direzione;

public class LabirintoBuilder {
	private Map<String, Stanza> stanze;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
		this.stanze = new HashMap<String, Stanza>();
	}

	public LabirintoBuilder addStanzaIniziale(String nome) {
	    this.stanzaIniziale = this.stanze.getOrDefault(nome, new Stanza(nome));
	    this.ultimaStanzaAggiunta = this.stanzaIniziale;
	    this.stanze.put(nome, this.stanzaIniziale);
	    return this;
	}

	public LabirintoBuilder addStanzaVincente(String nome) {
	    this.stanzaVincente = this.stanze.getOrDefault(nome, new Stanza(nome));
	    this.ultimaStanzaAggiunta = this.stanzaVincente;
	    this.stanze.put(nome, this.stanzaVincente);
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
	
	/**
	 * Associa un personaggio all'ultima stanza aggiunta nel Builder.
	 * @param personaggio Il personaggio da inserire nella stanza
	 * @return questo LabirintoBuilder per permettere il concatenamento dei metodi
	 */
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
		Labirinto nuovoLabirinto = new Labirinto();
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

	// overload senza soglia (usa default = 3)
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
