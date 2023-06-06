package domain;

import java.io.IOException;
import java.util.List;
import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.KundeUsernameIstbenutztException;
import domain.exceptions.MitarbeiterUsernameIstBenutztException;
import domain.exceptions.NichtGenugArtikelVorhandenException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import domain.exceptions.VerlaufLeerException;
import domain.exceptions.WarenkorbLeerException;
import entities.Adresse;
import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;
import entities.Mitarbeiter;
import entities.Nutzer;
import entities.Rechnung;
import entities.Verlauf;
import entities.Verlauf.AKTIONSTYP;
import entities.Warenkorb;

public class E_Shop {

	private ArtikelVerwaltung artikelVW;
	private WarenkorbVerwaltung warenKorbVW;
	private BestellungVerwaltung bestellVW;
	private KundeVerwaltung kundeVW;
	private MitarbeiterVerwaltung mitarbeiterVW;
	private RechnungsVerwaltung rechnungVW;
	private VerlaufsVerwaltung verlaufVW;
	private String datei = "";

	public E_Shop(String datei) throws IOException, ArtikelExistiertBereitsException, ArtikelExistiertNichtException, MitarbeiterUsernameIstBenutztException {

		this.datei = datei;
		artikelVW = new ArtikelVerwaltung();
		artikelVW.liesDaten(datei + "_Artikel.txt");
		warenKorbVW = new WarenkorbVerwaltung();
		bestellVW = new BestellungVerwaltung();
		kundeVW = new KundeVerwaltung();
		kundeVW.liesDaten(datei+"_Kunde.txt");
		mitarbeiterVW = new MitarbeiterVerwaltung();
		mitarbeiterVW.liesDaten(datei+ "_Mitarbeiter.txt");
		rechnungVW = new RechnungsVerwaltung();
		verlaufVW = new VerlaufsVerwaltung();
		verlaufVW.liesDaten(datei + "_Verlauf.txt", artikelVW, kundeVW, mitarbeiterVW);
	}

	// Artikel Methoden
	public List<Artikel> gibAlleArtikeln() {
		return artikelVW.getArtikelListe();
	}

	public Artikel sucheNachName(String name) throws ArtikelExistiertNichtException {
		return artikelVW.sucheArtikel(name);
	}

	public Artikel fuegeArtikelEin(String name, String beschreibung, int bestand, double preis)
			throws AnzahlIsNichtDefiniertException, ArtikelExistiertBereitsException {

		Artikel artikel = new Artikel(name, beschreibung, bestand, preis);
		artikelVW.fugeArtikelEin(artikel);
		return artikel;
	}

	public Artikel fuegeArtikelEin(Mitarbeiter mitarbeiter, String name, String beschreibung, int bestand, double preis)
			throws AnzahlIsNichtDefiniertException, ArtikelExistiertBereitsException {

		Artikel artikel = new Artikel(name, beschreibung, bestand, preis);
		artikelVW.fugeArtikelEin(artikel);
		verlaufVW.addVerlauf(AKTIONSTYP.Neue, mitarbeiter, artikel);

		return artikel;
	}

	public Artikel loescheArtikel(Mitarbeiter mitarbeiter, String name) throws ArtikelExistiertNichtException {
		Artikel artikel = artikelVW.sucheArtikel(name);
		artikelVW.artikelloeschen(artikel);
		verlaufVW.addVerlauf(AKTIONSTYP.LOESCHEN, mitarbeiter, artikel);

		return artikel;

	}

	public Artikel erhoeheArtikelBestand(Mitarbeiter mitarbeiter, String name, int anzahl)
			throws ArtikelExistiertNichtException {
		Artikel artikel = artikelVW.bestandErhoehen(name, anzahl);
		verlaufVW.addVerlauf(AKTIONSTYP.ERHOEHEN, mitarbeiter, artikel);
		return artikel;
	}

	public Artikel senkenArtikelBestand(Mitarbeiter mitarbeiter, String name, int anzahl)
			throws ArtikelExistiertNichtException {
		Artikel artikel = artikelVW.bestandSenken(name, anzahl);
		verlaufVW.addVerlauf(AKTIONSTYP.SENKEN, mitarbeiter, artikel);
		return artikel;
	}

	public void gibArtikelnlisteAus(List<Artikel> artikelListe) {
		if (artikelListe.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			for (Artikel artikel : artikelListe) {
				System.out.println(artikel);
			}
		}
	}

	public void schreibeArtikel() throws IOException {
		artikelVW.schreibeDaten(datei + "_Artikel.txt");
	}

