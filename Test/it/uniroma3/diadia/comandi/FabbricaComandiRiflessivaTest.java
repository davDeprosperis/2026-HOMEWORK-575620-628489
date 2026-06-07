package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaComandiRiflessivaTest {

    private FabbricaDiComandiRiflessiva fabbrica;

    @BeforeEach
    public void setUp() {
        this.fabbrica = new FabbricaDiComandiRiflessiva();
    }

    @Test
    public void testComandoNonValido() {
        // Ora il metodo non lancia eccezioni, restituisce ComandoNonValido
        Comando c = fabbrica.costruisciComando("corri");
        assertEquals("non valido", c.getNome());
    }

    @Test
    public void testComandoValido_Vai() {
        Comando c = fabbrica.costruisciComando("vai nord");
        assertEquals("vai", c.getNome());
    }

    @Test
    public void testParametroValido_Nord() {
        Comando c = fabbrica.costruisciComando("vai nord");
        assertEquals("nord", c.getParametro());
    }

    @Test
    public void testParametroNonValido() {
        // "Oveste" non esiste, quindi deve restituire ComandoNonValido
        Comando c = fabbrica.costruisciComando("Oveste");
        assertEquals("non valido", c.getNome());
    }
}