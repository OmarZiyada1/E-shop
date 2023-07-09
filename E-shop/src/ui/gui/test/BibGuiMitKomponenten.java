package ui.gui.test;

import javax.swing.*;


import domain.E_Shop;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import domain.exceptions.MitarbeiterUsernameIstBenutztException;
import domain.exceptions.VerlaufLeerException;
import entities.Artikel;
import entities.Kunde;
import entities.Mitarbeiter;
import entities.Nutzer;
import entities.Verlauf;
import ui.gui.models.ArtikelTableModel;
import ui.gui.models.ArtikelTableModel2Kunde;
import ui.gui.models.VerlaufTableModel;
import ui.gui.models.WarenkorbModel;
import ui.gui.panels.test.AddArtikelPanel;
import ui.gui.panels.test.ArtikelnTablePanel;
import ui.gui.panels.test.KundenMenuePanel;
import ui.gui.panels.test.KundenMenuePanel.OnWarenkorpListener;
import ui.gui.panels.test.Login_Panel;

import ui.gui.panels.test.LogoutPanel;
import ui.gui.panels.test.MitarbeiterMenuePanel;
import ui.gui.panels.test.SearchArtikelsPanel;
import ui.gui.panels.test.VerlaufPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BibGuiMitKomponenten extends JFrame
		implements AddArtikelPanel.AddArtikelListener, SearchArtikelsPanel.SearchResultListener,
		MitarbeiterMenuePanel.TableDataListener, Login_Panel.LoginSuccessListener, Login_Panel.PanelChangeListener,
		LogoutPanel.PanelChangeBeiLogout, KundenMenuePanel.OnWarenkorpListener {

	private E_Shop shop;

	private SearchArtikelsPanel searchPanel;
	private AddArtikelPanel addArtikelPanel;
	private KundenMenuePanel kundenMenuePanel;
	private LogoutPanel logoutPanel;
	private ArtikelnTablePanel artikelnTablePanel;
	private JPanel loginPanel;
	private MitarbeiterMenuePanel mitarbeiterMenuePanel;
	private WarenkorbModel warenkorbModel;

	private Nutzer loggedNutzer;
	private List<Artikel> artikeln;
	private ArtikelTableModel2Kunde artikelTableModel2Kunde;
	public ArtikelTableModel artikelTableModel;
	JScrollPane scrollPane;
	private HashMap<Artikel, Integer> warenkorb;
	private List<Verlauf> verlaufListe;
	private VerlaufPanel verlaufPanel;
	private VerlaufTableModel verTableModel;

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

		// layout
		getContentPane().setLayout(new BorderLayout());

		// Menü initialisieren
		setupMenu();

		// North
		searchPanel = new SearchArtikelsPanel(shop, this);
		getContentPane().add(searchPanel, BorderLayout.NORTH);

		// Center
		artikeln = shop.gibAlleArtikeln();

		// south
		logoutPanel = new LogoutPanel(shop, this.loggedNutzer, this);
		getContentPane().add(logoutPanel, BorderLayout.SOUTH);

		if (shop.sucheMitarbeiter(loggednutzer.getNutzerName()) != null) {
			addArtikelPanel = new AddArtikelPanel(shop, this.loggedNutzer, this);
			artikelTableModel = new ArtikelTableModel (artikeln);
			try {
				verlaufListe =shop.gibVerlauflistaus();
				verTableModel = new VerlaufTableModel (verlaufListe);
			} catch (VerlaufLeerException e) {
				System.out.println(e.getMessage());
			}
			
			// West
			getContentPane().add(addArtikelPanel, BorderLayout.WEST);
			artikelnTablePanel = new ArtikelnTablePanel(this.shop, (Mitarbeiter) loggednutzer);
			scrollPane = new JScrollPane(artikelnTablePanel);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			scrollPane.setBorder(BorderFactory.createTitledBorder("Artikeln"));

			mitarbeiterMenuePanel = new MitarbeiterMenuePanel(this, shop, loggedNutzer);
			getContentPane().add(mitarbeiterMenuePanel, BorderLayout.EAST);

		} else if (shop.sucheKunde(loggednutzer.getNutzerName()) != null) {
			Kunde k = shop.sucheKunde(loggednutzer.getNutzerName());
			warenkorbModel = new WarenkorbModel(k.getKundeWarenkorb().getKorbArtikelListe());
			artikelTableModel2Kunde = new ArtikelTableModel2Kunde(artikeln);
			kundenMenuePanel = new KundenMenuePanel(shop, (Kunde) loggednutzer, this);
			getContentPane().add(kundenMenuePanel, BorderLayout.WEST);
			artikelnTablePanel = new ArtikelnTablePanel(this.shop, (Kunde) loggednutzer);
			scrollPane = new JScrollPane(artikelnTablePanel);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			scrollPane.setBorder(BorderFactory.createTitledBorder("Artikeln"));

		}

		this.setSize(640, 480);
		this.setVisible(true);
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
		artikelnTablePanel.sortTableMouseClick(artikeln);
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
		artikelnTablePanel.setModel(artikelTableModel);
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

	@Override
	public void updateTable() {
		artikeln = shop.gibAlleArtikeln();
		artikelnTablePanel.updateArtikelnList(artikeln);
	}

	@Override
	public void updateWarenKorb() {
		Kunde k = (Kunde) loggedNutzer;
		warenkorb = k.getKundeWarenkorb().getKorbArtikelListe();
		warenkorbModel.setWarenkorb(warenkorb, k.getKundeWarenkorb());
	}
	
	@Override
	public void updateVerlauf() {
		try {
			verlaufListe = shop.gibVerlauflistaus();
		} catch (VerlaufLeerException e) {
			System.out.println(e.getMessage());
		}
		verTableModel.setVerlaeufe(verlaufListe);
	}

	@Override
	public Artikel onSelctedRow() {

		ArtikelTableModel artikeltabelModel = new ArtikelTableModel(artikeln);
		// -1 bedeuted dass keines row selected
		int selectedRow = artikelnTablePanel.selectedrowIndex();
		Artikel arikel = null;

		if (selectedRow != -1 && artikelnTablePanel.getSelectedRowCount() == 1) {
			arikel = artikeltabelModel.getSelecetedArtikel(selectedRow);
		}
		return arikel;

	}

	@Override
	public void updateToWarenkorb() {
		artikelnTablePanel.setModel(warenkorbModel);
		scrollPane.setBorder(BorderFactory.createTitledBorder("WarenKorb"));

	}

	@Override
	public void updateToArtikel() {
		artikelnTablePanel.setModel(artikelTableModel2Kunde);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Artikeln"));

	}

	@Override
	public Artikel onSelectedRow_Kunde() {

		// -1 bedeuted dass keines row selected
		int selectedRow = artikelnTablePanel.selectedrowIndex();
		Artikel arikel = null;

		if (selectedRow != -1 && artikelnTablePanel.getSelectedRowCount() == 1) {
			arikel = artikelTableModel2Kunde.getSelecetedArtikel(selectedRow);
		}
		return arikel;
	}
	
	@Override
	public Artikel onSelectedRow_Warenkorb() {

		// -1 bedeuted dass keines row selected
		int selectedRow = artikelnTablePanel.selectedrowIndex();
		Artikel arikel = null;

		if (selectedRow != -1 && artikelnTablePanel.getSelectedRowCount() == 1 && selectedRow != warenkorbModel.getRowCount()-1 ) {
			arikel = warenkorbModel.getSelecetedArtikel(selectedRow);
		}
		return arikel;
	}

	@Override
	public void updateToVerlauf() {
		artikelnTablePanel.setModel(verTableModel);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Verlauf"));
	}



}
