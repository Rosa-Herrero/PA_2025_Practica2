package domini;


public class Article implements Comparable<Article> {
    private final String nom;
    private int preu; // o monedes en el cas del jugador
    private final ArticleTipus tipus;

    // --- Constructor ---
    public Article (String nomJugador) {
        this.nom = nomJugador;
        this.preu = 50;
        this.tipus = ArticleTipus.JUGADOR;
    }
    public Article(String nom, int preu, ArticleTipus tipus) {
        this.nom = nom;
        this.preu = preu;
        this.tipus = tipus;
    }

    // --- Getters i Setters ---
    public String getNom() {
        return this.nom;
    }

    public int getPreu() {
        return this.preu;
    }

    public void setPreu(int monedesJugador) {
        this.preu = monedesJugador;
    }

    /* Comparable:
     * si són jugadors ordenem per nom
     * altres: per defecte ordenem per preu, si és igual ordena alfabèticament
     */
    @Override
    public int compareTo(Article other) {
        if( this.tipus == ArticleTipus.JUGADOR && other.tipus == ArticleTipus.JUGADOR)
            return this.nom.compareToIgnoreCase(other.nom);
        int dif = Integer.compare(this.preu, other.preu);
        if( dif == 0) return this.nom.compareToIgnoreCase(other.nom);
        return dif;
    }

    // --- Equals i hashCode (per detectar articles iguals) ---
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(!(obj instanceof Article)) return false;
        Article other = (Article) obj;
        return this.preu == other.preu &&
                this.nom.equals(other.nom) &&
                this.tipus.equals(other.tipus);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nom, preu, tipus);
    }

    // --- ToString ---
    @Override
    public String toString() {
        return nom + " (" + tipus + ") - " + preu + " monedes";
    }
}
