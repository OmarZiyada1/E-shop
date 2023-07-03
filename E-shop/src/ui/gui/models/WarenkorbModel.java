package ui.gui.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import entities.Artikel;
import entities.Warenkorb;

public class WarenkorbModel extends AbstractTableModel {

	private Map<Artikel, Integer> warenkorbMap;
	private String[] spaltenNamen = { "Artikel Name", "Menge" };

	public WarenkorbModel(Map<Artikel, Integer> aktuelleWarenkorbMap) {
		super();
		this.warenkorbMap = new HashMap<Artikel, Integer>();
		warenkorbMap.putAll(aktuelleWarenkorbMap);

	}

	public void setWarenkorb(Map<Artikel, Integer> aktuelleWarenkorbMap) {
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
		

		return null;
	}

}
