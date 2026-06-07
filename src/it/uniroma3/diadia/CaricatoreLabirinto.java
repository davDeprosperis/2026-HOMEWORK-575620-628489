package it.uniroma3.diadia;

import java.io.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

    private static final String STANZE     = "Stanze:";
    private static final String ESTREMI    = "Estremi:";
    private static final String PERSONAGGI = "Personaggi:";
    private static final String ATTREZZI   = "Attrezzi:";
    private static final String USCITE     = "Uscite:";

    private LabirintoBuilder builder;
    private LineNumberReader reader;

    /**
     * Costruttore generico: accetta qualsiasi Reader.
     * Usare StringReader nei test, FileReader in produzione.
     */
    public CaricatoreLabirinto(Reader reader) {
        this.builder = new LabirintoBuilder();
        this.reader  = new LineNumberReader(reader);
    }

    /** Costruttore comodo per uso reale con file su disco. */
    public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
        this(new FileReader(nomeFile));
    }

    // =========================================================
    //  Entry point
    // =========================================================

    public void carica() throws IOException {
        this.leggiStanze();
        this.leggiEstremi();
        this.leggiPersonaggi();
        this.leggiAttrezzi();
        this.leggiUscite();
        this.reader.close();
    }

    // =========================================================
    //  Sezioni
    // =========================================================

    /**
     * Legge tutte le stanze dalla sezione "Stanze:".
     * Ogni riga può avere uno dei seguenti formati:
     *   "Atrio"                          -> Stanza normale
     *   "Sala buia lanterna"             -> StanzaBuia
     *   "Corridoio bloccata nord chiave" -> StanzaBloccata
     *   "Magica magica"                  -> StanzaMagica (soglia default = 3)
     *   "Magica magica 5"                -> StanzaMagica (soglia = 5)
     */
    private void leggiStanze() throws IOException {
        reader.mark(1024);
        String riga = leggiRigaSignificativa();
        if (riga == null || !riga.startsWith(STANZE)) {
            reader.reset();
            return;
        }

        while (true) {
            reader.mark(1024);
            riga = reader.readLine();
            if (riga == null || riga.isBlank() || isIntestazione(riga)) {
                reader.reset();
                break;
            }
            String[] tokens = riga.trim().split("\\s+");
            String nomeStanza = tokens[0];

            if (tokens.length >= 3 && tokens[1].equalsIgnoreCase("buia")) {
                builder.addStanzaBuia(nomeStanza, tokens[2]);
            } else if (tokens.length >= 4 && tokens[1].equalsIgnoreCase("bloccata")) {
                builder.addStanzaBloccata(nomeStanza, tokens[2], tokens[3]);
            } else if (tokens.length >= 2 && tokens[1].equalsIgnoreCase("magica")) {
                int soglia = (tokens.length >= 3) ? Integer.parseInt(tokens[2]) : 3;
                builder.addStanzaMagica(nomeStanza, soglia);
            } else {
                builder.addStanza(nomeStanza);
            }
        }
    }

    /**
     * Legge la sezione "Estremi:" che contiene due righe:
     *   prima riga:  nome stanza iniziale
     *   seconda riga: nome stanza vincente
     */
    private void leggiEstremi() throws IOException {
        reader.mark(1024);
        String riga = leggiRigaSignificativa();
        if (riga == null || !riga.startsWith(ESTREMI)) {
            reader.reset();
            return;
        }

        String nomeStanzaIniziale = leggiRigaSignificativa();
        String nomeStanzaVincente = leggiRigaSignificativa();
        if (nomeStanzaIniziale != null) builder.addStanzaIniziale(nomeStanzaIniziale.trim());
        if (nomeStanzaVincente != null) builder.addStanzaVincente(nomeStanzaVincente.trim());
    }

    /**
     * Legge la sezione "Personaggi:".
     * Formato di ogni riga:
     *   "nomeStanza tipo nomePersonaggio presentazione..."
     * Esempi:
     *   "Atrio cane Fido Sono un cane feroce!"
     *   "N10 mago Merlino Sono un mago potente!"
     *   "Magica strega Circe Sono una strega!"
     */
    private void leggiPersonaggi() throws IOException {
        reader.mark(1024);
        String riga = leggiRigaSignificativa();
        if (riga == null || !riga.startsWith(PERSONAGGI)) {
            reader.reset();
            return;
        }

        while (true) {
            reader.mark(1024);
            riga = reader.readLine();
            if (riga == null || riga.isBlank() || isIntestazione(riga)) {
                reader.reset();
                break;
            }
            // split con limite 4: [nomeStanza, tipo, nomePersonaggio, "presentazione..."]
            String[] tokens = riga.trim().split("\\s+", 4);
            if (tokens.length < 3) continue;

            String nomeStanza      = tokens[0].trim();
            String tipo            = tokens[1].trim().toLowerCase();
            String nomePersonaggio = tokens[2].trim();
            String presentazione   = (tokens.length == 4) ? tokens[3].trim() : "";

            AbstractPersonaggio personaggio = creaPersonaggio(tipo, nomePersonaggio, presentazione);
            if (personaggio != null) builder.posizionaPersonaggioInStanza(nomeStanza, personaggio);
        }
    }

    /**
     * Legge la sezione "Attrezzi:".
     * Formato di ogni riga:
     *   "nomeAttrezzo peso nomeStanza"
     * Esempio:
     *   "osso 5 N10"
     */
    private void leggiAttrezzi() throws IOException {
        reader.mark(1024);
        String riga = leggiRigaSignificativa();
        if (riga == null || !riga.startsWith(ATTREZZI)) {
            reader.reset();
            return;
        }

        while (true) {
            reader.mark(1024);
            riga = reader.readLine();
            if (riga == null || riga.isBlank() || isIntestazione(riga)) {
                reader.reset();
                break;
            }
            String[] tokens = riga.trim().split("\\s+");
            if (tokens.length == 3) {
                String nomeAttrezzo = tokens[0];
                int peso            = Integer.parseInt(tokens[1]);
                String nomeStanza   = tokens[2];
                builder.posizionaAttrezzoInStanza(nomeStanza, nomeAttrezzo, peso);
            }
        }
    }

    /**
     * Legge la sezione "Uscite:".
     * Formato di ogni riga:
     *   "nomeStanza1 direzione nomeStanza2"
     * Esempio:
     *   "N10 nord Biblioteca"
     */
    private void leggiUscite() throws IOException {
        reader.mark(1024);
        String riga = leggiRigaSignificativa();
        if (riga == null || !riga.startsWith(USCITE)) {
            reader.reset();
            return;
        }

        while (true) {
            reader.mark(1024);
            riga = reader.readLine();
            if (riga == null || riga.isBlank() || isIntestazione(riga)) {
                reader.reset();
                break;
            }
            String[] tokens = riga.trim().split("\\s+");
            if (tokens.length == 3) {
                String nomeStanzaPartenza     = tokens[0];
                String direzione              = tokens[1];
                String nomeStanzaDestinazione = tokens[2];
                builder.addAdiacenza(nomeStanzaPartenza, nomeStanzaDestinazione, direzione);
            }
        }
    }

    // =========================================================
    //  Factory dei personaggi
    // =========================================================

    /**
     * Crea il personaggio giusto in base al tipo.
     * Tipi riconosciuti: "cane", "mago", "strega".
     */
    private AbstractPersonaggio creaPersonaggio(String tipo, String nomePersonaggio, String presentazione) {
        switch (tipo) {
            case "cane":   return new Cane(nomePersonaggio, presentazione);
            case "strega": return new Strega(nomePersonaggio, presentazione);
            case "mago":   return new Mago(nomePersonaggio, presentazione, null);
            default:
                System.err.println("[CaricatoreLabirinto] tipo personaggio sconosciuto: " + tipo
                                   + " (riga " + reader.getLineNumber() + ")");
                return null;
        }
    }

    // =========================================================
    //  Utilità di lettura
    // =========================================================

    /** Legge e restituisce la prossima riga non vuota (o null a fine file). */
    private String leggiRigaSignificativa() throws IOException {
        String riga;
        do {
            riga = reader.readLine();
        } while (riga != null && riga.isBlank());
        return riga;
    }

    /** Restituisce true se la riga è un'intestazione di sezione. */
    private boolean isIntestazione(String riga) {
        return riga.startsWith(STANZE)     ||
               riga.startsWith(ESTREMI)    ||
               riga.startsWith(PERSONAGGI) ||
               riga.startsWith(ATTREZZI)   ||
               riga.startsWith(USCITE);
    }

    // =========================================================
    //  Getter
    // =========================================================

    public Labirinto getLabirinto() {
        return builder.getLabirinto();
    }
}