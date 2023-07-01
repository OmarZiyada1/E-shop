package ui.gui.test;



import javax.swing.*;

import domain.E_Shop;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import domain.exceptions.MitarbeiterUsernameIstBenutztException;
import entities.Artikel;
import entities.Nutzer;
import ui.gui.panels.test.AddArtikelPanel;
import ui.gui.panels.test.ArtikelnTablePanel;
import ui.gui.panels.test.Login_Panel;
import ui.gui.panels.test.SearchArtikelsPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class BibGuiMitKomponenten extends JFrame
		implements AddArtikelPanel.AddArtikelListener,
		SearchArtikelsPanel.SearchResultListener, Login_Panel.LoginSuccessListener,Login_Panel.PanelChangeListener {

	private E_Shop shop;

	private SearchArtikelsPanel searchPanel;
	private AddArtikelPanel addPanel;
//	private BooksListPanel booksPanel;
	private ArtikelnTablePanel ArtikelnPanel;
	private JPanel loginPanel;


	public BibGuiMitKomponenten(String titel) throws ArtikelExistiertBereitsException, ArtikelExistiertNichtException, MitarbeiterUsernameIstBenutztException, ParseException, BestandPasstNichtMitPackungsGroesseException {
		super(titel);
		
		try {
			shop = new E_Shop("EShop");
			
//			// Code für Umschaltung des Look-and-Feels:
//			// (Einfach mal ausprobieren!)
//			try {
//				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
////				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
////				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//				SwingUtilities.updateComponentTreeUI(this);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (UnsupportedLookAndFeelException e) {
//				e.printStackTrace();
//			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		runProg();
		
	}
	private void runProg() {
		loginPanel = new Login_Panel(this.shop, this, this);  
        getContentPane().add(loginPanel);
        setSize(640, 480);
        setVisible(true);
    }

	private void initialize(Nutzer loggednutzer) {
		// Menü initialisieren
		setupMenu();

		// Klick auf Kreuz / roten Kreis (Fenster schließen) behandeln lassen:
		// A) Mittels Default Close Operation
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// B) Mittels WindowAdapter (für Sicherheitsabfrage)
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowCloser());

		// Layout des Frames: BorderLayout
		getContentPane().setLayout(new BorderLayout());

		// North
		searchPanel = new SearchArtikelsPanel(shop, this);

		// West
		addPanel = new AddArtikelPanel(shop, this);

		// Center
		java.util.List<Artikel> Artikeln = shop.gibAlleArtikeln();
		// (wahlweise Anzeige als Liste oder Tabelle)
//		booksPanel = new BooksListPanel(buecher);
		ArtikelnPanel = new ArtikelnTablePanel(Artikeln);
		JScrollPane scrollPane = new JScrollPane(ArtikelnPanel);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Artikeln"));

		// "Zusammenbau" in BorderLayout des Frames
		getContentPane().add(searchPanel, BorderLayout.NORTH);
		getContentPane().add(addPanel, BorderLayout.WEST);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		// Hinweis zu ContentPane oben:
		// Komponenten müssen in Swing der ContentPane hinzugefügt werden (siehe oben).
		// this.add() oder add() können aber auch direkt auf einem Container-Objekt
		// aufgerufen werden. Die Komponenten werden dann per Default der ContentPane
		// hinzugefügt.

		this.setSize(640, 480);
		this.setVisible(true);
	}


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

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn im AddBookPanel ein Buch eingefügt wurde.
	 * (Als Reaktion soll die Bücherliste aktualisiert werden.)
	 * @see bib.local.ui.gui.panels.AddBookPanel.AddBookListener#onBookAdded(bib.local.entities.Buch)
	 */
	@Override
	public void onBookAdded(Artikel artikel) {
		// Ich lade hier einfach alle Bücher neu und lasse sie anzeigen
		java.util.List<Artikel> artikeln = shop.gibAlleArtikeln();
		ArtikelnPanel.updateArtikelnList(artikeln);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn das SearchBooksPanel ein Suchergebnis bereitstellen möchte.
	 * (Als Reaktion soll die Bücherliste aktualisiert werden.)
	 * @see bib.local.ui.gui.swing.panels.SearchBooksPanel.SearchResultListener#onSearchResult(java.util.List)
	 */
	@Override
	public void onSearchResult(java.util.List<Artikel> artikeln) {
		ArtikelnPanel.updateArtikelnList(artikeln);
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
			System.out.println("Klick auf MenuItem " + e.getActionCommand());

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
    public void onLoginSuccess(Nutzer nutzer) {
	    getContentPane().removeAll();
        initialize(nutzer);
    }
	
	
	@Override
    public void onPanelChange(JPanel newPanel) {
        getContentPane().remove(loginPanel);  // Wir entfernen das alte Panel
        loginPanel = newPanel;  // Wir speichern das neue Panel
        getContentPane().add(loginPanel);  // Wir fügen das neue Panel hinzu
        validate();  // Wir aktualisieren das GUI
        repaint();
    }

}
