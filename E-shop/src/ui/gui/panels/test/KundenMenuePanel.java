package ui.gui.panels.test;

import javax.swing.JPanel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import domain.exceptions.NichtGenugArtikelVorhandenException;
import entities.Artikel;
import entities.Kunde;
import ui.gui.models.WarenkorbModel;
import ui.gui.panels.test.MitarbeiterMenuePanel.TableDataListener;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class KundenMenuePanel extends JPanel {
	private JButton btn_ArtikelAnzeigen;
	private JButton btn_Warenkorb;
	private E_Shop shop;
	private Kunde kunde;
	private OnWarenkorpListener onWarenkorpListener;
	private TableDataListener tableDataListener;
	private JButton btnAddArtikel;
	
	
	public interface OnWarenkorpListener{
		void updateToWarenkorb();
		void updateToArtikel();
		Artikel onSelectedRow();
		void updateWarenKorb();
	}

	/**
	 * Create the panel.
	 */
	public KundenMenuePanel(E_Shop shop, Kunde kunde, TableDataListener tableDataListener , OnWarenkorpListener onWarenkorpListener) {
		this.tableDataListener=tableDataListener;
		this.shop = shop;
		this.onWarenkorpListener = onWarenkorpListener;
		this.kunde = kunde;
		this.onWarenkorpListener=onWarenkorpListener ;
		initGUI();
	}

	private void initGUI() {
		setBorder(new TitledBorder(null, "Kunde Menue", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		{
			this.btn_ArtikelAnzeigen = new JButton("Artikeln anzeigen");
			this.btn_ArtikelAnzeigen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btn_ArtikelAnzeigen_actionPerformed(e);
				}
			});
			add(this.btn_ArtikelAnzeigen);
		}
		{
			this.btn_Warenkorb = new JButton("Warenkorb");
			this.btn_Warenkorb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btn_Warenkorb_actionPerformed(e);
				}
			});
			add(this.btn_Warenkorb);
		}
		{
			this.btnAddArtikel = new JButton("Add Artikel");
			this.btnAddArtikel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnAddArtikel_actionPerformed(e);
				}
			});
			add(this.btnAddArtikel);
		}
	}

	protected void do_btn_Warenkorb_actionPerformed(ActionEvent e) {
	
		onWarenkorpListener.updateToWarenkorb();
	}
	protected void do_btn_ArtikelAnzeigen_actionPerformed(ActionEvent e) {
		onWarenkorpListener.updateToArtikel();
	}
	
	protected void do_btnAddArtikel_actionPerformed(ActionEvent e) {
		int menge = 0;
		Artikel artikel= onWarenkorpListener.onSelectedRow();
		String result = JOptionPane.showInputDialog(null, " Bitte zu hinzufuegen Menge eingeben:", "Eingabe >",
				JOptionPane.PLAIN_MESSAGE);
		menge = Integer.parseInt(result);
		
		try {
			shop.fuegeArtikelInkorbEin(kunde, artikel, menge);
			//System.out.println(kunde.getKundeWarenkorb());
			onWarenkorpListener.updateWarenKorb();
		} catch (NichtGenugArtikelVorhandenException | BestandPasstNichtMitPackungsGroesseException
				| ArtikelExistiertNichtException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
