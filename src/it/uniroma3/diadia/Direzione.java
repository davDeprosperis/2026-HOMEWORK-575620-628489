package it.uniroma3.diadia;

/***
 * Questa enum definisce le direzioni e le rispettive direzioni opposte
 * @author Davide De Prosperis, Matricola: 575620
 * @author Leonardo Coloricchio, Matricola: 628489
 * @version 3.0
 */

public enum Direzione {
	NORD, EST, SUD, OVEST;
	
	public final Direzione opposta() {
		switch (this) {
		case NORD: return SUD;
		case EST: return OVEST;
		case SUD : return NORD;
		case OVEST : return EST;
		default: return null;
		}
	}

}
