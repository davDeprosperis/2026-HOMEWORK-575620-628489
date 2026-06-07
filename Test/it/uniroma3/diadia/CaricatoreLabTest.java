package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.StringReader;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;

public class CaricatoreLabTest {

    // ── fixture ──────────────────────────────────────────────────────────

    private static final String MONOLOCALE =
        "Stanze:\n" +
        "Atrio\n" +
        "Estremi:\n" +
        "Atrio\n" +
        "Atrio\n" +
        "Attrezzi:\n" +
        "Uscite:\n";

    private static final String BILOCALE =
        "Stanze:\n" +
        "N10\n" +
        "Biblioteca\n" +
        "Estremi:\n" +
        "N10\n" +
        "Biblioteca\n" +
        "Attrezzi:\n" +
        "osso 5 N10\n" +
        "Uscite:\n" +
        "N10 nord Biblioteca\n" +
        "Biblioteca sud N10\n";
    
    private static final String CON_STANZA_BUIA =
        "Stanze:\n" +
        "Atrio\n" +
        "Sala buia lanterna\n" +
    	"Estremi:\n" +
        "Atrio\n" +
   	    "Sala\n" +
   	    "Personaggi:\n" +
   	    "Attrezzi:\n" +
   	    "Uscite:\n" +
   	    "Atrio nord Sala\n";

   	private static final String CON_STANZA_BLOCCATA =
        "Stanze:\n" +
        "Atrio\n" +
        "Corridoio bloccata nord chiave\n" +
        "Estremi:\n" +
   	    "Atrio\n" +
   	    "Corridoio\n" +
   	    "Personaggi:\n" +
   	    "Attrezzi:\n" +
   	    "Uscite:\n" +
        "Atrio nord Corridoio\n";

   	private static final String CON_STANZA_MAGICA =
        "Stanze:\n" +
   	    "Atrio\n" +
        "Magica magica 2\n" +
   	    "Estremi:\n" +
   	    "Atrio\n" +
   	    "Magica\n" +
   	    "Personaggi:\n" +
   	    "Attrezzi:\n" +
        "Uscite:\n" +
   	    "Atrio nord Magica\n";

   	private static final String CON_PERSONAGGIO =
        "Stanze:\n" +
   	    "Atrio\n" +
   	    "Estremi:\n" +
   	    "Atrio\n" +
   	    "Atrio\n" +
   	    "Personaggi:\n" +
        "Atrio cane Fido Sono un cane feroce!\n" +
   	    "Attrezzi:\n" +
   	    "Uscite:\n";

    // ── helper ───────────────────────────────────────────────────────────

    private Labirinto carica(String spec) throws Exception {
        CaricatoreLabirinto c = new CaricatoreLabirinto(new StringReader(spec));
        c.carica();
        return c.getLabirinto();
    }

    // ── test monolocale ──────────────────────────────────────────────────

    @Test
    public void testMonolocale_stanzaIniziale() throws Exception {
        Labirinto lab = carica(MONOLOCALE);
        assertEquals("Atrio", lab.getStanzaIniziale().getNome());
    }

    @Test
    public void testMonolocale_stanzaVincente() throws Exception {
        Labirinto lab = carica(MONOLOCALE);
        assertEquals("Atrio", lab.getStanzaVincente().getNome());
    }

    // ── test bilocale ────────────────────────────────────────────────────

    @Test
    public void testBilocale_stanzaIniziale() throws Exception {
        Labirinto lab = carica(BILOCALE);
        assertEquals("N10", lab.getStanzaIniziale().getNome());
    }

    @Test
    public void testBilocale_stanzaVincente() throws Exception {
        Labirinto lab = carica(BILOCALE);
        assertEquals("Biblioteca", lab.getStanzaVincente().getNome());
    }

    @Test
    public void testBilocale_uscita_nord() throws Exception {
        Labirinto lab = carica(BILOCALE);
        Stanza n10 = lab.getStanzaIniziale();
        assertEquals("Biblioteca",
            n10.getStanzaAdiacente(it.uniroma3.diadia.Direzione.NORD).getNome());
    }

