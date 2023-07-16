package ui.gui.panels.test;

import entities.Artikel;
import entities.Kunde;
import entities.Mitarbeiter;
import entities.Nutzer;
import ui.gui.models.ArtikelTableModel;
import ui.gui.models.ArtikelTableModel2Kunde;
import ui.gui.models.WarenkorbModel;
import ui.gui.panels.test.KundenMenuePanel.OnWarenkorpListener;

import javax.accessibility.AccessibleStreamable;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import domain.E_Shop;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtikelnTablePanel extends JTable {
	ArtikelTableModel tableModelMitarbeiter;
	ArtikelTableModel2Kunde tableModelKunde;
	Mitarbeiter mitarbeiter;
	Kunde kunde;
	E_Shop shop;
	WarenkorbModel warenKorbModel;
	OnWarenkorpListener onWarenkorpListener;

	public ArtikelnTablePanel(E_Shop shop, Mitarbeiter mitarbeiter) {
		super();
		this.mitarbeiter = mitarbeiter;
		this.shop = shop;
		this.tableModelMitarbeiter = new ArtikelTableModel(shop.gibAlleArtikeln());
		this.setModel(tableModelMitarbeiter);
		sortTableMouseClick(shop.gibAlleArtikeln());
		updateArtikelnList(shop.gibAlleArtikeln());
	}

	public ArtikelnTablePanel(E_Shop shop, Kunde kunde) {
		super();
		this.kunde = kunde;
		this.tableModelKunde = new ArtikelTableModel2Kunde(shop.gibAlleArtikeln());
		this.setModel(tableModelKunde);
		// WarenKorbmodel erzeugen ...
		sortTableMouseClick(shop.gibAlleArtikeln());
		updateArtikelnList(shop.gibAlleArtikeln());	
	}



	/**
	 * @param Artikeln
	 */
	public void sortTableMouseClick(List<Artikel> Artikeln) {

		this.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// ... Inhalt aktualisieren
				if (mitarbeiter != null) {
					int col = getColumnModel().getColumnIndexAtX(e.getX());
					String colName = tableModelMitarbeiter.getColumnName(col);

					if (colName.equals("Name")) {
						Collections.sort(Artikeln, (a1, a2) -> a1.getName().compareTo(a2.getName()));
					} else if (colName.equals("ID")) {
						Collections.sort(Artikeln, (a1, a2) -> a1.getArtikelId() - a2.getArtikelId());
					} else if (colName.equals("Preis")) {
						Collections.sort(Artikeln, (a1, a2) -> Double.compare(a1.getPreis(), a2.getPreis()));
					}
					tableModelMitarbeiter = (ArtikelTableModel) getModel();
					tableModelMitarbeiter.setArtikeln(Artikeln);
				} else if (kunde != null) {
					int col = getColumnModel().getColumnIndexAtX(e.getX());
					String colName = tableModelKunde.getColumnName(col);

					if (colName.equals("Name")) {
						Collections.sort(Artikeln, (a1, a2) -> a1.getName().compareTo(a2.getName()));
					} else if (colName.equals("ID")) {
						Collections.sort(Artikeln, (a1, a2) -> a1.getArtikelId() - a2.getArtikelId());
					} else if (colName.equals("Preis")) {
						Collections.sort(Artikeln, (a1, a2) -> Double.compare(a1.getPreis(), a2.getPreis()));
					}
					tableModelKunde = (ArtikelTableModel2Kunde) getModel();
					tableModelKunde.setArtikeln(Artikeln);
				}
			}
		});
	}

	public void updateArtikelnList(java.util.List<Artikel> artikel) {
		if (mitarbeiter != null) {

			// TableModel von JTable holen und ...
			ArtikelTableModel tableModel = (ArtikelTableModel) getModel();
			// ... Inhalt aktualisieren
			tableModel.setArtikeln(artikel);
		} else if (kunde != null) {
			// TableModel von JTable holen und ...
			ArtikelTableModel2Kunde tableModelKunde = (ArtikelTableModel2Kunde) getModel();
			// ... Inhalt aktualisieren
			tableModelKunde.setArtikeln(artikel);
		}
	}

	public int selectedrowIndex() {

		int selectedRow = this.getSelectedRow();
		
		return selectedRow;

	}

}
