package ui.gui.panels.test;

import entities.Artikel;

import ui.gui.models.ArtikelTableModel;
import ui.gui.models.WarenkorbModel;

import javax.accessibility.AccessibleStreamable;
import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ArtikelnTablePanel extends JTable {

	public ArtikelnTablePanel(List<Artikel> Artikeln) {
		super();

		// TableModel erzeugen ...
		ArtikelTableModel tableModel = new ArtikelTableModel(Artikeln);
		// ... bei JTable "anmelden" und ...
		setModel(tableModel);
		// ... Daten an Model übergeben (für Sortierung o.ä.)
		updateArtikelnList(Artikeln);
	}

	public ArtikelnTablePanel(Map<Artikel, Integer> WarenkorbMap) {
		super();

		// WarenKorbmodel erzeugen ...
		WarenkorbModel tableModel = new WarenkorbModel(WarenkorbMap);
		// ... bei JTable "anmelden" und ...
		setModel(tableModel);

		// ... Daten an Model übergeben (für Sortierung o.ä.)
		// updateArtikelnList(WarenkorbMap);
	}

	public void updateArtikelnList(java.util.List<Artikel> artikel) {

		// Sortierung (mit Lambda-Expression)
//		Collections.sort(buecher, (b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel
		Collections.sort(artikel, (a1, a2) -> a1.getArtikelId() - a2.getArtikelId()); // Sortierung nach Nummer

		// TableModel von JTable holen und ...
		ArtikelTableModel tableModel = (ArtikelTableModel) getModel();
		// ... Inhalt aktualisieren
		tableModel.setArtikeln(artikel);
	}
}
