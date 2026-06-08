package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuratore {

    private static final String NOME_FILE_PROPERTIES = "diadia.properties";
    private static final int PESO_MAX_DEFAULT = 10;
    private static final int CFU_DEFAULT = 20;
    
    private static Properties prop = null;

    /**
     * Metodo privato che carica le properties dal file usando il ClassLoader
     */
    private static void carica() {
        prop = new Properties();
        // Usiamo il blocco try-with-resources di Java 7 per gestire la chiusura dell'InputStream
        try (InputStream input = Configuratore.class.getClassLoader().getResourceAsStream(NOME_FILE_PROPERTIES)) {
            if (input != null) {
                prop.load(input);
            } else {
                System.err.println("Attenzione: file '" + NOME_FILE_PROPERTIES + "' non trovato. Verranno usati i valori di default.");
            }
        } catch (IOException e) {
            System.err.println("Errore di I/O durante il caricamento delle properties: " + e.getMessage());
        }
    }

    public static int getPesoMax() {
        if (prop == null) {
            carica();
        }
        return Integer.parseInt(prop.getProperty("peso_max_borsa", String.valueOf(PESO_MAX_DEFAULT)));
    }

    public static int getCfu() {
        if (prop == null) {
            carica();
        }
        return Integer.parseInt(prop.getProperty("cfu_iniziali", String.valueOf(CFU_DEFAULT)));
    }
}