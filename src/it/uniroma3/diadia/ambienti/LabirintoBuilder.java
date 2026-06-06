package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Direzione;

public class LabirintoBuilder {
private Map<String,Stanza>stanze;
private Stanza stanzaIniziale;
private Stanza stanzaVincente;
private Stanza ultimaStanzaAggiunta;
public LabirintoBuilder() {
	this.stanze=new HashMap<String, Stanza>();
}
public LabirintoBuilder addStanzaIniziale(String nome) {
	this.stanzaIniziale=new Stanza(nome);
	this.ultimaStanzaAggiunta=this.stanzaIniziale;
	
	this.stanze.put(nome,this.ultimaStanzaAggiunta);
	return this;
}
public LabirintoBuilder addStanzaVincente(String nome) {
	this.stanzaVincente=new Stanza(nome);
	this.ultimaStanzaAggiunta=this.stanzaVincente;
	this.stanze.put(nome,this.stanzaVincente);
	return this;
}
public LabirintoBuilder addStanza(String nome) {
	Stanza nuovaStanza=new Stanza(nome);
	this.ultimaStanzaAggiunta=nuovaStanza;
	this.stanze.put(nome,nuovaStanza);
	return this;

}
public LabirintoBuilder addAttrezzo(String nome,int peso) {
	Attrezzo nuovoAttrezzo=new Attrezzo(nome,peso);
	this.ultimaStanzaAggiunta.addAttrezzo(nuovoAttrezzo);
	return this;
	
}
public LabirintoBuilder addAdiacenza(String nomeStanza1,String nomeStanza2,String direzione) {
	this.stanze.get(nomeStanza1);
	this.stanze.get(nomeStanza2);
	this.stanze.get(nomeStanza1).impostaStanzaAdiacente(Direzione.valueOf(direzione.toUpperCase()), this.stanze.get(nomeStanza2));
	return this;
}
public Labirinto getLabirinto() {
	Labirinto nuovoLabirinto=new Labirinto();
	nuovoLabirinto.setStanzaIniziale(this.stanzaIniziale);
	nuovoLabirinto.setStanzaVincente(this.stanzaVincente);
	return nuovoLabirinto;
}

}