    @Test
    public void testBilocale_uscita_sud() throws Exception {
        Labirinto lab = carica(BILOCALE);
        Stanza biblioteca = lab.getStanzaVincente();
        assertEquals("N10",
            biblioteca.getStanzaAdiacente(it.uniroma3.diadia.Direzione.SUD).getNome());
    }

    @Test
    public void testBilocale_attrezzo_presente() throws Exception {
        Labirinto lab = carica(BILOCALE);
        Stanza n10 = lab.getStanzaIniziale();
        assertTrue(n10.hasAttrezzo("osso"));
    }

    @Test
    public void testBilocale_attrezzo_peso() throws Exception {
        Labirinto lab = carica(BILOCALE);
        Stanza n10 = lab.getStanzaIniziale();
        assertEquals(5, n10.getAttrezzo("osso").getPeso());
    }
    
    @Test
    public void testBilocale_debug() throws Exception {
        Labirinto lab = carica(BILOCALE);
        Stanza n10 = lab.getStanzaIniziale();
        // stampa tutti gli attrezzi presenti nella stanza
        for (it.uniroma3.diadia.attrezzi.Attrezzo a : n10.getAttrezzi()) {
            System.out.println("Attrezzo trovato: '" + a.getNome() + "'");
        }
    }
 // ── test stanza buia ─────────────────────────────────────────────────

    @Test
    public void testStanzaBuia_tipoCorretto() throws Exception {
        Labirinto lab = carica(CON_STANZA_BUIA);
        Stanza sala = lab.getStanzaVincente();
        assertTrue(sala instanceof it.uniroma3.diadia.ambienti.StanzaBuia);
    }

    @Test
    public void testStanzaBuia_senzaLanterna_descrizioneScura() throws Exception {
        Labirinto lab = carica(CON_STANZA_BUIA);
        Stanza sala = lab.getStanzaVincente();
        assertEquals("qui c'è un buio pesto", sala.getDescrizione());
    }

    // ── test stanza bloccata ─────────────────────────────────────────────

    @Test
    public void testStanzaBloccata_tipoCorretto() throws Exception {
        Labirinto lab = carica(CON_STANZA_BLOCCATA);
        Stanza corridoio = lab.getStanzaVincente();
        assertTrue(corridoio instanceof it.uniroma3.diadia.ambienti.StanzaBloccata);
    }

    @Test
    public void testStanzaBloccata_senzaChiave_nonSiPassa() throws Exception {
        Labirinto lab = carica(CON_STANZA_BLOCCATA);
        Stanza corridoio = lab.getStanzaVincente();
        // senza la chiave, andare a nord riporta nella stanza stessa
        assertEquals(corridoio,
            corridoio.getStanzaAdiacente(it.uniroma3.diadia.Direzione.NORD));
    }

    // ── test stanza magica ───────────────────────────────────────────────

    @Test
    public void testStanzaMagica_tipoCorretto() throws Exception {
        Labirinto lab = carica(CON_STANZA_MAGICA);
        Stanza magica = lab.getStanzaVincente();
        assertTrue(magica instanceof it.uniroma3.diadia.ambienti.StanzaMagica);
    }

    // ── test personaggio ─────────────────────────────────────────────────

    @Test
    public void testPersonaggio_presente() throws Exception {
        Labirinto lab = carica(CON_PERSONAGGIO);
        assertNotNull(lab.getStanzaIniziale().getPersonaggio());
    }

    @Test
    public void testPersonaggio_tipoCorretto() throws Exception {
        Labirinto lab = carica(CON_PERSONAGGIO);
        assertTrue(lab.getStanzaIniziale().getPersonaggio()
        instanceof it.uniroma3.diadia.personaggi.Cane);
    }

    @Test
    public void testPersonaggio_nomeCorretto() throws Exception {
        Labirinto lab = carica(CON_PERSONAGGIO);
        assertEquals("Fido",
            lab.getStanzaIniziale().getPersonaggio().getNome());
    }
    
}