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
import domain.exceptions.VerlaufLeerException;
import entities.Artikel;
import entities.Mitarbeiter;
import entities.Nutzer;
import entities.Verlauf;
import ui.gui.models.VerlaufTableModel;
import ui.gui.panels.test.AddArtikelPanel.AddArtikelListener;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

public class MitarbeiterMenuePanel extends JPanel {
	private JButton btnLoeschen;
	private JButton btnBestand_Senken;
	private JButton btnArtikel_Erhoehen;
	private JButton btnZeigeverlauf;
	private TableDataListener tableDataListener;
	private E_Shop shop;
	private Mitarbeiter mitarbeiter;
	private JButton btn_zeigeArtikeln;
	private List<Verlauf> verlaufListe;

	public interface TableDataListener {
		public Artikel onSelctedRow();
		public void updateTable();
		public void updateToVerlauf(List<Verlauf> verlaufListe);
		public void updateToArtikeln();
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
		gridBagLayout.rowHeights = new int[] { 0, 21, 21, 21, 21, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		{
			this.btnLoeschen = new JButton("Artikel Löschen");
			this.btnLoeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnLoeschen_actionPerformed(e);
				}
			});
			{
				this.btn_zeigeArtikeln = new JButton("Zeige Artikeln");
				this.btn_zeigeArtikeln.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						do_btn_zeigeArtikeln_actionPerformed(e);
					}
				});
				GridBagConstraints gbc_btn_zeigeArtikeln = new GridBagConstraints();
				gbc_btn_zeigeArtikeln.insets = new Insets(0, 0, 5, 0);
				gbc_btn_zeigeArtikeln.fill = GridBagConstraints.BOTH;
				gbc_btn_zeigeArtikeln.gridx = 0;
				gbc_btn_zeigeArtikeln.gridy = 0;
				add(this.btn_zeigeArtikeln, gbc_btn_zeigeArtikeln);
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
				gbc_btnZeigeverlauf.gridy = 1;
				add(this.btnZeigeverlauf, gbc_btnZeigeverlauf);
			}
			GridBagConstraints gbc_btnLoeschen = new GridBagConstraints();
			gbc_btnLoeschen.fill = GridBagConstraints.BOTH;
			gbc_btnLoeschen.insets = new Insets(0, 0, 5, 0);
			gbc_btnLoeschen.gridx = 0;
			gbc_btnLoeschen.gridy = 2;
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
			gbc_btnBestand_Senken.gridy = 3;
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
			gbc_btnArtikel_Erhoehen.gridx = 0;
			gbc_btnArtikel_Erhoehen.gridy = 4;
			add(this.btnArtikel_Erhoehen, gbc_btnArtikel_Erhoehen);
		}
	}

	//button loeschen
	protected void do_btnLoeschen_actionPerformed(ActionEvent e) {
		if (tableDataListener.onSelctedRow() == null) {
			
			JOptionPane.showMessageDialog(null, "Bitte nur einen Artikel auswählen", "info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				
				shop.loescheArtikel(mitarbeiter, tableDataListener.onSelctedRow().getName());
				shop.schreibeArtikel();
				shop.schreibeVerlauf();
				tableDataListener.updateTable();
			} catch (ArtikelExistiertNichtException | IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	//button senken
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
	
	//button erhoehen
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
		try {
			verlaufListe =shop.gibVerlauflistaus();
		} catch (VerlaufLeerException e1) {
			System.out.println(e1.getMessage());
		}
		
		tableDataListener.updateToVerlauf(verlaufListe);
		btnLoeschen.setVisible(false);
		btnArtikel_Erhoehen.setVisible(false);
		btnBestand_Senken.setVisible(false);

		
	}
	protected void do_btn_zeigeArtikeln_actionPerformed(ActionEvent e) {
		btnLoeschen.setVisible(true);
		btnArtikel_Erhoehen.setVisible(true);
		btnBestand_Senken.setVisible(true);
		tableDataListener.updateToArtikeln();
	}
}
