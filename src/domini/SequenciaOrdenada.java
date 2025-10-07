package domini;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SequenciaOrdenada<E extends Comparable<E>> implements Iterable<E> {

    /* Seqüència enllaçada LINEAL d'elements genèrics
     * ORDENADA sense repetits
     * sense capçalera
     */

    // --- Node intern ---
    private class Node implements Cloneable{
        E info;
        Node seguent;

        Node(E info) {
            this.info = info;
            this.seguent = null;
        }

        @SuppressWarnings("unchecked")
        public Node clone() throws CloneNotSupportedException {
            // TODO: implementa un clonatge profund del node i dels següents nodes
            return null;
        }

    }

    // --- Atributs ---
    private Node primer;

    // --- Constructor ---
    public SequenciaOrdenada() {
        this.primer = null;
    }

    // --- Mètodes públics ---


    /** Insereix un element mantenint l'ordre natural (definit per compareTo). */
    public boolean inserir(E nouElement) throws MyException {
        // TODO:
        //  - Llança una excepció si l’element és nul
        //  - Insereix mantenint l’ordre ascendent
        //  - No admet elements repetits, retorna false si és el cas
        return false;
    }

    /** Elimina el primer element que coincideixi amb l'element buscat,
     * cal que sigui intel·ligent (no cal recorrer tota la seqüència). */
    public boolean eliminar(E buscat) throws MyException {
        // TODO:
        //  - Gestiona els casos especials:
        //  - Llança una excepció si l’element és nul
        //  - retorna false si la llista és buida
        //  - Cerca de forma eficient, sense recórrer més del necessari
        //  - Si s'elimina retorna true
        return false;
    }

    /** Retorna true si la llista està buida */
    public boolean esBuida() {
        return primer == null;
    }

    /** Retorna el nombre d'elements de la llista  */
    public int getSize() {
        // TODO: invoca el mètode privat recursiu
        return 0;
    }

    // Mètode recursiu privat
    private int getSizeRec(Node node) {
        // TODO: implementa un mètode recursiu per comptar el nombre de nodes
        return 0;
    }


    /** Mostra el contingut de la llista */
    @Override
    public String toString() {
        // TODO: recorre tots els elements i genera un String amb el contingut
        // Exemple de una seqüència amb valors 1,3,5,8:
        // Seqüencia ordenada:
        // 0 - 1
        // 1 - 3
        // 2 - 5
        // 3 - 8
        return "TODO";
    }

    /** Clona parcialment la llista fins a un valor màxim */
    public SequenciaOrdenada<E> clonarParcial(E valorMaxim) throws CloneNotSupportedException {
        // TODO: crea una còpia de la seqüència fins a l’element que sigui <= valorMaxim
        return null;
    }

    // --- Iterable: implementem iterator() perquè funcioni el for-each ---
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node actual = primer;

            @Override
            public boolean hasNext() {
                // TODO: retorna true mentre hi hagi elements
                return false;
            }

            @Override
            public E next() {
                // TODO: retorna el valor actual i avança al següent node
                return null;
            }

            @Override
            public void remove() {
                //no implementem remove via iterator
                throw new UnsupportedOperationException("remove no suportat per aquest iterador");
            }
        };
    }
}