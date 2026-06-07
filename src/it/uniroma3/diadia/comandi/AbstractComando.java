package it.uniroma3.diadia.comandi;

import java.util.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import java.io.File;
import java.net.URL;
import java.net.URI;  

public abstract class AbstractComando implements Comando {
	private static List<String> comandiDisponibili = null;
	private String parametro;

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getParametro() {
		return this.parametro;
	}

	public abstract String getNome();

	public abstract void esegui(Partita partita, IO io);

	public String getNomeComandoDinamico() {
		String nomeClasse = this.getClass().getSimpleName();
		return nomeClasse.replace("Comando", "").toLowerCase();
	}

	/**
	 * Scansiona dinamicamente il package dei comandi per trovare tutte le classi
	 * che implementano un comando, estraendone il nome.
	 */
	public static List<String> getComandiDisponibili() {
		if (comandiDisponibili == null) {
			comandiDisponibili = new ArrayList<>();
			String packagePath = "it/uniroma3/diadia/comandi";

			try {
				// Recuperiamo l'URL della risorsa associata al package
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				URL resource = classLoader.getResource(packagePath);

				if (resource != null) {
					// Trasformiamo l'URL in URI per gestire correttamente spazi o caratteri
					// speciali nel path
					URI uri = resource.toURI();
					File directory = new File(uri);

					if (directory.exists() && directory.isDirectory()) {
						File[] files = directory.listFiles();
						if (files != null) {
							for (File file : files) {
								String fileName = file.getName();

								// Verifichiamo i vincoli richiesti
								if (fileName.startsWith("Comando") && fileName.endsWith(".class") 
									    && !fileName.equals("Comando.class") 
									    && !fileName.contains("Test") 
									    && !fileName.contains("NonValido")) { 

									    String nomeComando = fileName
									            .replace("Comando", "")
									            .replace(".class", "")
									            .toLowerCase();
									    
									    if (!comandiDisponibili.contains(nomeComando)) {
									        comandiDisponibili.add(nomeComando);
									    }
									}
							}
						}
					}
				}
			} catch (Exception e) {
				// Gestione silenziosa dell'errore o log di fallback
				e.printStackTrace();
			}
		}
		return comandiDisponibili;

	}

}
