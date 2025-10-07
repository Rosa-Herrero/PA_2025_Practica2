package presentacio;

import domini.InventariJugador;
import domini.SequenciaOrdenada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class SeleccioJugadorFrame extends JFrame {

    private Consumer<InventariJugador> onJugadorSeleccionat;
    private Runnable onTancar;

    private final JList<String> llistaJugadors;
    private final SequenciaOrdenada<InventariJugador> jugadors;

    public SeleccioJugadorFrame(SequenciaOrdenada<InventariJugador> jugadors) {
        super("Selecció de jugador");
        this.jugadors = jugadors;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Llista de noms dels jugadors
        DefaultListModel<String> model = new DefaultListModel<>();
        for (InventariJugador j : jugadors) {
            model.addElement(j.getNomJugador());
        }

        llistaJugadors = new JList<>(model);
        llistaJugadors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(llistaJugadors), BorderLayout.CENTER);

        // Botons inferiors
        JPanel panellBotons = new JPanel();
        JButton btnSeleccionar = new JButton("Seleccionar jugador");
        JButton btnSortir = new JButton("Sortir");

        panellBotons.add(btnSeleccionar);
        panellBotons.add(btnSortir);
        add(panellBotons, BorderLayout.SOUTH);

        // Acció per seleccionar un jugador
        btnSeleccionar.addActionListener(this::seleccionarJugador);

        // Acció per sortir
        btnSortir.addActionListener(e -> {
            if (onTancar != null) onTancar.run();
            dispose();
        });
    }

    private void seleccionarJugador(ActionEvent e) {
        int index = llistaJugadors.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un jugador de la llista");
            return;
        }

        // Obtenim el jugador seleccionat
        InventariJugador jugadorSeleccionat = null;
        int i = 0;
        for (InventariJugador j : jugadors) {
            if (i == index) {
                jugadorSeleccionat = j;
                break;
            }
            i++;
        }

        // Notifiquem al controlador principal
        if (onJugadorSeleccionat != null && jugadorSeleccionat != null) {
            onJugadorSeleccionat.accept(jugadorSeleccionat);
        }
    }

    // Mètode per definir el callback quan es selecciona un jugador
    public void setOnJugadorSeleccionat(Consumer<InventariJugador> listener) {
        this.onJugadorSeleccionat = listener;
    }

    // Mètode per definir què passa quan es tanca la finestra
    public void setOnTancar(Runnable listener) {
        this.onTancar = listener;
    }
}
