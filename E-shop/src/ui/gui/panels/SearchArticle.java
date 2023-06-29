package ui.gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import domain.E_Shop;
import domain.exceptions.ArtikelExistiertNichtException;
import entities.Artikel;



public class SearchArticle  extends JPanel{

	
	public interface SearchResultListener {
		void onSearchResult(Artikel artikel);
		void onSearchResult(List <Artikel> artikel);
	}
	
	private E_Shop sh= null;
	private JTextField searchTextField;
	private JButton searchButton = null;
	private SearchResultListener searchResultListener;
	
	public SearchArticle(E_Shop shop, SearchResultListener searchResultListener) {
		sh = shop;
		this.searchResultListener = searchResultListener;

		//setupUI();
		
		//setupEvents();
	}
	
	
	
	
	
	private void setupUI() {
		// GridBagLayout
		// (Hinweis: Das ist schon ein komplexerer LayoutManager, der mehr kann als hier gezeigt.
		//  Hervorzuheben ist hier die Idee, explizit Constraints (also Nebenbedindungen) für 
		//  die Positionierung / Ausrichtung / Größe von GUI-Komponenten anzugeben.)
		GridBagLayout gridBagLayout = new GridBagLayout(); 
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;	// Zeile 0

		JLabel searchLabel = new JLabel("Suchbegriff:");
		c.gridx = 0;	// Spalte 0
		c.weightx = 0.2;	// 20% der gesamten Breite
		c.anchor = GridBagConstraints.EAST;
		gridBagLayout.setConstraints(searchLabel, c);
		this.add(searchLabel);
		
		searchTextField = new JTextField();
		searchTextField.setToolTipText("Hier Suchbegriff eingeben.");
		c.gridx = 1;	// Spalte 1
		c.weightx = 0.6;	// 60% der gesamten Breite
		gridBagLayout.setConstraints(searchTextField, c);
		this.add(searchTextField);
		
		searchButton = new JButton("Such!");
		c.gridx = 2;	// Spalte 2
		c.weightx = 0.2;	// 20% der gesamten Breite
		gridBagLayout.setConstraints(searchButton, c);
		this.add(searchButton);
		
		// Rahmen definieren
		setBorder(BorderFactory.createTitledBorder("Suche"));
	}
	
	private void setupEvents() {
		searchButton.addActionListener(new SuchListener());
	}
	
	class SuchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource().equals(searchButton)) {
				String suchbegriff = searchTextField.getText();
				List <Artikel> suchErgebnis;
				Artikel gesuchteArtikel;
				if (suchbegriff.isEmpty()) {
					suchErgebnis = sh.gibAlleArtikeln();
					searchResultListener.onSearchResult(suchErgebnis);
				} else {
					try {
						gesuchteArtikel = sh.sucheNachName(suchbegriff);
						searchResultListener.onSearchResult(gesuchteArtikel);
					} catch (ArtikelExistiertNichtException e) {
						e.getMessage();
					}
				}
				
			}
		}
	}
}
