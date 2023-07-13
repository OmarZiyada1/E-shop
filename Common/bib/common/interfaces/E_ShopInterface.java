package bib.common.interfaces;

import java.io.IOException;


import java.util.List;
import java.util.Vector;

import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.BestandPasstNichtMitPackungsGroesseException;
import domain.exceptions.KundeUsernameIstbenutztException;
import domain.exceptions.MitarbeiterUsernameIstBenutztException;
import domain.exceptions.NichtGenugArtikelVorhandenException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import domain.exceptions.SenkenUnterNullNichtMoeglichException;
import domain.exceptions.VerlaufLeerException;
import domain.exceptions.WarenkorbLeerException;
import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;
import entities.Massengutartikel;
import entities.Mitarbeiter;
import entities.Rechnung;
import entities.Verlauf;
import entities.Warenkorb;

public interface E_ShopInterface {

	/**
	 * Methode, die eine Liste aller im Bestand befindlichen Bücher zurückgibt.
	 * 
	 * @return Liste aller Bücher im Bestand der Bibliothek
	 */
	// public abstract List<Artikel> gibAlleBuecher();

	/**
	 * Methode zum Suchen von Büchern anhand des Titels. Es wird eine Liste von
	 * Büchern zurückgegeben, die alle Bücher mit exakt übereinstimmendem Titel
	 * enthält.
	 * 
	 * @param titel Titel des gesuchten Buchs
	 * @return Liste der gefundenen Bücher (evtl. leer)
	 */
	// public abstract List<Buch> sucheNachTitel(String titel);

	/**
	 * Methode zum Einfügen eines neuen Buchs in den Bestand. Wenn das Buch bereits
	 * im Bestand ist, wird der Bestand nicht geändert.
	 * 
	 * @param titel  Titel des Buchs
	 * @param nummer Nummer des Buchs
	 * @returns Buch-Objekt, das im Erfolgsfall eingefügt wurde
	 * @throws BuchExistiertBereitsException wenn das Buch bereits existiert
	 */
	// public abstract Buch fuegeBuchEin(String titel, int nummer) throws
	// BuchExistiertBereitsException;

	/**
	 * Methode zum Löschen eines Buchs aus dem Bestand. Es wird nur das erste
	 * Vorkommen des Buchs gelöscht.
	 * 
	 * @param titel  Titel des Buchs
	 * @param nummer Nummer des Buchs
	 */
	// public void loescheBuch(String titel, int nummer);

	/**
	 * Methode zum Speichern des Buchbestands in einer Datei.
	 * 
	 * @throws IOException
	 */
	// public abstract void schreibeBuecher() throws IOException;

	/**
	 * Methode zum Beenden einer Verbindung zum Server.
	 * 
	 * @throws IOException
	 */
	// public abstract void disconnect() throws IOException;

	// Artikel Methoden
	public Vector<Artikel> gibAlleArtikeln();

	public Artikel sucheNachName(String name) throws ArtikelExistiertNichtException;

	public Artikel fuegeArtikelEin(Mitarbeiter mitarbeiter, String name, String beschreibung, int bestand, double preis,
			boolean istPackung) throws AnzahlIsNichtDefiniertException, ArtikelExistiertBereitsException,
			BestandPasstNichtMitPackungsGroesseException, ArtikelExistiertNichtException;

	public Artikel fuegeMassenArtikelEin(Mitarbeiter mitarbeiter, String name, String beschreibung, int bestand,
			double preis, boolean istPackung, int packungsGroesse)
			throws AnzahlIsNichtDefiniertException, ArtikelExistiertBereitsException,
			BestandPasstNichtMitPackungsGroesseException, ArtikelExistiertNichtException;

	public Artikel loescheArtikel(Mitarbeiter mitarbeiter, String name) throws ArtikelExistiertNichtException;

	public Artikel erhoeheArtikelBestand(Mitarbeiter mitarbeiter, String name, int anzahl)
			throws ArtikelExistiertNichtException, BestandPasstNichtMitPackungsGroesseException;

	public Artikel senkenArtikelBestand(Mitarbeiter mitarbeiter, String name, int anzahl)
			throws ArtikelExistiertNichtException, BestandPasstNichtMitPackungsGroesseException,
			SenkenUnterNullNichtMoeglichException;

	public void gibArtikelnlisteAus(List<Artikel> artikelListe);

	public boolean checkMassengutatikel(Artikel artikel) throws ArtikelExistiertNichtException;

	public Massengutartikel artikelZuMassengeutartikel(Artikel artikel) throws ArtikelExistiertNichtException;

	public void schreibeArtikel() throws IOException;

	// Kunde Methoden
	public Kunde sucheKunde(String nutzerName);

	public void kundenRegistrieren(String name, String vorname, String nutzerNr, String passwort, String strasse,
			String hNr, String plz, String ort, String land) throws KundeUsernameIstbenutztException;

	public Kunde kundenEinloggen(String nutzerName, String passwort) throws NutzernameOderPasswortFalschException;

	public List<Bestellung> GibAlleMeineBestellungen(Kunde kunde);

	public void loggeKundeAus(Kunde kunde);

	public List<Kunde> gibAlleKunden();

	public void schreibeKunde() throws IOException;

	// Mitarbeiter Methoden

	public Mitarbeiter sucheMitarbeiter(String nutzerName);

	public List<String> mitarbeiterMenue();

	public void mitarbeiterEinfuegen(String name, String vorName, String nutzerName, String passwort)
			throws MitarbeiterUsernameIstBenutztException;

	public Mitarbeiter mitarbeiterEinloggen(String nutzerName, String passwort)
			throws NutzernameOderPasswortFalschException;

	public void regestiereNeueMitarbeiter(String name, String vorName, String nutzerName, String passwort)
			throws MitarbeiterUsernameIstBenutztException;

	public void loggeMitarbeiterAus(Mitarbeiter mitarbeiter);

	public List<Mitarbeiter> gibAlleMitarbeiter();

	public void schreibeMitarbeiter() throws IOException;

	// Rechnung Methoden

	public Rechnung erstelleRechnung(Bestellung bestl);

	public List<Rechnung> GinAlleRechnungen();

	public void fuegeArtikelInkorbEin(Kunde kunde, Artikel art, int anzahl)
			throws NichtGenugArtikelVorhandenException, BestandPasstNichtMitPackungsGroesseException,
			ArtikelExistiertNichtException, AnzahlIsNichtDefiniertException;

	public void entferneArtikelVomWarenkorb(Kunde kunde, Artikel art, int anzahl)
			throws AnzahlIsNichtDefiniertException, BestandPasstNichtMitPackungsGroesseException,
			ArtikelExistiertNichtException;

	public void loescheArtikelVomWarenkorb(Kunde kunde, Artikel art);

	public void leereWarenkorb(Kunde kunde);

	public Warenkorb getKundenWarenkorb(Kunde kunde);

	// Bestellung
	public Bestellung bestellen(Kunde kunde)
			throws WarenkorbLeerException, NichtGenugArtikelVorhandenException, SenkenUnterNullNichtMoeglichException;

	public List<Bestellung> getBestellungList();

	// Verlauf

	public List<Verlauf> gibVerlauflistaus() throws VerlaufLeerException;

	public void schreibeVerlauf() throws IOException;

	public List<Verlauf> zeigeVerlaufArtikelDreissigTage(String name) throws ArtikelExistiertNichtException;
	
	public void disconnect() throws IOException;
}
