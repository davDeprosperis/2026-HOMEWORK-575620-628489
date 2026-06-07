package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	public Comando costruisciComando(String istruzione){
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		String nomeComando = null; // es. ‘vai’
		String parametro = null; // es. ‘sud’
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();// seconda parola: eventuale parametro
		
		if (nomeComando == null) {
		    return new ComandoNonValido();
		}

		try {
		    StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		    nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		    nomeClasse.append(nomeComando.substring(1));
		    
		    comando = (Comando) Class.forName(nomeClasse.toString()).newInstance();
		    comando.setParametro(parametro);
		} catch (Exception e) {
		    // Se la classe non viene trovata o c'è un errore, creiamo un comando non valido
		    comando = new ComandoNonValido();
		}
		return comando;
	}
}