	// Kunde Methoden
	public void kundenRegistrieren(String name, String vorname, String nutzerNr, String passwort, String strasse,
			String hNr, String plz, String ort, String land) throws KundeUsernameIstbenutztException {
		Adresse adresse = new Adresse(strasse, hNr, plz, ort, land);
		Kunde kunde = new Kunde(name, vorname, nutzerNr, passwort, adresse);
		kundeVW.kundeRegistieren(kunde);

	}

	public Kunde kundenEinloggen(String nutzerName, String passwort) throws NutzernameOderPasswortFalschException {
		return kundeVW.kundeEinloggen(nutzerName, passwort);

	}

	public List<Bestellung> GibAlleMeineBestellungen(Kunde kunde) {
		return kundeVW.getMeineBestellungen(kunde);

	}

	public void loggeKundeAus(Kunde kunde) {
		kundeVW.kundeAusloggen(kunde);
	}

	public List<Kunde> gibAlleKunden() {
		return kundeVW.getList_Kunde();
	}
	
	public void schreibeKunde() throws IOException {
		kundeVW.schreibeDaten(datei+"_Kunde.txt");
	}
	// Mitarbeiter Methoden

	public void mitarbeiterEinfuegen(String name, String vorName, String nutzerName, String passwort)
			throws MitarbeiterUsernameIstBenutztException {

		Mitarbeiter mitarbeiter = new Mitarbeiter(name, vorName, nutzerName, passwort);
		mitarbeiterVW.fuegeMitarbeiterEin(mitarbeiter);

	}

	public Mitarbeiter mitarbeiterEinloggen(String nutzerName, String passwort)
			throws NutzernameOderPasswortFalschException {
		return mitarbeiterVW.mitarbeiterEinloggen(nutzerName, passwort);

	}

	public void regestiereNeueMitarbeiter(String name, String vorName, String nutzerName, String passwort)
			throws MitarbeiterUsernameIstBenutztException {

		mitarbeiterVW.neueMitarbeiterRegistieren(name, vorName, nutzerName, passwort);

	}

	public void loggeMitarbeiterAus(Mitarbeiter mitarbeiter) {
		mitarbeiterVW.mitarbeiterAusloggen(mitarbeiter);
	}

	public List<Mitarbeiter> gibAlleMitarbeiter() {
		return mitarbeiterVW.getList_Mitarbeiter();
	}
	
	public void schreibeMitarbeiter() throws IOException {
		mitarbeiterVW.schreibeDaten(datei + "_Mitarbeiter.txt");
	}
	// Rechnung Methoden

	public Rechnung erstelleRechnung(Bestellung bestl) {

		return rechnungVW.erstelleRechnung(bestl);
	}

	public List<Rechnung> GinAlleRechnungen() {
		return rechnungVW.getRechnungenList();
	}

	// Warenkorb

	public void fuegeArtikelInkorbEin(Kunde kunde, Artikel art, int anzahl) throws NichtGenugArtikelVorhandenException {
		warenKorbVW.fuegeArtikelInKorbEin(kunde, art, anzahl);
	}

	public void entferneArtikelVomWarenkorb(Kunde kunde, Artikel art, int anzahl)
			throws AnzahlIsNichtDefiniertException {
		warenKorbVW.entferneArtikelKorbListe(kunde, art, anzahl);
	}

	public void loescheArtikelVomWarenkorb(Kunde kunde, Artikel art) {
		warenKorbVW.loescheArtikeVomKorb(kunde, art);
	}

	public void leereWarenkorb(Kunde kunde) {
		warenKorbVW.leereWarenKorb(kunde);
	}

	public Warenkorb getKundenWarenkorb(Kunde kunde) {
		return warenKorbVW.getWarenkorb(kunde);
	}

	// Bestellung
	public Bestellung bestellen(Kunde kunde) throws WarenkorbLeerException, NichtGenugArtikelVorhandenException {
		Bestellung best = bestellVW.bestellen(kunde);
		for (Artikel artikel : best.getBestellteArtikeln().keySet()) {
			verlaufVW.addVerlauf(AKTIONSTYP.BESTELLEN, kunde, artikel);
		}

		return best;
	}

	public List<Bestellung> getBestellungList() {
		return bestellVW.getBestellungList();
	}

	// Verlauf

	public List<Verlauf> gibVerlauflistaus() throws VerlaufLeerException {
		return verlaufVW.getVerlauflListe();
	}

	//
	public void schreibeVerlauf() throws IOException {
		verlaufVW.schreibeDaten(datei + "_Verlauf.txt");
	}

}
