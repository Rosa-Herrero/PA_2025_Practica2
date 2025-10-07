
import domini.MyException;
import domini.SequenciaOrdenada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenciaOrdenadaTest {

    private SequenciaOrdenada<Integer> llista;

    @BeforeEach
    void setUp() {
        llista = new SequenciaOrdenada<>();
    }

    @Test
    void testInserirOrdena() throws MyException {
        // afegim 5, 3, 8, 1
        assertEquals(0, llista.getSize());
        assertTrue(llista.inserir(5));
        assertEquals(1, llista.getSize());
        assertTrue(llista.inserir(3));
        assertEquals(2, llista.getSize());
        assertTrue(llista.inserir(8));
        assertEquals(3, llista.getSize());
        assertTrue(llista.inserir(1));
        assertEquals(4, llista.getSize());
        // cal que l'ordre sigui 1, 3, 5, 8
        String resultat = llista.toString();
        assertTrue(resultat.contains("0 - 1"));
        assertTrue(resultat.contains("1 - 3"));
        assertTrue(resultat.contains("2 - 5"));
        assertTrue(resultat.contains("3 - 8"));
    }

    @Test
    void testRepetits() throws MyException {
        // afegim 5 i 3
        assertEquals(0, llista.getSize());
        assertTrue(llista.inserir(5));
        assertEquals(1, llista.getSize());
        assertTrue(llista.inserir(3));
        assertEquals(2, llista.getSize());
        // repetim 5 i no pot inserir
        assertFalse(llista.inserir(5));
        assertEquals(2, llista.getSize());
        // repetim 3 i no pot inserir
        assertFalse(llista.inserir(3));
        assertEquals(2, llista.getSize());
    }

    @Test
    void testEliminarElementExisteix() throws MyException {
        // afegim 10, 20 i 30
        assertEquals(0, llista.getSize());
        assertTrue(llista.inserir(10));
        assertEquals(1, llista.getSize());
        assertTrue(llista.inserir(20));
        assertEquals(2, llista.getSize());
        assertTrue(llista.inserir(30));
        assertEquals(3, llista.getSize());
        // eliminem 20
        assertTrue(llista.eliminar(20));
        assertEquals(2, llista.getSize());
        // comprovem que conté 10 i 30, i no conté 20
        String resultat = llista.toString();
        assertTrue(resultat.contains("0 - 10"));
        assertFalse(resultat.contains("20"));
        assertTrue(resultat.contains("1 - 30"));
        // eliminem 10
        assertTrue(llista.eliminar(10));
        assertEquals(1, llista.getSize());
        // comprovem que conté 10 i 30, i no conté 20
        resultat = llista.toString();
        assertFalse(resultat.contains("10"));
        assertFalse(resultat.contains("20"));
        assertTrue(resultat.contains("0 - 30"));
    }

    @Test
    void testEliminarElementNoExisteix() throws MyException {
        // afegim 10 i 20
        assertEquals(0, llista.getSize());
        assertTrue(llista.inserir(10));
        assertEquals(1, llista.getSize());
        assertTrue(llista.inserir(20));
        assertEquals(2, llista.getSize());
        // intentem eliminar 99 i no pot
        assertFalse(llista.eliminar(99));
        assertEquals(2, llista.getSize());
        // comprovem que conté 10 i 20
        String resultat = llista.toString();
        assertTrue(resultat.contains("0 - 10"));
        assertTrue(resultat.contains("1 - 20"));
    }

    @Test
    void testEsBuida() throws MyException {
        // llista inicial és buida
        assertEquals(0, llista.getSize());
        assertTrue(llista.esBuida());
        // afegim un element
        assertTrue(llista.inserir(42));
        // llista no és buida
        assertEquals(1, llista.getSize());
        assertFalse(llista.esBuida());
    }

    @Test
    void testExcepcioAmbMissatge() {
        // comprovem que es llança una excepció amb un missatge
        // inserir null
        MyException thrown = assertThrows(MyException.class, () -> llista.inserir(null));
        assertTrue(thrown.getMessage().length() > 8);
        // buscar un null
        thrown = assertThrows(MyException.class, () -> llista.eliminar(null));
        assertTrue(thrown.getMessage().length() > 8);
    }

    @Test
    void testEliminarDeLlistaBuida() throws MyException {
        // llista buida no podem eliminar
        assertFalse(llista.eliminar(10));
    }

    @Test
    void testClonar() throws MyException, CloneNotSupportedException {
        llista.inserir(5);
        llista.inserir(3);
        llista.inserir(8);
        llista.inserir(1);
        SequenciaOrdenada<Integer> copia = llista.clonarParcial(6);
        assertEquals(3, copia.getSize());
        // Modifiquem la còpia i comprovem que l'original no canvia
        copia.eliminar(3);
        assertEquals(4, llista.getSize());
        assertEquals(2, copia.getSize());
        assertTrue(llista.toString().contains("1 - 3"));
        assertFalse(copia.toString().contains("3"));

    }
}
