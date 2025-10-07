
import domini.Article;
import domini.ArticleTipus;
import domini.InventariJugador;
import domini.MyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventariJugadorTest {

    private InventariJugador inventari;

    @BeforeEach
    void setUp() throws MyException {
        inventari = new InventariJugador("Jugador1",100);
    }

    @Test
    void testGetNomJugador() {
        assertEquals("Jugador1", inventari.getNomJugador());
    }

    @Test
    void testAfegirFinal() throws MyException {
        inventari.afegirFinal(new Article("Espasa"));
        inventari.afegirFinal(new Article("Escut"));

        assertEquals(2, inventari.getSize());
        String result = inventari.toString();
        assertTrue(result.contains("Espasa"));
        assertTrue(result.contains("Escut"));
        assertTrue(result.indexOf("Espasa") < result.indexOf("Escut"));
    }

    @Test
    void testAfegirPrincipi() throws MyException {
        inventari.afegirFinal(new Article("Espasa"));
        inventari.afegirPrincipi(new Article("Poció"));

        assertEquals(2, inventari.getSize());
        String result = inventari.toString();
        assertTrue(result.contains("Espasa"));
        assertTrue(result.contains("Poció"));
        assertTrue(result.indexOf("Poció") < result.indexOf("Espasa"));
    }

    @Test
    void testEliminarArticleExisteix() throws MyException {
        Article espasa = new Article("Espasa");
        Article escut = new Article("Escut");

        inventari.afegirFinal(espasa);
        inventari.afegirFinal(escut);
        assertEquals(2, inventari.getSize());

        assertTrue(inventari.eliminar(espasa));
        assertEquals(1, inventari.getSize());
        assertFalse(inventari.toString().contains("Espasa"));

        assertFalse(inventari.eliminar(espasa));
    }

    @Test
    void testEliminarArticleNoExisteix() throws MyException {
        inventari.afegirFinal(new Article("Espasa"));

        assertFalse(inventari.eliminar(new Article("Armadura")));
        assertEquals(1, inventari.getSize());
    }

    @Test
    void testGetTres() throws MyException {
        List<String> expectedNames = List.of("Espasa", "Escut", "Casc");
        List<Integer> expectedIndexes = List.of(0, 1, 2, 0, 1, 2, 0);
        for( String name : expectedNames)
            inventari.afegirFinal(new Article(name, 10, ArticleTipus.ARMA));
        assertEquals(3, inventari.getSize());

        for (int index = 0; index <= expectedNames.size(); index++) {
            ArrayList<Article> article = inventari.getTres(index);
            assertEquals(3, article.size());
            for( int i = 0; i < article.size(); i++)
                assertEquals(expectedNames.get(expectedIndexes.get(i+index)), article.get(i).getNom());
        }
    }

    @Test
    void testGetSize() throws MyException {
        assertEquals(0, inventari.getSize());
        inventari.afegirFinal(new Article("Espasa"));
        inventari.afegirFinal(new Article("Escut"));
        assertEquals(2, inventari.getSize());
    }

    @Test
    void testEsBuit() throws MyException {
        assertTrue(inventari.esBuit());
        inventari.afegirFinal(new Article("Espasa"));
        assertFalse(inventari.esBuit());
    }

    @Test
    void testCompareTo() throws MyException {
        InventariJugador j1 = new InventariJugador("Anna",100);
        InventariJugador j2 = new InventariJugador("Bernat",100);

        assertTrue(j1.compareTo(j2) < 0);
        assertTrue(j2.compareTo(j1) > 0);
    }

    @Test
    void testAfegirNullLlançaExcepcio() {
        assertThrows(MyException.class, () -> inventari.afegirFinal(null));
        assertThrows(MyException.class, () -> inventari.afegirPrincipi(null));
    }
}
