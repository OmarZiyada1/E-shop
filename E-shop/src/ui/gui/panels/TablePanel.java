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
import javax.swing.JOptionPane;

public class TablePanel extends JPanel {
	private E_Shop shop;

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane tableJScrollPane;

	/**
	 * Create the panel.
	 * 
	 * @throws ArtikelExistiertNichtException
	 */
	public TablePanel(E_Shop shop) throws ArtikelExistiertNichtException {
		this.shop = shop;
		initGUI();
	}

	private void initGUI() throws ArtikelExistiertNichtException {
		setBackground(Color.DARK_GRAY);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 716, 0 };
		gridBagLayout.rowHeights = new int[] { 47, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
		this.setLayout(gridBagLayout);

		// table
		model = new DefaultTableModel();
		table = new JTable(model);
		this.table.setForeground(Color.WHITE);
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

		tableJScrollPane.setBounds(0, 0, 358, 190);
		GridBagConstraints gbc_tableJScrollPane = new GridBagConstraints();
		gbc_tableJScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_tableJScrollPane.gridx = 0;
		gbc_tableJScrollPane.gridy = 1;
		gbc_tableJScrollPane.fill = GridBagConstraints.BOTH;
		add(tableJScrollPane, gbc_tableJScrollPane);
	}

	public int selectedrow() {
		// -1 bedeuted dass keines row selected
		
		int selectedRow = -1;
		if (table.getSelectedRow() != -1 && table.getSelectedRowCount() == 1) {
			selectedRow = table.getSelectedRow();
		} else {
			JOptionPane.showMessageDialog(null, "Bitte nur einen Artikel auswählen", "info",
					JOptionPane.INFORMATION_MESSAGE);
		}
		System.out.println(selectedRow );
		return selectedRow;

	}
	
	
	
}
