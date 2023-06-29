package ui.gui.panels;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertNichtException;
import entities.Artikel;
import entities.Massengutartikel;

import javax.swing.JLabel;
import java.awt.Color;
import java.util.Vector;

public class ArtikelAnzeige extends JPanel {
    private E_Shop shop;
    private JPanel switchMainPanel;
    private JPanel switchSidePanel;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane tableJScrollPane;

    /**
     * Create the panel.
     * @throws ArtikelExistiertNichtException 
     */
    public ArtikelAnzeige(JPanel switchMainPanel, E_Shop shop) throws ArtikelExistiertNichtException {
        this.shop = shop;
        this.switchMainPanel = switchMainPanel;
        initGUI();
    }

    private void initGUI() throws ArtikelExistiertNichtException {
        setBackground(new Color(64, 0, 64));
        setLayout(null);

        // table
        model = new DefaultTableModel();
        table = new JTable(model);

        table.setBounds(53, 51, 358, 190);
        add(table);

        tableJScrollPane = new JScrollPane(table);

        model.addColumn("id");
        model.addColumn("name");
        model.addColumn("beschereibung");
        model.addColumn("bestand");
        model.addColumn("preis");
        model.addColumn("verfuegbarkeit");
        model.addColumn("ist packung");
        model.addColumn("packungsgröße");
        
        for (Artikel artikel : shop.gibAlleArtikeln()) {
            Vector<Object> row = new Vector<>();
            if (artikel instanceof Massengutartikel) {
                Massengutartikel massengutartikel = (Massengutartikel) artikel;
                row.add(massengutartikel.getArtikelId());
                row.add(massengutartikel.getName());
                row.add(massengutartikel.getBeschreibung());
                row.add(massengutartikel.getBestand());
                row.add(massengutartikel.getPreis());
                row.add(massengutartikel.isVerfuegbar());
                row.add(massengutartikel.getIstPackung());
                row.add(massengutartikel.getPackungsGroesse());
            } else {
                row.add(artikel.getArtikelId());
                row.add(artikel.getName());
                row.add(artikel.getBeschreibung());
                row.add(artikel.getBestand());
                row.add(artikel.getPreis());
                row.add(artikel.isVerfuegbar());
                row.add(artikel.getIstPackung());
            }
            model.addRow(row);
        }

        tableJScrollPane.setBounds(53, 51, 358, 190);
        add(tableJScrollPane);
    }
}
