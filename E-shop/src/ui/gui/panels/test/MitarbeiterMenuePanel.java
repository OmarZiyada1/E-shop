package ui.gui.panels.test;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import domain.exceptions.SenkenUnterNullNichtMoeglichException;
import entities.Artikel;
import entities.Mitarbeiter;
import entities.Nutzer;
import entities.Verlauf;
import ui.gui.panels.test.AddArtikelPanel.AddArtikelListener;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MitarbeiterMenuePanel extends JPanel {
	private JButton btnLoeschen;
	private JButton btnBestand_Senken;
	private JButton btnArtikel_Erhoehen;
	private JButton btnZeigeverlauf;
	private TableDataListener tableDataListener;
	private E_Shop shop;
	private Mitarbeiter mitarbeiter;
	private JButton btnSelectedArtikelVerlauf;
	private Artikel artikel;
	private JButton btnArtikelnAnzeigen;
	public interface TableDataListener {
		void updateToArtikel();
		public Artikel onSelctedRow();

		public void updateTable();

		public void updateVerlauf();

		public void updateToVerlauf();

		Verlauf onSelctedRowVerlauf();

		void updateVerlauf30(Artikel artikel);

		void updateTo30Verlauf();
	}

	/**
	 * Create the panel.
	 */
	public MitarbeiterMenuePanel(TableDataListener tableDataListener, E_Shop shop, Nutzer loggedNutzer) {
		
		this.tableDataListener = tableDataListener;
		this.shop = shop;
		this.mitarbeiter = (Mitarbeiter) loggedNutzer;
		initGUI();
	}

	private void initGUI() {
		
		setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Menue",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 107, 0 };
		gridBagLayout.rowHeights = new int[] { 21, 21, 21, 21, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		{
			this.btnLoeschen = new JButton("Artikel Löschen");
			this.btnLoeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnLoeschen_actionPerformed(e);
				}
			});
			GridBagConstraints gbc_btnLoeschen = new GridBagConstraints();
			gbc_btnLoeschen.fill = GridBagConstraints.BOTH;
			gbc_btnLoeschen.insets = new Insets(0, 0, 5, 0);
			gbc_btnLoeschen.gridx = 0;
			gbc_btnLoeschen.gridy = 0;
			add(this.btnLoeschen, gbc_btnLoeschen);
		}
		{
			this.btnBestand_Senken = new JButton("Bestand Senken");
			this.btnBestand_Senken.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnBestand_Senken_actionPerformed(e);
				}
			});
			GridBagConstraints gbc_btnBestand_Senken = new GridBagConstraints();
			gbc_btnBestand_Senken.fill = GridBagConstraints.BOTH;
			gbc_btnBestand_Senken.insets = new Insets(0, 0, 5, 0);
			gbc_btnBestand_Senken.gridx = 0;
			gbc_btnBestand_Senken.gridy = 1;
			add(this.btnBestand_Senken, gbc_btnBestand_Senken);
		}
		{
			this.btnArtikel_Erhoehen = new JButton("Artikel Erhoehen");
			this.btnArtikel_Erhoehen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnArtikel_Erhoehen_actionPerformed(e);
				}
			});
			GridBagConstraints gbc_btnArtikel_Erhoehen = new GridBagConstraints();
			gbc_btnArtikel_Erhoehen.fill = GridBagConstraints.BOTH;
			gbc_btnArtikel_Erhoehen.insets = new Insets(0, 0, 5, 0);
			gbc_btnArtikel_Erhoehen.gridx = 0;
			gbc_btnArtikel_Erhoehen.gridy = 2;
			add(this.btnArtikel_Erhoehen, gbc_btnArtikel_Erhoehen);
		}
		{
			this.btnZeigeverlauf = new JButton("Zeigeverlauf");
			this.btnZeigeverlauf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnZeigeverlauf_actionPerformed(e);
				}
			});
			GridBagConstraints gbc_btnZeigeverlauf = new GridBagConstraints();
			gbc_btnZeigeverlauf.insets = new Insets(0, 0, 5, 0);
			gbc_btnZeigeverlauf.fill = GridBagConstraints.BOTH;
			gbc_btnZeigeverlauf.gridx = 0;
			gbc_btnZeigeverlauf.gridy = 3;
			add(this.btnZeigeverlauf, gbc_btnZeigeverlauf);
		}
		{
			this.btnSelectedArtikelVerlauf = new JButton("Artikel Vf. letzte 30 T.");
			this.btnSelectedArtikelVerlauf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnSelectedArtikelVerlauf_actionPerformed(e);
				}
			});
			GridBagConstraints gbc_btnSelectedArtikelVerlauf = new GridBagConstraints();
			gbc_btnSelectedArtikelVerlauf.insets = new Insets(0, 0, 5, 0);
			gbc_btnSelectedArtikelVerlauf.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSelectedArtikelVerlauf.gridx = 0;
			gbc_btnSelectedArtikelVerlauf.gridy = 4;
			add(this.btnSelectedArtikelVerlauf, gbc_btnSelectedArtikelVerlauf);
		}
		{
			this.btnArtikelnAnzeigen = new JButton("Artikeln List");
			this.btnArtikelnAnzeigen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnArtikelnAnzeigen_actionPerformed(e);
				}
			});
			GridBagConstraints gbc_btnArtikelnAnzeigen = new GridBagConstraints();
			gbc_btnArtikelnAnzeigen.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnArtikelnAnzeigen.gridx = 0;
			gbc_btnArtikelnAnzeigen.gridy = 5;
			add(this.btnArtikelnAnzeigen, gbc_btnArtikelnAnzeigen);
		}
		
	}

	protected void do_btnLoeschen_actionPerformed(ActionEvent e) {
		if (tableDataListener.onSelctedRow() == null) {

			JOptionPane.showMessageDialog(null, "Bitte nur einen Artikel auswählen", "info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {

				shop.loescheArtikel(mitarbeiter, tableDataListener.onSelctedRow().getName());
				shop.schreibeArtikel();
				shop.schreibeVerlauf();
				tableDataListener.updateVerlauf();
				tableDataListener.updateTable();
			} catch (ArtikelExistiertNichtException | IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	protected void do_btnBestand_Senken_actionPerformed(ActionEvent e) {
		if (tableDataListener.onSelctedRow() == null) {
			JOptionPane.showMessageDialog(null, "Bitte nur einen Artikel auswählen", "info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				int menge = 0;
				String result = JOptionPane.showInputDialog(null, " Bitte zu senkende Menge eingeben:", "Eingabe >",
						JOptionPane.PLAIN_MESSAGE);
				menge = Integer.parseInt(result);
				shop.senkenArtikelBestand(mitarbeiter, tableDataListener.onSelctedRow().getName(), menge);
				shop.schreibeArtikel();
				shop.schreibeVerlauf();
				tableDataListener.updateVerlauf();
				tableDataListener.updateTable();
			} catch (ArtikelExistiertNichtException | BestandPasstNichtMitPackungsGroesseException | IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Die Menge muss eine ganze Zahl sein.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (SenkenUnterNullNichtMoeglichException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	protected void do_btnArtikel_Erhoehen_actionPerformed(ActionEvent e) {
		if (tableDataListener.onSelctedRow() == null) {
			JOptionPane.showMessageDialog(null, "Bitte nur einen Artikel auswählen", "info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				int menge = 0;
				String result = JOptionPane.showInputDialog(null, " Bitte zu erhöhte Menge eingeben:", "Eingabe >",
						JOptionPane.PLAIN_MESSAGE);
				menge = Integer.parseInt(result);
				shop.erhoeheArtikelBestand(mitarbeiter, tableDataListener.onSelctedRow().getName(), menge);
				shop.schreibeArtikel();
				shop.schreibeVerlauf();
				tableDataListener.updateVerlauf();
				tableDataListener.updateTable();
			} catch (ArtikelExistiertNichtException | BestandPasstNichtMitPackungsGroesseException | IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Die Menge muss eine ganze Zahl sein.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void do_btnZeigeverlauf_actionPerformed(ActionEvent e) {
		tableDataListener.updateVerlauf();
		tableDataListener.updateToVerlauf();
		changeVerlaufBtnsVisible(false);
	}

	protected void do_btnSelectedArtikelVerlauf_actionPerformed(ActionEvent e) {
		if (tableDataListener.onSelctedRow() == null) {
			JOptionPane.showMessageDialog(null, "Bitte nur einen Artikel auswählen", "info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			artikel = tableDataListener.onSelctedRow(); 
			tableDataListener.updateVerlauf30(artikel) ;
			tableDataListener.updateTo30Verlauf();
			changeVerlaufBtnsVisible(false);
		}
	}
	protected void do_btnArtikelnAnzeigen_actionPerformed(ActionEvent e) {
		changeVerlaufBtnsVisible(true);
		tableDataListener.updateToArtikel();
		tableDataListener.updateTable();
	}
	
	
	public void changeVerlaufBtnsVisible(boolean a) {
		btnSelectedArtikelVerlauf.setVisible(a);
		btnArtikel_Erhoehen.setVisible(a);
		btnBestand_Senken.setVisible(a);
		btnLoeschen.setVisible(a);
		
	}
}
