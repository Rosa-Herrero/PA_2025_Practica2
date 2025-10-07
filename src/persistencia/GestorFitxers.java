package persistencia;

import domini.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorFitxers {

    /* Atenció: aquesta classe pertany a la capa de persistència (no a la de domini)
     * Per tant, no s'hauria d'invocar des de la capa presentació*/
    private GestorFitxers(){}

    /* Retorna una llista amb els noms dels fitxers de jugadors
     * trobats a la carpeta Files/Jugadors (sense extensió .txt)*/
    public static SequenciaOrdenada<InventariJugador> carregarJugadors() throws MyException {
        SequenciaOrdenada<InventariJugador> jugadors = new SequenciaOrdenada<>();
        File carpeta = new File("Files/Players");

        if (!carpeta.exists() || !carpeta.isDirectory())
            throw new MyException("⚠ No s'ha trobat la carpeta Files/Player.");


        File[] fitxers = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        if (fitxers == null)
            throw new MyException("⚠ Carpeta Files/Player buida.");

        for (File f : fitxers) {
            jugadors.inserir(carregarInventari(f.getAbsolutePath()));
        }

        return jugadors;
    }

    /* Carrega un fitxer d'inventari de jugador i retorna un objecte InventariJugador
     * Format del fitxer "Files/Player/NomJugador.txt":
     * NomJugador,monedes
     * NomArticle,preu,tipus
     * ... altres articles... */
    private static InventariJugador carregarInventari(String pathname) throws MyException {
        File fitxer = new File(pathname);

        if (!fitxer.exists()) {
            throw new MyException("No s'ha trobat el fitxer per al jugador: " + pathname);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String primeraLinia = br.readLine();
            if (primeraLinia == null) {
                throw new MyException("Fitxer buit per al jugador: " + pathname);
            }

            // Primera línia → NomJugador,monedes
            String[] cap = primeraLinia.split(",");
            if (cap.length != 2) {
                throw new MyException("Format incorrecte a la primera línia del fitxer de " + pathname);
            }

            String nom = cap[0].trim();
            int monedes = Integer.parseInt(cap[1].trim());

            // Crear inventari
            InventariJugador inventari = new InventariJugador(nom, monedes);

            // Llegir articles
            String linia;
            while ((linia = br.readLine()) != null) {
                if (linia.trim().isEmpty()) continue;
                String[] parts = linia.split(",");
                if (parts.length != 3) continue;

                String nomArticle = parts[0].trim();
                int preu = Integer.parseInt(parts[1].trim());
                ArticleTipus tipus = ArticleTipus.fromString(parts[2].trim());

                Article article = new Article(nomArticle, preu, tipus);
                inventari.afegirFinal(article);
            }

            return inventari;

        } catch (IOException e) {
            throw new MyException("Error en llegir el fitxer: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new MyException("Format numèric incorrecte al fitxer de " + pathname);
        } catch (IllegalArgumentException e) {
            throw new MyException("Tipus d'article desconegut al fitxer de " + pathname);
        }
    }

    /* Carrega tots els articles de la botiga des del fitxer Files/Botiga/botiga.txt
     * i els retorna dins d'una SequenciaOrdenada<Article>.
     * Format esperat del fitxer:
     * NomArticle,preu,tipus */
    public static SequenciaOrdenada<Article> carregarBotiga() {
        String pathFile = "Files/Botiga.txt";
        SequenciaOrdenada<Article> botiga = new SequenciaOrdenada<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                if (linia.trim().isEmpty()) continue; // línia buida
                String[] parts = linia.split(",");
                if (parts.length == 3) {
                    try {
                        String nom = parts[0].trim();
                        int preu = Integer.parseInt(parts[1].trim());
                        ArticleTipus tipus = ArticleTipus.fromString(parts[2].trim());
                        Article article = new Article(nom, preu, tipus);
                        if( !botiga.inserir(article) )
                            System.err.println("Article duplicat a la botiga (no s'afegeix): " + linia);
                    } catch (Exception e) {
                        System.err.println("Error en carregar l'article: " + linia + " → " + e.getMessage());
                    }
                } else {
                    System.err.println("Format incorrecte a la línia: " + linia);
                }
            }
        } catch (IOException e) {
            System.err.println("Error en llegir el fitxer de la botiga: " + e.getMessage());
        }

        return botiga;
    }
}

