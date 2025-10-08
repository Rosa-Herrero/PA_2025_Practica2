package domini;

import java.util.ArrayList;

public class InventariJugador implements Comparable<InventariJugador> {

    /** Seqüència enllaçada CIRCULAR amb CAPÇALERA
     * alguns articles podran estar repetits i d'altres són únics.**/

    // --- Atributs ---
    private Node cap; // capçalera  = jugador = primer node de la llista enllaçada

    // Classe interna per als nodes
    private static class Node {
        Article info;
        Node seguent;

        Node(Article info) {
            this.info = info;
            this.seguent = null;
        }
    }

    // --- Constructor ---
    public InventariJugador(String nomJugador, int monedesJugador) throws MyException {
        // TODO:
        //  - Comprova que el nom no sigui null → llança MyException si ho és
        //  - Comprova que les monedes no siguin negatives → llança MyException si ho són
        //  - Crea el node cap amb un Article que contingui el nom del jugador
        //  - Desa les monedes dins del camp preu d’aquest article
        //  - Fes que el cap apunti a si mateix (llista circular)
    }

    // --- Getters i setters ---
    public String getNomJugador() {
        // TODO: retorna el nom del jugador des del node cap
        return null;
    }

    public int getMonedesJugador() {
        // TODO: retorna les monedes del jugador (guardades al camp preu de l’article cap)
        return 0;
    }

    public void setMonedesJugador(int monedesJugador) throws MyException {
        // TODO:
        //  - Comprova que les monedes no siguin negatives → llança MyException si ho són
        //  - Actualitza el valor dins del node cap
    }

    // --- Mètodes públics ---

    /** Afegeix un article al final de la llista */
    public void afegirFinal(Article article) throws MyException {
        // TODO:
        //  - Comprova que l’article no sigui null → llança MyException si ho és
        //  - Comprova que la llista tingui capçalera → llança MyException si cap és null
        //  - Recorre fins al darrer node compte que és circular
    }

    /** Afegeix un article al principi de la llista, després del cap */
    public void afegirPrincipi(Article article) throws MyException {
        // TODO:
        //  - Comprova que l’article no sigui null → llança MyException si ho és
        //  - Comprova que la llista tingui capçalera → llança MyException si cap és null
        //  - Inserta el nou node just després del cap
    }

    /** Elimina el primer article que coincideixi amb equals */
    public boolean eliminar(Article articleBuscat) throws MyException {
        // TODO:
        //  - Comprova que hi hagi capçalera → llança MyException si cap és null
        //  - Si l’article buscat és el del cap → llança MyException (no es pot eliminar el cap)
        //  - Cerca l’article dins la llista circular
        //  - Si el trobes, elimina’l i retorna true
        //  - Si no, retorna false
        return false;
    }

     public ArrayList<Article> getTres( int index){
         // TODO:
         //  - Recorre la llista fins a arribar a la posició indicada
         //  - Retorna l’article d’aquella posició i els dos següents (sense repetir)
         //  - Gestiona el cas circular (si arribes al cap, continua des del principi)
         return null;
     }

    /** Retorna la quantitat d'articles, ignorant el cap, no utilitzis recursivitat */
    public int getSize() {
        // TODO:
        //  - Compta quants nodes hi ha sense comptar el cap
        //  - no utilitzis recursivitat
        return 0;
    }

    /** Retorna true si l'inventari està buit */
    public boolean esBuit() {
        // TODO: retorna true si el cap és l'únic element
        return false;
    }

    /** Mostra tots els articles de l’inventari */
    @Override
    public String toString() {
        // TODO:
        //  - Crea un String amb el nom del jugador i la seva llista d’articles
        //  - Recorre la llista circular i mostra cada article amb el seu índex
        // Exemple:
        // Inventari de Jugador1:
        // 0 - Espasa (Jugador) - 50 monedes
        // 1 - Escut (Jugador) - 50 monedes
        return "TODO";
    }

    /** Permet comparar inventaris pel nom del jugador (per ordenar-los) */
    @Override
    public int compareTo(InventariJugador altre) {
        // TODO: compara alfabèticament els noms dels jugadors (ignorant majúscules/minúscules)
        return 0;
    }
}
