package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;


/**
 * Interfaccia dedicata ai comandi
 * 
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 2.0
 * @see Partita
 * @see IO
 *  
 */

public interface Comando {
	public void esegui(Partita partita, IO io);

	public void setParametro(String parametro);

	public String getNome();

	public String getParametro();

}