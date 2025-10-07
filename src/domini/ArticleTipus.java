package domini;

public enum ArticleTipus {
    ARMA("Arma"),
    ARMADURA("Armadura"),
    POCIO("Pocio"),
    RECURS("Recurs"),
    JUGADOR("Jugador");

    private final String descripcio;

    ArticleTipus(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    @Override
    public String toString() {
        return descripcio;
    }

    public static ArticleTipus fromString(String text) throws MyException {
        for (ArticleTipus tipus : ArticleTipus.values()) {
            if (tipus.descripcio.equalsIgnoreCase(text)) {
                return tipus;
            }
        }
        throw new MyException("No s'ha trobat cap tipus d'article amb la descripció: " + text);
    }
}
