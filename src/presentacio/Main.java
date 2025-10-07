package presentacio;

import domini.*;
import persistencia.GestorFitxers;

import javax.swing.*;

public class Main {

    private static SequenciaOrdenada<InventariJugador> jugadors;
    private static SequenciaOrdenada<Article> botiga;

    private static SeleccioJugadorFrame seleccioJugadorFrame;
    private static InventariFrame inventariFrame;
    private static BotigaFrame botigaFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                inicialitzarDades();
                mostrarSeleccioJugador();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error en carregar les dades inicials: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }

    /** Carrega jugadors i botiga des del sistema de fitxers */
    private static void inicialitzarDades() throws Exception {
        jugadors = GestorFitxers.carregarJugadors();
        botiga = GestorFitxers.carregarBotiga();
    }

    /** Mostra la finestra de selecció de jugador */
    private static void mostrarSeleccioJugador() {
        seleccioJugadorFrame = new SeleccioJugadorFrame(jugadors);

        seleccioJugadorFrame.setOnJugadorSeleccionat(jugador -> {
            seleccioJugadorFrame.dispose();
            mostrarInventari(jugador);
        });

        seleccioJugadorFrame.setOnTancar(() -> {
            // Si es tanca la finestra de selecció, finalitzem el joc
            System.exit(0);
        });

        seleccioJugadorFrame.setVisible(true);
    }

    /** Mostra el frame amb l’inventari d’un jugador */
    private static void mostrarInventari(InventariJugador jugador) {
        inventariFrame = new InventariFrame(jugador);

        inventariFrame.setOnObrirBotiga(() -> {
            inventariFrame.dispose();
            mostrarBotiga(jugador);
        });

        inventariFrame.setOnTancar(() -> {
            inventariFrame.dispose();
            mostrarSeleccioJugador(); // Tornar a la selecció de jugadors
        });

        inventariFrame.setVisible(true);
    }

    /** Mostra la botiga per un jugador */
    private static void mostrarBotiga(InventariJugador jugador) {
        botigaFrame = new BotigaFrame(jugador, botiga);

        botigaFrame.setOnTancar(() -> {
            botigaFrame.dispose();
            mostrarInventari(jugador); // Tornem a l'inventari actualitzat
        });

        botigaFrame.setVisible(true);
    }
}
