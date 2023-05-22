package ui.cui;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import domain.E_Shop;
import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.KundeIDistbenutztException;
import domain.exceptions.MitarbeiterIDIstBenutztException;
import entities.Artikel;


public class E_shop_CUI {
	private E_Shop sh;
	private BufferedReader in;

	public E_shop_CUI() throws IOException {
	
		sh = new E_Shop();
		// Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	private String liesEingabe() throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}
	
	private void gibStartMenuAus() {
		System.out.println("\"Befehle: \\n  Login:  'l'\"");
		System.out.print("         \n  Regestrieren als Kunde: 'r'");
		System.out.print("> "); 
		System.out.flush();
	}

	private void gibMenueAus() {
		System.out.print("Befehle: \n  Artikel ausgeben:  'a'");
		System.out.print("         \n  Artikel lˆschen: 'd'");
		System.out.print("         \n  Artikel einf¸gen: 'e'");
		System.out.print("         \n  Artikel suchen:  'f'");
		System.out.print("         \n  Daten sichern:  's'");
		System.out.print("         \n  ---------------------");
		System.out.println("         \n  Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush();
	}
	
	
	private void verarbeiteLogin(String line) throws KundeIDistbenutztException {
		switch (line) {
		case "r":
			//hier
			//sh.kundenRegistrieren();
			
		}	
	}

	private void verarbeiteEingabe(String line) throws IOException, ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException {

		int artikelId;
		String name;
		String beschreibung;
		int bestand;
		double preis;
		List<Artikel> artikelListe;
		
		// Eingabe bearbeiten:
		switch (line) {
		case "a":
			artikelListe = sh.gibAlleArtikeln();
			gibArtikelnlisteAus(artikelListe);
			break;
		case "d":
			// lies die notwendigen Parameter, einzeln pro Zeile
			System.out.print("Artikel Nummer > ");
			artikelId = Integer.parseInt(liesEingabe());
			System.out.print("Artikel Name  > ");
			name = liesEingabe();
			System.out.print("Artikel Beschreibung  > ");
			beschreibung = liesEingabe();
			System.out.print("Artikel Bestand  > ");
			bestand = Integer.parseInt(liesEingabe());
			System.out.print("Artikel Preis  > ");
			preis = Double.parseDouble(liesEingabe());

			sh.loescheArtikel(name, beschreibung, bestand, preis);

			break;
		case "e":
			// lies die notwendigen Parameter, einzeln pro Zeile
			System.out.print("Artikel Nummer > ");
			artikelId = Integer.parseInt(liesEingabe());
			System.out.print("Artikel Name  > ");
			name = liesEingabe();
			System.out.print("Artikel Beschreibung  > ");
			beschreibung = liesEingabe();
			System.out.print("Artikel Bestand  > ");
			bestand = Integer.parseInt(liesEingabe());
			System.out.print("Artikel Preis  > ");
			preis = Double.parseDouble(liesEingabe());

			sh.fuegeArtikelEin( name, beschreibung, bestand, preis);
			System.out.println("Einf¸gen ok");
			break;
		case "f":
			System.out.print("Artikel Name  > ");
			name = liesEingabe();
			artikelListe = sh.sucheNachName(name);
			gibArtikelnlisteAus(artikelListe);
			
		}
	}

	private void gibArtikelnlisteAus(List<Artikel> artikelListe) {
		if (artikelListe.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			for (Artikel artikel : artikelListe) {
				System.out.println(artikel);
			}
		}
	}
	
	public void run() throws ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException {
		// Variable f√ºr Eingaben von der Konsole
		String input = "";

		// Hauptschleife der Benutzungsschnittstelle
		do {
			gibMenueAus();
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!input.equals("q"));
	}

	
	public static void main(String[] args) throws IOException, MitarbeiterIDIstBenutztException, ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException {
		E_shop_CUI cui;
		try {
			cui = new E_shop_CUI();
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
