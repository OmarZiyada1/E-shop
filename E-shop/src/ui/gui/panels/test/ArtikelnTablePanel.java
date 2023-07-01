package ui.gui.panels.test;
import entities.Artikel;
import ui.gui.models.ArtikelTableModel;
import javax.swing.*;
import java.util.Collections;
public class ArtikelnTablePanel extends JTable {

	public ArtikelnTablePanel(java.util.List<Artikel> buecher) {
		super();

		// TableModel erzeugen ...
		ArtikelTableModel tableModel = new ArtikelTableModel(buecher);
		// ... bei JTable "anmelden" und ...
		setModel(tableModel);
		// ... Daten an Model übergeben (für Sortierung o.ä.)
		updateArtikelnList(buecher);
	}
	
	public void updateArtikelnList(java.util.List<Artikel> artikel) {

		// Sortierung (mit Lambda-Expression)
//		Collections.sort(buecher, (b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel
		Collections.sort(artikel, (a1, a2) -> a1.getArtikelId() - a2.getArtikelId());	// Sortierung nach Nummer

		// TableModel von JTable holen und ...
		ArtikelTableModel tableModel = (ArtikelTableModel) getModel();
		// ... Inhalt aktualisieren
		tableModel.setArtikeln(artikel);
	}
}
