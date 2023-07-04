package ui.gui.test;

import javax.swing.*;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import domain.exceptions.MitarbeiterUsernameIstBenutztException;
import entities.Artikel;
import entities.Kunde;
import entities.Nutzer;
import ui.gui.models.ArtikelTableModel;
import ui.gui.models.WarenkorbModel;
import ui.gui.panels.test.AddArtikelPanel;
import ui.gui.panels.test.ArtikelnTablePanel;
import ui.gui.panels.test.KundenMenuePanel;
import ui.gui.panels.test.Login_Panel;

import ui.gui.panels.test.LogoutPanel;
import ui.gui.panels.test.MitarbeiterMenuePanel;
import ui.gui.panels.test.SearchArtikelsPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class BibGuiMitKomponenten extends JFrame implements AddArtikelPanel.AddArtikelListener,
		SearchArtikelsPanel.SearchResultListener, MitarbeiterMenuePanel.TableDataListener,
		Login_Panel.LoginSuccessListener, Login_Panel.PanelChangeListener, LogoutPanel.PanelChangeBeiLogout {

	private E_Shop shop;

	private SearchArtikelsPanel searchPanel;
	private AddArtikelPanel addArtikelPanel;
	private KundenMenuePanel kundenMenuePanel;

	private LogoutPanel logoutPanel;
//	private BooksListPanel booksPanel;
	private ArtikelnTablePanel artikelnTablePanel;

	private JPanel loginPanel;
	private MitarbeiterMenuePanel mitarbeiterMenuePanel;
	private Nutzer loggedNutzer;
	private java.util.List<Artikel> artikeln;

	public static void main(String[] args) {
		// Start der Anwendung (per anonymer Klasse)
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BibGuiMitKomponenten gui = new BibGuiMitKomponenten("EShop");
				} catch (ArtikelExistiertBereitsException | ArtikelExistiertNichtException
						| MitarbeiterUsernameIstBenutztException | ParseException
						| BestandPasstNichtMitPackungsGroesseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

//		// Start der Anwendung (per Lambda-Expression)
//		SwingUtilities.invokeLater(() -> { BibGuiAusVL gui = new BibGuiAusVL("Bibliothek"); });
	}

	public BibGuiMitKomponenten(String titel) throws ArtikelExistiertBereitsException, ArtikelExistiertNichtException,
			MitarbeiterUsernameIstBenutztException, ParseException, BestandPasstNichtMitPackungsGroesseException {
		super(titel);

		try {
			shop = new E_Shop("EShop");

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		runProg();

	}

	private void runProg() {

		getContentPane().removeAll();
		loginPanel = new Login_Panel(this.shop, this, this);
		getContentPane().add(loginPanel);
		setSize(640, 480);
		setVisible(true);

	}

	private void initialize(Nutzer loggednutzer) {
		this.loggedNutzer = loggednutzer;
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowCloser());

		// Menü initialisieren
		setupMenu();

		// Klick auf Kreuz / roten Kreis (Fenster schließen) behandeln lassen:
		// A) Mittels Default Close Operation
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// B) Mittels WindowAdapter (für Sicherheitsabfrage)

		addArtikelPanel = new AddArtikelPanel(shop, this.loggedNutzer, this);
		kundenMenuePanel = new KundenMenuePanel();

		if (shop.sucheMitarbeiter(loggednutzer.getNutzerName()) != null) {
			k_m_design(addArtikelPanel);
			// OST
			mitarbeiterMenuePanel = new MitarbeiterMenuePanel(this, shop, loggedNutzer);
			getContentPane().add(mitarbeiterMenuePanel, BorderLayout.EAST);
		} else if (shop.sucheKunde(loggednutzer.getNutzerName()) != null) {
			k_m_design(kundenMenuePanel);
			
		}

		this.setSize(640, 480);
		this.setVisible(true);
	}

	/**
	 * 
	 */
	private void k_m_design(JPanel switch_Panel) {
		getContentPane().setLayout(new BorderLayout());
		// North
		searchPanel = new SearchArtikelsPanel(shop, this);
		// West

		getContentPane().add(switch_Panel, BorderLayout.WEST);
		// Center
		artikeln = shop.gibAlleArtikeln();

		artikelnTablePanel = new ArtikelnTablePanel(artikeln);
		JScrollPane scrollPane = new JScrollPane(artikelnTablePanel);

		// ArtikelnTablePanel warenkorbModel = new
		// ArtikelnTablePanel(shop.getKundenWarenkorb((Kunde)loggeNutzer).getKorbArtikelListe());
		// JScrollPane scrollPane = new JScrollPane(warenkorbModel);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Artikeln"));

		getContentPane().add(searchPanel, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// south
		logoutPanel = new LogoutPanel(shop, this.loggedNutzer, this);
		getContentPane().add(logoutPanel, BorderLayout.SOUTH);

		
	}

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn im AddBookPanel ein Buch
	 * eingefügt wurde. (Als Reaktion soll die Bücherliste aktualisiert werden.)
	 * 
	 * @see
	 * bib.local.ui.gui.panels.AddBookPanel.AddBookListener#onBookAdded(bib.local.
	 * entities.Buch)
	 */
	@Override
	public void onArikelAdded(Artikel artikel) {
		// Ich lade hier einfach alle Bücher neu und lasse sie anzeigen
		artikeln = shop.gibAlleArtikeln();
		artikelnTablePanel.updateArtikelnList(artikeln);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn das SearchBooksPanel ein
	 * Suchergebnis bereitstellen möchte. (Als Reaktion soll die Bücherliste
	 * aktualisiert werden.)
	 * 
	 * @see bib.local.ui.gui.swing.panels.SearchBooksPanel.SearchResultListener#
	 * onSearchResult(java.util.List)
	 */
	@Override
	public void onSearchResult(java.util.List<Artikel> artikeln) {
		artikelnTablePanel.updateArtikelnList(artikeln);
	}

	private void setupMenu() {
		// Menüleiste anlegen ...
		JMenuBar mBar = new JMenuBar();

		JMenu fileMenu = new FileMenu();
		mBar.add(fileMenu);

		JMenu helpMenu = new HelpMenu();
		mBar.add(helpMenu);

		// ... und beim Fenster anmelden
		this.setJMenuBar(mBar);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Mitgliedsklasse für File-Menü
	 *
	 */
	class FileMenu extends JMenu implements ActionListener {

		public FileMenu() {
			super("File");

			JMenuItem saveItem = new JMenuItem("Save");
			saveItem.addActionListener(this);
			add(saveItem);

			addSeparator();

			JMenuItem quitItem = new JMenuItem("Quit");
			quitItem.addActionListener(this);
			add(quitItem);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Save":
				try {
					shop.schreibeArtikel();
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
				break;
			case "Quit":
				// Nur "this" ginge nicht, weil "this" auf das FileMenu-Objekt zeigt.
				// "BibGuiAusVL.this" zeigt auf das dieses (innere) FileMenu-Objekt
				// umgebende Objekt der Klasse BibGuiAusVL.
				BibGuiMitKomponenten.this.setVisible(false);
				BibGuiMitKomponenten.this.dispose();
				System.exit(0);

			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * Mitgliedsklasse für Help-Menü
	 *
	 */
	class HelpMenu extends JMenu implements ActionListener {

		public HelpMenu() {
			super("Help");

			// Nur zu Testzwecken: Menü mit Untermenü
			JMenu m = new JMenu("About");
			JMenuItem mi = new JMenuItem("Programmers");
			mi.addActionListener(this);
			m.add(mi);
			mi = new JMenuItem("Stuff");
			mi.addActionListener(this);
			m.add(mi);
			this.add(m);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Klick auf Menü '" + e.getActionCommand() + "'.");
		}
	}

	@Override
	public void onPanelChangeBeiLogout() {
		runProg();
	}

	@Override
	public void onLoginSuccess(Nutzer nutzer) {
		getContentPane().removeAll();

		if (shop.sucheMitarbeiter(nutzer.getNutzerName()) != null) {
			initialize(nutzer);
		} else if (shop.sucheKunde(nutzer.getNutzerName()) != null) {
			initialize(nutzer);
		}
	}

	@Override
	public void onPanelChange(JPanel newPanel) {
		getContentPane().remove(loginPanel); // Wir entfernen das alte Panel
		loginPanel = newPanel; // Wir speichern das neue Panel
		getContentPane().add(loginPanel); // Wir fügen das neue Panel hinzu
		validate(); // Wir aktualisieren das GUI
		repaint();
	}

	public void updateTable() {
		artikeln = shop.gibAlleArtikeln();
		artikelnTablePanel.updateArtikelnList(artikeln);
	}

	@Override
	public Artikel onSelctedRow() {
		ArtikelTableModel artikeltabelModel = new ArtikelTableModel(artikeln);
		// -1 bedeuted dass keines row selected
		int selectedRow = -1;
		Artikel arikel = null;
		if (artikelnTablePanel.selectedrowIndex() != -1 && artikelnTablePanel.selectedrowIndex() == 1) {
			selectedRow = artikelnTablePanel.selectedrowIndex();
			arikel = artikeltabelModel.getSelecetedArtikel(selectedRow);
		} 

		return arikel;

	}

}
