package ui.gui.panels.test;

import domain.E_Shop;
import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import entities.Artikel;
import entities.Massengutartikel;
import entities.Mitarbeiter;
import entities.Nutzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

// Wichtig: Das AddBookPanel _ist ein_ Panel und damit auch eine Component; 
// es kann daher in das Layout eines anderen Containers 
// (in unserer Anwendung des Frames) eingefügt werden.
public class AddArtikelPanel extends JPanel {

	// Über dieses Interface übermittelt das AddBookPanel
	// ein neu hinzugefügtes Buch an einen Empfänger.
	// In unserem Fall ist der Empfänger die BibGuiMitKomponenten,
	// die dieses Interface implementiert und auf ein neue hinzugefügtes
	// Buch reagiert, indem sie die Bücherliste aktualisiert.
	public interface AddArtikelListener {
		public void onArikelAdded(Artikel Artikel);
	}

	private E_Shop shop = null;
	private AddArtikelListener addArtikelListener = null;
	private JButton hinzufuegenButton;
	private JTextField artikelNameTextFeld;
	private JTextField beschreibungTextFeld;
	private JTextField preisTextFeld = null;
	private JTextField bestandTextFeld = null;
	private JRadioButton massenArtikelJa_rbt = null;
	private JRadioButton massenArtikelNein_rbt = null;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField packungsgroesseTextFeld = null;
	private JLabel packungLabel = null;
	boolean istMassengut = false;
	private Nutzer loggedNutzer;

	public AddArtikelPanel(E_Shop shop, Nutzer loggedNutzer, AddArtikelListener addArtikelListener) {
		this.shop = shop;
		this.loggedNutzer = loggedNutzer;
		this.addArtikelListener = addArtikelListener;

		setupUI();

		setupEvents();
	}

	private void setupUI() {

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Abstandhalter ("Filler") zwischen Rand und erstem Element
		Dimension borderMinSize = new Dimension(5, 10);
		Dimension borderPrefSize = new Dimension(5, 10);
		Dimension borderMaxSize = new Dimension(5, 10);
		Box.Filler filler = new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize);
		add(filler);

