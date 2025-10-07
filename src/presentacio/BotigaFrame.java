package presentacio;

import domini.Article;
import domini.InventariJugador;
import domini.MyException;
import domini.SequenciaOrdenada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotigaFrame extends JFrame {

    private final InventariJugador jugador;
    private final SequenciaOrdenada<Article> botiga;
    private final JLabel labelMonedes;
    private final DefaultListModel<Article> modelLlista;
    private final JList<Article> llistaArticles;

    private Runnable onTancar;

    public BotigaFrame(InventariJugador jugador, SequenciaOrdenada<Article> botiga) {
        this.jugador = jugador;
        this.botiga = botiga;

        setTitle("Botiga del joc");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no tanquem tota l’app
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ─────────────────────────────────────────────
        // Panell superior: monedes disponibles
        // ─────────────────────────────────────────────
        JPanel panellSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelMonedes = new JLabel("Monedes disponibles: " + jugador.getMonedesJugador());
        labelMonedes.setFont(new Font("Arial", Font.BOLD, 16));
        panellSuperior.add(labelMonedes);
        add(panellSuperior, BorderLayout.NORTH);

        // ─────────────────────────────────────────────
        // Panell central: llista d’articles
        // ─────────────────────────────────────────────
        modelLlista = new DefaultListModel<>();
        for (Article a : botiga) {
            modelLlista.addElement(a);
        }

        llistaArticles = new JList<>(modelLlista);
        llistaArticles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        llistaArticles.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(llistaArticles);
        add(scroll, BorderLayout.CENTER);

        // ─────────────────────────────────────────────
        // Panell inferior: botons d’acció
        // ─────────────────────────────────────────────
        JPanel panellInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnComprar = new JButton("Comprar");
        JButton btnTancar = new JButton("Tornar a l’inventari");

        btnComprar.addActionListener(new ComprarListener());
        btnTancar.addActionListener(e -> tancarFinestra());

        panellInferior.add(btnComprar);
        panellInferior.add(btnTancar);

        add(panellInferior, BorderLayout.SOUTH);

        // ─────────────────────────────────────────────
        // Listener per capturar el tancament manual (botó X)
        // ─────────────────────────────────────────────
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tancarFinestra(); // mateix comportament que el botó "Tornar"
            }
        });
    }

    // ─────────────────────────────────────────────
    // Gestiona el tancament, ja sigui botó o manual
    // ─────────────────────────────────────────────
    private void tancarFinestra() {
        if (onTancar != null) onTancar.run();
        dispose();
    }

    // ─────────────────────────────────────────────
    // Classe interna: gestionar el botó comprar
    // ─────────────────────────────────────────────
    private class ComprarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Article seleccionat = llistaArticles.getSelectedValue();
            if (seleccionat == null) {
                JOptionPane.showMessageDialog(BotigaFrame.this,
                        "Has de seleccionar un article per comprar!",
                        "Cap article seleccionat", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (jugador.getMonedesJugador() < seleccionat.getPreu()) {
                JOptionPane.showMessageDialog(BotigaFrame.this,
                        "No tens prou monedes per comprar " + seleccionat.getNom(),
                        "Monedes insuficients", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Compra validada
            try {
                jugador.afegirPrincipi(seleccionat);
                jugador.setMonedesJugador(jugador.getMonedesJugador() - seleccionat.getPreu());
            } catch (MyException ex) {
                JOptionPane.showMessageDialog(BotigaFrame.this,
                        "Error en comprar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 🔧 Actualitzar saldo correctament
            labelMonedes.setText("Monedes disponibles: " + jugador.getMonedesJugador());

            JOptionPane.showMessageDialog(BotigaFrame.this,
                    "Has comprat: " + seleccionat.getNom() +
                            " per " + seleccionat.getPreu() + " monedes!",
                    "Compra realitzada", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ─────────────────────────────────────────────
    // Callback per tornar a l’inventari
    // ─────────────────────────────────────────────
    public void setOnTancar(Runnable onTancar) {
        this.onTancar = onTancar;
    }
}
