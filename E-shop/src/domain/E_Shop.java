package domain;

import java.util.List;
import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.KundeIDistbenutztException;
import domain.exceptions.MitarbeiterIDIstBenutztException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import entities.Artikel;
import entities.Kunde;
import entities.Mitarbeiter;


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

	
	//Artikel Methoden
	public List<Artikel> gibAlleArtikeln() {
		return artikelVW.getArtikelListe();
	}

	public List<Artikel> sucheNachName(String name) {

		return artikelVW.sucheArtikel(name);
	}

	public Artikel fuegeArtikelEin(String name, String beschreibung, int bestand, double preis)
			throws AnzahlIsNichtDefiniertException {

		Artikel artikel=new Artikel(name, beschreibung, bestand, preis);
		artikelVW.fugeArtikelEin(artikel, bestand);
		return artikel;
	}

	public void loescheArtikel(String name, String beschreibung, int bestand, double preis)
			throws ArtikelExistiertNichtException {
		Artikel artikel=new Artikel(name, beschreibung, bestand, preis);
		artikelVW.artikelloeschen(artikel);

	}
	public void erhoeheArtikelBestand(Artikel artikel,int anzahl) {
		artikelVW.bestandErhoehen(artikel, anzahl);	
	}
	public void senkenArtikelBestand(Artikel artikel,int anzahl) {
		artikelVW.bestandSenken(artikel, anzahl);	
	}
	
	//Kunde Methoden
	public void kundenRegistrieren(Kunde kunde) throws KundeIDistbenutztException {
		kundeVW.kundeRegistieren(kunde);
		
	}
	public void kundenEinlogen(Kunde kunde) throws KundeIDistbenutztException, NutzernameOderPasswortFalschException {
		kundeVW.kundeEinloggen(kunde.getNutzerName(),kunde.getPasswort());
		
	}
	//Mitarbeiter Methoden
	
	public void mitarbeiterEinfügen(Mitarbeiter mitarbeiter) throws MitarbeiterIDIstBenutztException {
		mitarbeiterVW.fuegeMitarbeiterEin(mitarbeiter);
	
	}
	public void mitarbeiterEinlogen(Mitarbeiter mitarbeiter) throws MitarbeiterIDIstBenutztException, NutzernameOderPasswortFalschException {
		mitarbeiterVW.mitarbeiterEinlogen(mitarbeiter.getNutzerName(),mitarbeiter.getPasswort());
	
	}
	public List<Mitarbeiter> alleMitarbeiter() {
		return mitarbeiterVW.getList_Mitarbeiter();	
	}
	//todo
}