		artikelNameTextFeld = new JTextField();
		beschreibungTextFeld = new JTextField();
		preisTextFeld = new JTextField();
		bestandTextFeld = new JTextField();
		massenArtikelJa_rbt = new JRadioButton();
		massenArtikelJa_rbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_massenArtikelJa_rbt_actionPerformed(e);
			}
		});
		massenArtikelNein_rbt = new JRadioButton();
		massenArtikelNein_rbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_massenArtikelNein_rbt_actionPerformed(e);
			}
		});
		packungsgroesseTextFeld = new JTextField();
		;

		this.massenArtikelNein_rbt.setSelected(true);

		add(new JLabel("ArtikelName:"));
		add(artikelNameTextFeld);
		add(new JLabel("Beschreibung"));
		add(beschreibungTextFeld);
		add(new JLabel("Preis:"));
		add(preisTextFeld);
		add(new JLabel("Bestand:"));
		add(bestandTextFeld);
		add(new JLabel("Geht es um einen MassengutArtikel:"));
		add(new JLabel("Ja:"));
		add(massenArtikelJa_rbt);
		add(new JLabel("nein:"));
		add(massenArtikelNein_rbt);
		buttonGroup.add(massenArtikelJa_rbt);
		buttonGroup.add(massenArtikelNein_rbt);
		packungLabel = new JLabel("Packungsgröße:");
		add(packungLabel);
		add(packungsgroesseTextFeld);
		packungLabel.setVisible(false);
		packungsgroesseTextFeld.setVisible(false);

		// Abstandhalter ("Filler") zwischen letztem Eingabefeld und Add-Button
		Dimension fillerMinSize = new Dimension(5, 20);
		Dimension fillerPrefSize = new Dimension(5, Short.MAX_VALUE);
		Dimension fillerMaxSize = new Dimension(5, Short.MAX_VALUE);
		filler = new Box.Filler(fillerMinSize, fillerPrefSize, fillerMaxSize);
		add(filler);

		hinzufuegenButton = new JButton("Hinzufuegen");
		add(hinzufuegenButton);

		// Abstandhalter ("Filler") zwischen letztem Element und Rand
		add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));

		// Rahmen definieren
		setBorder(BorderFactory.createTitledBorder("Einfügen"));
	}

	private void setupEvents() {
//		hinzufuegenButton.addActionListener(
//				new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent ae) {
//						System.out.println("Event: " + ae.getActionCommand());
//						buchEinfügen();
//					}
//				});
		hinzufuegenButton.addActionListener(e -> artikelEinfügen());
	}

	private void artikelEinfügen() {
		String name = artikelNameTextFeld.getText();
		String beschreibung = beschreibungTextFeld.getText();
		String preis = preisTextFeld.getText();
		String bestand = bestandTextFeld.getText();
		boolean isMassengut = this.istMassengut;
		String packungsgroesse = packungsgroesseTextFeld.getText();

		Massengutartikel artikel_1;
		if (istMassengut) {

			if (!name.isEmpty() && !beschreibung.isEmpty() && !preis.isEmpty() && !bestand.isEmpty()
					&& !packungsgroesse.isEmpty()) {
				int packungsgroesseInt = Integer.parseInt(packungsgroesse);
				double preisD = Double.parseDouble(preis);
				int bestandInt = Integer.parseInt(bestand);

				try {
					artikel_1 = (Massengutartikel) shop.fuegeMassenArtikelEin((Mitarbeiter) this.loggedNutzer, name,
							beschreibung, bestandInt, preisD, isMassengut, packungsgroesseInt);
					datenSischern();

					textFeldeLeeren();
					addArtikelListener.onArikelAdded(artikel_1);

				} catch (AnzahlIsNichtDefiniertException | ArtikelExistiertBereitsException
						| BestandPasstNichtMitPackungsGroesseException | ArtikelExistiertNichtException
						| IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

			} else {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (!name.isEmpty() && !beschreibung.isEmpty() && !preis.isEmpty() && !bestand.isEmpty()) {

			double preisD = Double.parseDouble(preis);
			int bestandInt = Integer.parseInt(bestand);
			Artikel artikel;
			try {
				artikel = shop.fuegeArtikelEin((Mitarbeiter) this.loggedNutzer, name, beschreibung, bestandInt, preisD,
						isMassengut);
				datenSischern();
				textFeldeLeeren();

				// Am Ende Listener, d.h. unseren Frame benachrichtigen:
				addArtikelListener.onArikelAdded(artikel);
			} catch (AnzahlIsNichtDefiniertException | ArtikelExistiertBereitsException
					| BestandPasstNichtMitPackungsGroesseException | ArtikelExistiertNichtException | IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * @param artikel_1
	 */
	private void textFeldeLeeren() {
		artikelNameTextFeld.setText("");
		beschreibungTextFeld.setText("");
		preisTextFeld.setText("");
		bestandTextFeld.setText("");
		beschreibungTextFeld.setText("");
		massenArtikelNein_rbt.setSelected(true);
		packungsgroesseTextFeld.setText("");
	}

	/**
	 * @throws IOException
	 */
	private void datenSischern() throws IOException {
		shop.schreibeArtikel();

		shop.schreibeVerlauf();

	}

	protected void do_massenArtikelJa_rbt_actionPerformed(ActionEvent e) {
		if (massenArtikelJa_rbt.isSelected()) {
			packungLabel.setVisible(true);
			packungsgroesseTextFeld.setVisible(true);
			packungsgroesseTextFeld.setText("");
			this.istMassengut = true;
		}
	}

	protected void do_massenArtikelNein_rbt_actionPerformed(ActionEvent e) {
		if (massenArtikelNein_rbt.isSelected()) {
			packungLabel.setVisible(false);
			packungsgroesseTextFeld.setVisible(false);
			this.istMassengut = false;
		}
	}
}
