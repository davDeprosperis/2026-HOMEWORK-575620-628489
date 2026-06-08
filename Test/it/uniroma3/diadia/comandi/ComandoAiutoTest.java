package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import java.util.ArrayList;

public class ComandoAiutoTest {

    private ComandoAiuto comando;
    private IOSimulator io;
    private Partita partita;

    @BeforeEach
    public void setUp() {
        this.comando = new ComandoAiuto();
        this.io = new IOSimulator(new ArrayList<String>());
        this.partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("Atrio").getLabirinto());
    }

    @Test
    public void testEsegui() {
        this.comando.esegui(partita, io);
        
        // Usiamo il metodo corretto della tua implementazione di IOSimulator
        assertTrue(io.hasMessaggio("aiuto "));
    }
    
    @Test
    public void testGetNome() {
        assertEquals("aiuto", comando.getNome());
    }
}