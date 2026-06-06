package it.uniroma3.diadia.comandi;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FabbricaComandiRiflessivaTest {

@Test
public void testComandoNonValido() {
    FabbricaDiComandiRiflessiva fabbrica = new FabbricaDiComandiRiflessiva();
    try {
        fabbrica.costruisciComando("corri");
        fail("avrebbe dovuto lanciare un'eccezione");
    } catch(Exception e) {
        
    }
}
@Test
public void testComandoValido_Vai() {
	FabbricaDiComandiRiflessiva fabbrica=new FabbricaDiComandiRiflessiva();
	try {
		Comando c=fabbrica.costruisciComando("vai nord");
		assertEquals("vai",c.getNome());
	} catch(Exception e) {
		fail ("Il test è corretto non devi lanciare eccezioni");
	}
}
@Test
public void testParametroValido_Nord() {
	FabbricaDiComandiRiflessiva fabbrica=new FabbricaDiComandiRiflessiva();
	try {
		Comando c=fabbrica.costruisciComando("vai nord");
		assertEquals("nord", c.getParametro());
	}catch(Exception e){
		fail("non dovevi lanciare un'eccezione");
	}
}
@Test
public void testParametroNonValido() {
	FabbricaDiComandiRiflessiva fabbrica=new FabbricaDiComandiRiflessiva();
	try {
		fabbrica.costruisciComando("Oveste");
		fail("Oveste non è valido dovevi lanciare un'eccezzione");
		
	}catch(Exception e) {
		//prova a prenderla
	}
}


}
