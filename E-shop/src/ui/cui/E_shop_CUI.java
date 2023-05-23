package ui.cui;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import domain.E_Shop;
import domain.exceptions.AnzahlIsNichtDefiniertException;

import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.KundeIDistbenutztException;
import domain.exceptions.MitarbeiterIDIstBenutztException;
import domain.exceptions.NichtGenugArtikelVorhandenException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import domain.exceptions.VerlaufLeerException;
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
		// System.out.print(" \n Daten sichern: 's'");
		System.out.print("         \n  Mitarbeiter regestrieren:  'm'");
		System.out.print("         \n  Artikelbestand erhöhen:  'h'");
		System.out.print("         \n  Artikelbestand senken:  'w'");
		System.out.print("         \n  Zeige Verlauf:  'v'");
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
		System.out.print("         \n  Zeige Verlauf:  'v'");
		// System.out.print(" \n Daten sichern: 's'");
		System.out.print("           \n  ---------------------");
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

		switch (line.toLowerCase()) {
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
				System.out.println("\nSie Haben Sich erfolgreich regestriert. Sie Können Sich jetzt anmelden\n");
			} catch (KundeIDistbenutztException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
			break;
		case "l":
			System.out.print("NutzerName  > ");
			loginNutzerName = liesEingabe();
			System.out.print("Passwort   > ");
			loginPasswort = liesEingabe();
			try {
				loggedMitarbeiter = sh.mitarbeiterEinloggen(loginNutzerName, loginPasswort);
				System.out.println("\nSie Sind Erfolgreich angemeldet\n");
			} catch (NutzernameOderPasswortFalschException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
			break;
		case "k":
			System.out.print("NutzerName  > ");
			loginNutzerName = liesEingabe();
			System.out.print("Passwort   > ");
			loginPasswort = liesEingabe();
			try {
				loggedkunde = sh.kundenEinloggen(loginNutzerName, loginPasswort);
				System.out.println("\nSie Sind Erfolgreich angemeldet\n");
			} catch (NutzernameOderPasswortFalschException e) {
				System.err.println(e.getMessage() + "\n");
			}
			break;

		}
	}

	private void verarbeiteEingabe(String line)
			throws IOException, ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException, VerlaufLeerException {
		boolean verfügbarkeit;
		int artikelID;
		String artikelName;
		String beschreibung;
		int bestand;
		double preis;
		List<Artikel> artikelListe;
		String mitarbeiterName;
		String mitarbeiterNameVorname;
		String mitarbeiterNutzername;
		String mitarbeiterPasswort;
		Artikel gesuchteArtikel = null;
		int anzahl;

		// Eingabe bearbeiten:
		switch (line.toLowerCase()) {
		case "a":
			artikelListe = sh.gibAlleArtikeln();
			sh.gibArtikelnlisteAus(artikelListe);
			break;
		case "d":
			// lies die notwendigen Parameter, einzeln pro Zeile

			System.out.print("Artikel ID  > ");
			artikelID = Integer.parseInt(liesEingabe());
			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			System.out.print("Artikel Beschreibung  > ");
			beschreibung = liesEingabe();
			System.out.print("Artikel Bestand  > ");
			bestand = Integer.parseInt(liesEingabe());
			System.out.print("Artikel Preis  > ");
			preis = Double.parseDouble(liesEingabe());
			System.out.print("Verfügbarkeit  > ");
			verfügbarkeit = Boolean.parseBoolean(liesEingabe());

			try {
				gesuchteArtikel = sh.loescheArtikel(artikelID, artikelName, beschreibung, bestand, preis,
						verfügbarkeit);
				System.out.println("\nLöschen ok\n");
				sh.addVerlauf("Mitarbeiter (Artikellöschen): ", loggedMitarbeiter, gesuchteArtikel);
			} catch (ArtikelExistiertNichtException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}

			break;
		case "e":
			// lies die notwendigen Parameter, einzeln pro Zeile
			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			System.out.print("Artikel Beschreibung > ");
			beschreibung = liesEingabe();
			System.out.print("Artikel Preis  > ");
			preis = Double.parseDouble(liesEingabe());
			System.out.print("Bestand  > ");
			bestand = Integer.parseInt(liesEingabe());

			try {
				gesuchteArtikel = sh.fuegeArtikelEin(artikelName, beschreibung, bestand, preis, bestand);
				System.out.println("\nEinfügen ok\n");
				sh.addVerlauf("Mitarbeiter (Artikel eingefügt): ", loggedMitarbeiter, gesuchteArtikel);
			} catch (AnzahlIsNichtDefiniertException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}

			break;
		case "f":
			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			try {
				gesuchteArtikel = sh.sucheNachName(artikelName);
			} catch (ArtikelExistiertNichtException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
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
				System.out.println("\nNeue Mitarbeiter regestrierung ok\n");
			} catch (MitarbeiterIDIstBenutztException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
			break;
		case "h":
			System.out.println("Artikel name >");
			artikelName = liesEingabe();
			System.out.println("Um wiel viel erhöhen?  >");
			anzahl = Integer.parseInt(liesEingabe());
			try {
				gesuchteArtikel = sh.erhoeheArtikelBestand(artikelName, anzahl);
				System.out.println("\nBestand erhöht.\n");
				sh.addVerlauf("Mitarbeiter (Artikel bestand erhöht): ", loggedMitarbeiter, gesuchteArtikel);
			} catch (ArtikelExistiertNichtException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}

			break;
		case "w":
			System.out.println("Artikel name >");
			artikelName = liesEingabe();
			System.out.println("Um wiel viel senken?  >");
			anzahl = Integer.parseInt(liesEingabe());
			try {
				gesuchteArtikel = sh.senkenArtikelBestand(artikelName, anzahl);
				System.out.println("\nBestand gesenkt.\n");
				sh.addVerlauf("kund (Bestellung): ", loggedMitarbeiter, gesuchteArtikel);
			} catch (ArtikelExistiertNichtException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}

			break;

		case "v":
			try {
				System.out.println(sh.gibVerlauflistaus());
			} catch (VerlaufLeerException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
		}
	}

	private void verarbeiteKundenEingabe(String line) throws IOException, NichtGenugArtikelVorhandenException,
			WarenkorbLeerException, VerlaufLeerException, ArtikelExistiertNichtException {
		List<Artikel> artikelListe;
		String artikelName;
		Artikel gesuchteArtikel = null;
		int anzahl;
		// Eingabe bearbeiten:
		switch (line.toLowerCase()) {
		case "a":
			artikelListe = sh.gibAlleArtikeln();
			sh.gibArtikelnlisteAus(artikelListe);
			break;

		case "f":
			System.out.print("Artikel Name  > ");
			artikelName = liesEingabe();
			try {
				gesuchteArtikel = sh.sucheNachName(artikelName);
			} catch (ArtikelExistiertNichtException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
			break;
		case "d":
			System.out.println("Bitte name des Artikels eingeben  >");
			artikelName = liesEingabe();
			try {
				gesuchteArtikel = sh.sucheNachName(artikelName);
			} catch (ArtikelExistiertNichtException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
			System.out.println("Stückanzahl  >");
			anzahl = Integer.parseInt(liesEingabe());
			sh.fuegeArtikelInkorbEin(loggedkunde, gesuchteArtikel, anzahl);
			System.out.println("\nArtikel wurde erfolgreich im Warenkorb hinzugefügt\n");
			break;
		case "c":
			System.out.println("Bitte name des Artikels eingeben  >");
			artikelName = liesEingabe();
			try {
				gesuchteArtikel = sh.sucheNachName(artikelName);
			} catch (ArtikelExistiertNichtException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}
			String line2 = "";
			System.out.print(
					"Wenn Sie Anzahl erhöhen möchten bitte '+' Eingeben. Sollten Sie den Anzahl senken wollen '-' eingeben \n ");
			line2 = liesEingabe();
			switch (line2) {
			case "+":
				System.out.print("Stückzahl eingeben bitte  >");
				anzahl = Integer.parseInt(liesEingabe());
				try {
					sh.fuegeArtikelInkorbEin(loggedkunde, gesuchteArtikel, anzahl);
					System.out.println("\nBestand des Artikel '" + gesuchteArtikel + "' wurde um '" + anzahl
							+ "' Stückzahl erhöht\n");
				} catch (NichtGenugArtikelVorhandenException e) {
					System.err.println("\n" + e.getMessage() + "\n");
				}

				break;
			case "-":
				System.out.print("Stückzahl eingeben bitte  >");
				anzahl = Integer.parseInt(liesEingabe());
				try {
					sh.fuegeArtikelInkorbEin(loggedkunde, gesuchteArtikel, -anzahl);
					System.out.println("\nBestand des Artikel '" + gesuchteArtikel + "' wurde um '" + anzahl
							+ "' Stückzahl gesenkt\n");
				} catch (NichtGenugArtikelVorhandenException e) {

					System.err.println("\n" + e.getMessage() + "\n");
				}
				break;
			}
			break;
		case "r":
			sh.leereWarenkorb(loggedkunde);
			System.out.println("\nIhre Warenkorb ist jetzt leer\n");
			sh.getKundenWarenkorb(loggedkunde);
			break;

		case "w":
			System.out.println("\n" + sh.getKundenWarenkorb(loggedkunde) + "\n");
			break;

		case "m":
			try {
				aktuelleBestellung = sh.bestellen(loggedkunde);
				System.out.println("\n" + sh.erstelleRechnung(aktuelleBestellung) + "\n");
				sh.leereWarenkorb(loggedkunde);
				for (Artikel artikel : aktuelleBestellung.getBestellteArtikeln().keySet()) {
					sh.addVerlauf("Mitarbeiter (Artikellöschen): ", loggedkunde, artikel);
				}
			} catch (WarenkorbLeerException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}

			break;
		case "v":
			try {
				System.out.println(sh.gibVerlauflistaus());
			} catch (VerlaufLeerException e) {
				System.err.println("\n" + e.getMessage() + "\n");
			}

		}

	}

	public void run() throws ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException,
			MitarbeiterIDIstBenutztException, KundeIDistbenutztException, NichtGenugArtikelVorhandenException,
			WarenkorbLeerException, VerlaufLeerException {
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
				// e.printStackTrace();
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
					// e.printStackTrace();
				}
			} while (!input.equals("q"));

		} else if (loggedkunde != null) {
			do {
				gibKundeMenueAus();
				try {
					input = liesEingabe();
					verarbeiteKundenEingabe(input);
				} catch (IOException e) {
					// e.printStackTrace();
				}

			} while (!input.equals("q"));
		}

	}

	public static void main(String[] args) throws IOException, MitarbeiterIDIstBenutztException,
			ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException, KundeIDistbenutztException,
			NichtGenugArtikelVorhandenException, WarenkorbLeerException, VerlaufLeerException {
		E_shop_CUI cui;
		try {
			cui = new E_shop_CUI();
			cui.run();
		} catch (IOException e) {
			// e.printStackTrace();
		}

	}

}
