package ui.gui.models;

import entities.Artikel;
import entities.Massengutartikel;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class ArtikelTableModel extends AbstractTableModel {

	private List<Artikel> artikeln;
	private String[] spaltenNamen = { "ID", "Name", "Beschreibung", "Preis", "Bestand", "MassengutArtikel",
	"Packungsgroeße" };

	public ArtikelTableModel(List<Artikel> aktuelleArtikeln) {
		super();
		// Ich erstelle eine Kopie der Bücherliste,
		// damit beim Aktualisieren (siehe Methode setBooks())
		// keine unerwarteten Seiteneffekte entstehen.
		artikeln = new Vector<Artikel>();
		artikeln.addAll(aktuelleArtikeln);
	}

	public void setArtikeln(List<Artikel> aktuelleArtikeln) {
		artikeln.clear();
		artikeln.addAll(aktuelleArtikeln);
		//Änderungen an den Daten des Modells mitzuteilen, 
		//damit die JTable diese Änderungen reflektieren kann.
		fireTableDataChanged();
	}

	/*
	 * Ab hier überschriebene Methoden mit Informationen, die eine JTable von jedem
	 * TableModel erwartet: - Anzahl der Zeilen - Anzahl der Spalten - Benennung der
	 * Spalten - Wert einer Zelle in Zeile / Spalte
	 */

	@Override
	public int getRowCount() {
		return artikeln.size();
	}

	@Override
	public int getColumnCount() {
		return spaltenNamen.length;
	}

	@Override
	public String getColumnName(int col) {
		return spaltenNamen[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Artikel gewaehltesArtikel = artikeln.get(row);
		
		if (gewaehltesArtikel.getIstPackung()) {
			Massengutartikel gewaehltesArtikel_1 = (Massengutartikel) gewaehltesArtikel;
			switch (col) {
			case 0:
				return gewaehltesArtikel_1.getArtikelId();
			case 1:
				return gewaehltesArtikel_1.getName();
			case 2:
				return gewaehltesArtikel_1.getBeschreibung();
			case 3:
				return gewaehltesArtikel_1.getPreis();
			case 4:
				return gewaehltesArtikel_1.getBestand();
			case 5:
				return gewaehltesArtikel_1.getIstPackung();
			case 6:
				return gewaehltesArtikel_1.getPackungsGroesse();
			default:
				return null;
			}
		} else {

			switch (col) {
			case 0:
				return gewaehltesArtikel.getArtikelId();
			case 1:
				return gewaehltesArtikel.getName();
			case 2:
				return gewaehltesArtikel.getBeschreibung();
			case 3:
				return gewaehltesArtikel.getPreis();
			case 4:
				return gewaehltesArtikel.getBestand();
			case 5:
				return gewaehltesArtikel.getIstPackung();
			default:
				return null;
			}
		}

	}

}
