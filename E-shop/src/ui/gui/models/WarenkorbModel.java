package ui.gui.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import entities.Artikel;

public class WarenkorbModel extends AbstractTableModel {

	private HashMap<Artikel, Integer> warenkorbMap;
	private String[] spaltenNamen = { "Artikel Name", "Menge" };

	public WarenkorbModel(HashMap<Artikel, Integer> aktuelleArtikeln) {
		super();

		warenkorbMap = new HashMap<Artikel, Integer>();
		warenkorbMap.putAll(aktuelleArtikeln);

	}

	public void setWarenkorb(HashMap<Artikel, Integer> aktuelleWarenkorbMap) {
		System.out.println("hier");
		warenkorbMap.clear();
		warenkorbMap.putAll(aktuelleWarenkorbMap);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {

		return warenkorbMap.size();
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		List<Artikel> keys = new ArrayList<Artikel>(warenkorbMap.keySet());
		Artikel artikel = keys.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return artikel.getName();
		case 1:
			return warenkorbMap.get(artikel);
		default:
			return null;
		}
	}
}
