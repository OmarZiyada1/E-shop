package ui.gui.panels;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertNichtException;
import entities.Artikel;
import entities.Massengutartikel;

import java.awt.Color;
import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ArtikelAnzeige extends JPanel {
    private E_Shop shop;
    private JPanel switchMainPanel;
    private JPanel switchSidePanel;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane tableJScrollPane;
    private JTextField textField;
    private JButton btn_suchen;

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
        setBackground(Color.DARK_GRAY);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{35, 716, 0};
        gridBagLayout.rowHeights = new int[]{47, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0};
       this.setLayout(gridBagLayout);
        
        

        // table
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setBackground(Color.DARK_GRAY);

        table.setBounds(0, 0, 358, 190);
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
        
        JPanel panel_suchenContainer = new JPanel();
        panel_suchenContainer.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc_panel_suchenContainer = new GridBagConstraints();
        gbc_panel_suchenContainer.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_suchenContainer.insets = new Insets(0, 0, 5, 5);
        gbc_panel_suchenContainer.gridx = 1;
        gbc_panel_suchenContainer.gridy = 0;
        add(panel_suchenContainer, gbc_panel_suchenContainer);
        GridBagLayout gbl_panel_suchenContainer = new GridBagLayout();
        gbl_panel_suchenContainer.columnWidths = new int[]{86, 85, 0};
        gbl_panel_suchenContainer.rowHeights = new int[]{21, 0};
        gbl_panel_suchenContainer.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_panel_suchenContainer.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_suchenContainer.setLayout(gbl_panel_suchenContainer);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 0, 5);
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        panel_suchenContainer.add(textField, gbc_textField);
        textField.setColumns(10);
        
        btn_suchen = new JButton("Suchen");
        GridBagConstraints gbc_btn_suchen = new GridBagConstraints();
        gbc_btn_suchen.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_suchen.anchor = GridBagConstraints.NORTH;
        gbc_btn_suchen.gridx = 1;
        gbc_btn_suchen.gridy = 0;
        panel_suchenContainer.add(btn_suchen, gbc_btn_suchen);

        tableJScrollPane.setBounds(0, 0, 358, 190);
        GridBagConstraints gbc_tableJScrollPane = new GridBagConstraints();
        gbc_tableJScrollPane.insets = new Insets(0, 0, 0, 5);
        gbc_tableJScrollPane.gridx = 1;
        gbc_tableJScrollPane.gridy = 1;
        gbc_tableJScrollPane.fill = GridBagConstraints.BOTH;
        add(tableJScrollPane, gbc_tableJScrollPane);
    }
}
