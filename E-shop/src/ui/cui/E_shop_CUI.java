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
import domain.exceptions.NichtGenugArtikelVorhandenException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import domain.exceptions.WarenkorbLeerException;
import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;
import entities.Mitarbeiter;

public class E_shop_CUI {
	private E_Shop sh;
	private BufferedReader in;
	private Kunde loggedkunde;
	private Mitarbeiter loggedMitarbeiter;
	private Bestellung aktuelleBestellung;

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
		System.out.print("Befehle:\nLogin als Mitarbeiter:  'l'");
		System.out.print("\nLogin als Kunde:  'k'");
		System.out.print("\nRegestrieren als Kunde: 'r'");
		System.out.print("\nQuit: 'q'");
		System.out.print("\n> ");// Prompt
		System.out.flush();
	}

	private void gibMitarbeiterMenueAus() {
		System.out.print("Befehle: \n  Artikel ausgeben:  'a'");
		System.out.print("         \n  Artikel löschen: 'd'");
		System.out.print("         \n  Artikel einfügen: 'e'");
		System.out.print("         \n  Artikel suchen:  'f'");
		System.out.print("         \n  Daten sichern:  's'");
		System.out.print("         \n  Mitarbeiter regestrieren:  'm'");
		System.out.print("         \n  Artikelbestand erhöhen:  'h'");
		System.out.print("         \n  Artikelbestand senken:  'w'");
		System.out.print("         \n  ---------------------");
		System.out.println("         \n  Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush();
	}

	private void gibKundeMenueAus() {
		System.out.print("Befehle: \n  Artikel ausgeben:  'a'");
		System.out.print("         \n  Artikel suchen:  'f'");
		System.out.print("         \n  Artikel in Warenkorb anlegen: 'd'");
		System.out.print("         \n  Artikel Stückzahl ändern: 'c'");
		System.out.print("         \n  Warenkorb anzeigen: 'w'");

		System.out.print("         \n  Warenkorb leeren: 'r'");
		System.out.print("         \n  Bestellen:  'm'");
		// System.out.print(" \n Daten sichern: 's'");
		System.out.print("         \n  ---------------------");
		System.out.println("         \n  Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush();
	}

	private void verarbeiteLogin(String line) throws IOException {
		String name;
		String vorname;
		String nutzerName;
		String passwort;
		String strasse;
		String hNr;
		String plz;
		String ort;
		String land;
		String loginNutzerName;
		String loginPasswort;

		switch (line) {
		case "r":
			System.out.print("Name eingeben> ");
			name = liesEingabe();
			System.out.print("Vorname  > ");
			vorname = liesEingabe();
			System.out.print("NutzerName  > ");
			nutzerName = liesEingabe();
			System.out.print("Passwort   > ");
			passwort = liesEingabe();
			System.out.print("Straße  > ");
			strasse = liesEingabe();
			System.out.print("HausNr.   > ");
			hNr = liesEingabe();
			System.out.print("PLZ.  > ");
			plz = liesEingabe();
			System.out.print("Ort   > ");
			ort = liesEingabe();
			System.out.print("Land  > ");
			land = liesEingabe();
			try {
				sh.kundenRegistrieren(name, vorname, nutzerName, passwort, strasse, hNr, plz, ort, land);
				System.out.println("Sie Haben Sich erfolgreich regestriert. Sie Können Sich jetzt anmelden\n");
			} catch (KundeIDistbenutztException e1) {
				e1.printStackTrace();
			}
			break;
		case "l":
			System.out.print("NutzerName  > ");
			loginNutzerName = liesEingabe();
			System.out.print("Passwort   > ");
			loginPasswort = liesEingabe();
			try {
				loggedMitarbeiter = sh.mitarbeiterEinloggen(loginNutzerName, loginPasswort);
			} catch (NutzernameOderPasswortFalschException e) {
				e.printStackTrace();
			}
			break;
		case "k":
			System.out.print("NutzerName  > ");
			loginNutzerName = liesEingabe();
			System.out.print("Passwort   > ");
			loginPasswort = liesEingabe();
			try {
				loggedkunde = sh.kundenEinloggen(loginNutzerName, loginPasswort);
				System.out.println("Sie Sind Erfolgreich angemeldet\n");
			} catch (NutzernameOderPasswortFalschException e) {
				e.printStackTrace();
			}
			break;

		}
	}

	private void verarbeiteEingabe(String line)
			throws IOException, ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException {

		String artikelName;
		String beschreibung;
		int bestand;
		double preis;
		List<Artikel> artikelListe;
		String mitarbeiterName;
		String mitarbeiterNameVorname;
		String mitarbeiterNutzername;
		String mitarbeiterPasswort;
		Artikel gesuchteArtikel;
		int anzahl;

		// Eingabe bearbeiten:
		switch (line) {
		case "a":
			artikelListe = sh.gibAlleArtikeln();
			gibArtikelnlisteAus(artikelListe);
			break;
		case "d":
			// lies die notwendigen Parameter, einzeln pro Zeile

			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			System.out.print("Artikel Beschreibung  > ");
			beschreibung = liesEingabe();
			System.out.print("Artikel Bestand  > ");
			bestand = Integer.parseInt(liesEingabe());
			System.out.print("Artikel Preis  > ");
			preis = Double.parseDouble(liesEingabe());

			sh.loescheArtikel(artikelName, beschreibung, bestand, preis);

			break;
		case "e":
			// lies die notwendigen Parameter, einzeln pro Zeile
			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			System.out.print("Artikel Beschreibung  > ");
			beschreibung = liesEingabe();
			System.out.print("Artikel Preis  > ");
			preis = Double.parseDouble(liesEingabe());
			System.out.print("Bestand  > ");
			bestand = Integer.parseInt(liesEingabe());

			sh.fuegeArtikelEin(artikelName, beschreibung, bestand, preis, bestand);
			System.out.println("Einfügen ok");
			break;
		case "f":
			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			gesuchteArtikel = sh.sucheNachName(artikelName);
			System.out.println(gesuchteArtikel);
			break;
		case "m":
			System.out.print("Name eingeben> ");
			mitarbeiterName = liesEingabe();
			System.out.print("Vorname  > ");
			mitarbeiterNameVorname = liesEingabe();
			System.out.print("NutzerName  > ");
			mitarbeiterNutzername = liesEingabe();
			System.out.print("Passwort   > ");
			mitarbeiterPasswort = liesEingabe();
			try {
				sh.regestiereNeueMitarbeiter(mitarbeiterName, mitarbeiterNameVorname, mitarbeiterNutzername,
						mitarbeiterPasswort);
			} catch (MitarbeiterIDIstBenutztException e) {
				e.printStackTrace();
			}
			break;
		case "h":
			System.out.println("Artikel name >");
			artikelName = liesEingabe();
			System.out.println("Um wiel viel erhöhen?  >");
			anzahl = Integer.parseInt(liesEingabe());
			sh.erhoeheArtikelBestand(artikelName, anzahl);
			break;
		case "w":
			System.out.println("Artikel name >");
			artikelName = liesEingabe();
			System.out.println("Um wiel viel senken?  >");
			anzahl = Integer.parseInt(liesEingabe());
			sh.senkenArtikelBestand(artikelName, anzahl);
			break;
		}
	}

	private void verarbeiteKundenEingabe(String line) throws IOException, NichtGenugArtikelVorhandenException,  WarenkorbLeerException {
		List<Artikel> artikelListe;
		String artikelName;
		Artikel gesuchteArtikel;
		int anzahl;
		// Eingabe bearbeiten:
		switch (line) {
		case "a":
			artikelListe = sh.gibAlleArtikeln();
			gibArtikelnlisteAus(artikelListe);
			break;

		case "f":
			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			gesuchteArtikel = sh.sucheNachName(artikelName);
			System.out.println(gesuchteArtikel+ "\n");
			break;
		case "d":
			System.out.println("Bitte name des Artikels eingeben  >");
			artikelName = liesEingabe();
			gesuchteArtikel = sh.sucheNachName(artikelName);
			System.out.println("Stückanzahl  >");
			anzahl = Integer.parseInt(liesEingabe());
			sh.fuegeArtikelInkorbEin(loggedkunde, gesuchteArtikel, anzahl);
			System.out.println("Artikel wurde erfolgreich hinzugefügt\n");
			break;
		case "c":
			System.out.println("Bitte name des Artikels eingeben  >");
			artikelName = liesEingabe();
			gesuchteArtikel = sh.sucheNachName(artikelName);
			String line2 ="";
			System.out.print(
					"Wenn Sie Anzahl erhöhen möchten bitte '+' Eingeben. Sollten Sie den Anzahl senken wollen '-' eingeben \n ");
					line2=liesEingabe();
			switch(line2) {
			case "+":
				System.out.print("Stückzahl eingeben bitte  >");
				anzahl =Integer.parseInt(liesEingabe());
				try {
					sh.fuegeArtikelInkorbEin(loggedkunde, gesuchteArtikel, anzahl);
					System.out.println("Bestand des Artikel '"+gesuchteArtikel+"' wurde um '"+anzahl+"' Stückzahl erhöht");
				} catch (NichtGenugArtikelVorhandenException e) {
					e.printStackTrace();
				}
				
				break;
			case "-":
				System.out.print("Stückzahl eingeben bitte  >");
				anzahl =Integer.parseInt(liesEingabe());
				try {
					sh.fuegeArtikelInkorbEin(loggedkunde, gesuchteArtikel, -anzahl);
					System.out.println("Bestand des Artikel '"+gesuchteArtikel+"' wurde um '"+anzahl+"' Stückzahl gesenkt");
				} catch (NichtGenugArtikelVorhandenException e) {
					
					e.printStackTrace();
				}
				break;
			}
			break;
		case "r":
			sh.leereWarenkorb(loggedkunde);
			System.out.println("Ihre Warenkorb ist jetzt leer\n");
			sh.getKundenWarenkorb(loggedkunde);
			break;
			
		case "w":
			System.out.println("\n"+ sh.getKundenWarenkorb(loggedkunde)+"\n");
			break;
			
		case "m":
			aktuelleBestellung = sh.bestellen(loggedkunde);
			System.out.println("\n"+sh.erstelleRechnung(aktuelleBestellung)+"\n");
			sh.leereWarenkorb(loggedkunde);
			break;

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

	public void run() throws ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException,
			MitarbeiterIDIstBenutztException, KundeIDistbenutztException, NichtGenugArtikelVorhandenException, WarenkorbLeerException {
		// Variable fÃ¼r Eingaben von der Konsole
		sh.mitarbeiterEinfügen("Omar", "Ziyada", "oz", "123456");
		
		sh.kundenRegistrieren("Sudki", "Koulak", "sk", "12345", "Wartburg", "86", "28201", "Bremen", "DE");
		sh.fuegeArtikelEin("Hose", "Jeans SlimFit", 5, 30.0);
		sh.fuegeArtikelEin("Hemd", "kurze hemdärmel", 2, 35.0);
		sh.fuegeArtikelEin("Shirt", "wolle", 10, 10);
		
		String input = "";
		do {

			gibStartMenuAus();
			try {
				input = liesEingabe();
				verarbeiteLogin(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (loggedkunde == null && loggedMitarbeiter == null && !input.equals("q"));
		// Hauptschleife der Benutzungsschnittstelle

		if (loggedMitarbeiter != null) {
			do {
				gibMitarbeiterMenueAus();
				try {
					input = liesEingabe();
					verarbeiteEingabe(input);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (!input.equals("q"));

		} else if (loggedkunde != null) {
			do {
				gibKundeMenueAus();
				try {
					input = liesEingabe();
					verarbeiteKundenEingabe(input);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} while (!input.equals("q"));
		}

	}

	public static void main(String[] args) throws IOException, MitarbeiterIDIstBenutztException,
			ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException, KundeIDistbenutztException, NichtGenugArtikelVorhandenException, WarenkorbLeerException {
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
