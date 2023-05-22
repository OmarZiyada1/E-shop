package domain;

import java.util.HashMap;
import java.util.List;
import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.KundeIDistbenutztException;
import domain.exceptions.MitarbeiterIDIstBenutztException;
import domain.exceptions.NichtGenugArtikelVorhandenException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import domain.exceptions.WarenkorbLeerException;
import entities.Adresse;
import entities.Artikel;
import entities.Bestellung;
import entities.Kunde;
import entities.Mitarbeiter;
import entities.Rechnung;
import entities.Warenkorb;

public class E_Shop {

	public ArtikelVerwaltung artikelVW;
	public WarenkorbVerwaltung warenKorbVW;
	public BestellungVerwaltung bestellVW;
	public KundeVerwaltung kundeVW;
	public MitarbeiterVerwaltung mitarbeiterVW;
	public RechnungsVerwaltung rechnungVW;

	public E_Shop() {
		artikelVW = new ArtikelVerwaltung();
		warenKorbVW = new WarenkorbVerwaltung();
		bestellVW = new BestellungVerwaltung();
		kundeVW = new KundeVerwaltung();
		mitarbeiterVW = new MitarbeiterVerwaltung();
		rechnungVW = new RechnungsVerwaltung();
	}

	// Artikel Methoden
	public List<Artikel> gibAlleArtikeln() {
		return artikelVW.getArtikelListe();
	}

	public Artikel sucheNachName(String name) {

		return artikelVW.sucheArtikel(name);
	}

	public Artikel fuegeArtikelEin(String name, String beschreibung, int bestand, double preis, int anzahl)
			throws AnzahlIsNichtDefiniertException {

		Artikel artikel = new Artikel(name, beschreibung, bestand, preis);
		artikelVW.fugeArtikelEin(artikel, anzahl);
		return artikel;
	}

	public Artikel fuegeArtikelEin(String name, String beschreibung, int bestand, double preis)
			throws AnzahlIsNichtDefiniertException
			 {

		Artikel artikel = new Artikel(name, beschreibung, bestand, preis);
		artikelVW.fugeArtikelEin(artikel);
		return artikel;
	}

	public void loescheArtikel(String name, String beschreibung, int bestand, double preis)
			throws ArtikelExistiertNichtException {
		Artikel artikel = new Artikel(name, beschreibung, bestand, preis);
		artikelVW.artikelloeschen(artikel);

	}

	public void erhoeheArtikelBestand(String name,  int anzahl) {
		artikelVW.bestandErhoehen(name, anzahl);
	}

	public void senkenArtikelBestand(String name,  int anzahl) {
		artikelVW.bestandSenken(name, anzahl);
	}

	// Kunde Methoden
	public void kundenRegistrieren(String name, String vorname, String nutzerNr, String passwort, String strasse,
			String hNr, String plz, String ort, String land) throws KundeIDistbenutztException {
		Adresse adresse = new Adresse(strasse, hNr, plz, ort, land);
		Kunde kunde = new Kunde(name,vorname,nutzerNr,passwort,adresse);
		kundeVW.kundeRegistieren(kunde);

	}

	public Kunde kundenEinloggen(String nutzerName, String passwort) throws NutzernameOderPasswortFalschException {
		return kundeVW.kundeEinloggen(nutzerName, passwort);

	}

	public List<Bestellung> GibAlleMeineBestellungen(Kunde kunde) {
		return kundeVW.getMeineBestellungen(kunde);
	}

	public List<Kunde> gibAlleKunden() {
		return kundeVW.getList_Kunde();
	}
	// Mitarbeiter Methoden

	public void mitarbeiterEinfügen(String name, String vorName, String nutzerName, String passwort)
			throws MitarbeiterIDIstBenutztException {

		Mitarbeiter mitarbeiter = new Mitarbeiter(name, vorName, nutzerName, passwort);
		mitarbeiterVW.fuegeMitarbeiterEin(mitarbeiter);

	}

	public Mitarbeiter mitarbeiterEinloggen(String nutzerName, String passwort)
			throws NutzernameOderPasswortFalschException {
		return mitarbeiterVW.mitarbeiterEinloggen(nutzerName, passwort);

	}

	public void regestiereNeueMitarbeiter(String name, String vorName, String nutzerName, String passwort)
			throws MitarbeiterIDIstBenutztException {

		mitarbeiterVW.neueMitarbeiterRegistieren(name, vorName, nutzerName, passwort);

	}

	public List<Mitarbeiter> gibAlleMitarbeiter() {
		return mitarbeiterVW.getList_Mitarbeiter();
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

	public double UpdadteGesamtprise(HashMap<Artikel, Integer> liste, Kunde kunde) {
		return warenKorbVW.updadteGesamtprise(liste, kunde);
	}

	public Warenkorb getKundenWarenkorb(Kunde kunde) {
		return warenKorbVW.getWarenkorb(kunde);
	}

	// Bestellung
	public Bestellung bestellen(Kunde kunde) throws  WarenkorbLeerException {
		return bestellVW.bestellen(kunde);
	}

	public List<Bestellung> getBestellungList() {
		return bestellVW.getBestellungList();
	}

}
