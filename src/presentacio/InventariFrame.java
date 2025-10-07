package presentacio;

import domini.Article;
import domini.InventariJugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class InventariFrame extends JFrame {

    private int indexActual = 0;
    private final InventariJugador inventari;
    private final JLabel[] labelArticle = new JLabel[3];
    private final JLabel labelMonedes;

    // Callbacks per al main
    private Runnable onObrirBotiga;
    private Runnable onTancar;

    public InventariFrame(InventariJugador inventari) {
        this.inventari = inventari;

        setTitle("Inventari del jugador: " + inventari.getNomJugador());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ─── Panell superior ───
        JPanel panellSuperior = new JPanel(new GridLayout(3, 1));
        panellSuperior.add(new JLabel("Nom del jugador: " + inventari.getNomJugador()));
        labelMonedes = new JLabel("Monedes: " + inventari.getMonedesJugador());
        panellSuperior.add(labelMonedes);
        panellSuperior.add(new JLabel("Inventari:", SwingConstants.CENTER));
        add(panellSuperior, BorderLayout.NORTH);

        // ─── Panell central ───
        JPanel panellCentral = new JPanel(new BorderLayout());
        JPanel panelArticles = new JPanel(new GridLayout(1, 3, 5, 0));

        JButton btnEsquerra = new JButton("<");
        JButton btnDreta = new JButton(">");

        for (int i = 0; i < labelArticle.length; i++) {
            labelArticle[i] = new JLabel(" ", SwingConstants.CENTER);
            panelArticles.add(labelArticle[i]);
        }

        btnEsquerra.addActionListener(e -> {
            if (inventari.getSize() > 0) {
                indexActual = (indexActual - 1 + inventari.getSize()) % inventari.getSize();
                actualitzarArticles();
            }
        });

        btnDreta.addActionListener(e -> {
            if (inventari.getSize() > 0) {
                indexActual = (indexActual + 1) % inventari.getSize();
                actualitzarArticles();
            }
        });

        panellCentral.add(btnEsquerra, BorderLayout.WEST);
        panellCentral.add(panelArticles, BorderLayout.CENTER);
        panellCentral.add(btnDreta, BorderLayout.EAST);
        add(panellCentral, BorderLayout.CENTER);

        // ─── Panell inferior ───
        JPanel panellInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnBotiga = new JButton("Obrir botiga");
        JButton btnSortir = new JButton("Tornar");

        btnBotiga.addActionListener(e -> {
            if (onObrirBotiga != null) onObrirBotiga.run();
        });

        btnSortir.addActionListener(e -> {
            if (onTancar != null) onTancar.run();
        });

        panellInferior.add(btnBotiga);
        panellInferior.add(btnSortir);
        add(panellInferior, BorderLayout.SOUTH);

        // ─── Actualitzar contingut inicial ───
        actualitzarArticles();

        // ─── Control del tancament manual (botó X) ───
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (onTancar != null) onTancar.run();
            }
        });
    }

    private void actualitzarArticles() {
        ArrayList<Article> articles = inventari.getTres(indexActual);
        for (int i = 0; i < labelArticle.length; i++) {
            if (i < articles.size())
                labelArticle[i].setText(articles.get(i).getNom());
            else
                labelArticle[i].setText(" ");
        }
        labelMonedes.setText("Monedes: " + inventari.getMonedesJugador());
    }

    // ─── Getters / Setters per callbacks ───
    public void setOnObrirBotiga(Runnable onObrirBotiga) {
        this.onObrirBotiga = onObrirBotiga;
    }

    public void setOnTancar(Runnable onTancar) {
        this.onTancar = onTancar;
    }
}
