package ui.cui;

import domain.ArtikelVerwaltung;

import domain.BestellungVerwaltung;
import domain.KundeVerwaltung;
import domain.MitarbeiterVerwaltung;
import domain.RechnungsVerwaltung;
import domain.WarenkorbVerwaltung;
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

public class Text {

	public Text() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws MitarbeiterIDIstBenutztException,
			NutzernameOderPasswortFalschException, KundeIDistbenutztException, AnzahlIsNichtDefiniertException,
			ArtikelExistiertNichtException, WarenkorbLeerException, NichtGenugArtikelVorhandenException {
		// TODO Auto-generated method stub

		Adresse adresse = new Adresse("wartStr", "86", "28217", "Bremen", "de");
//
//		Mitarbeiter mitarbeiter1 = new Mitarbeiter("sudki", "koulak", "sk", "password1", adresse);
//		MitarbeiterVerwaltung mw = new MitarbeiterVerwaltung();
//		mw.fuegeMitarbeiterEin(mitarbeiter1);
//
		Adresse adresse2 = new Adresse("bundenstr", "152", "28202", "Bremen", "De");
//		Mitarbeiter mitarbeiter2 = new Mitarbeiter("Mohamad", "Askari", "MA", "password2", adresse2);
//		mw.fuegeMitarbeiterEin(mitarbeiter2);
//
//		mw.mitarbeiterEinloggen("MA", "password2");
//
		KundeVerwaltung kw = new KundeVerwaltung();
		Kunde k1 = new Kunde("Ziyada", "Omar", "OZ", "k123", adresse);
		Kunde k2 = new Kunde("Ahmad", "Mohsen", "AM", "k124", adresse2);
		kw.kundeRegistieren(k1);
		kw.kundeRegistieren(k2);
//
//		kw.kundeEinloggen("OZ", "k123");
//		// kw.kundeEinloggen("EE", "k1234");
//
		Artikel hose = new Artikel( "Hose", "Jeans SlimFit", 5, 30.0);
		Artikel hemd = new Artikel("Hemd", "kurze hemdärmel", 2, 35.0);
		
		Artikel shirt = new Artikel("Shirt", "wolle", 10, 10);

		ArtikelVerwaltung artikelVW = new ArtikelVerwaltung();
		artikelVW.fugeArtikelEin(hose, hose.getBestand());
		artikelVW.fugeArtikelEin(hemd, hemd.getBestand());
		artikelVW.fugeArtikelEin(shirt, shirt.getBestand());
//
		System.out.println(artikelVW.getArtikelListe());
//
		WarenkorbVerwaltung warenkorbVW = new WarenkorbVerwaltung();
		warenkorbVW.fuegeArtikelInKorbEin(k1, shirt, 1);
		warenkorbVW.fuegeArtikelInKorbEin(k1, hemd, 2);
		System.out.println(warenkorbVW.getWarenkorb(k1));
//		warenkorbVW.fuegeArtikelInKorbEin(k2, hose, 3);
//		warenkorbVW.fuegeArtikelInKorbEin(k2, shirt, 5);
//		System.out.println(warenkorbVW.getWarenkorb(k2));
//		// warenkorbVW.entferneArtikelKorbListe(k1, hemd,1);
//		// System.out.println(warenkorbVW.getWarenkorb(k1));
//		// warenkorbVW.loescheArtikeVomKorb(k1,hemd);
//		// System.out.println(warenkorbVW.getWarenkorb(k1));
//		// warenkorbVW.leereWarenKorb(k1);
//
		
//		artikelVW.bestandSenken("Hemd", 1);
//		System.out.println(artikelVW.getArtikelListe());
		BestellungVerwaltung bestellVW = new BestellungVerwaltung();
		Bestellung b1 =bestellVW.bestellen(k1);
//		System.out.println(k1.getAktuelleBestellung());
//		RechnungsVerwaltung rechVW = new RechnungsVerwaltung();
//		rechVW.erstelleRechnung(k1.getAktuelleBestellung());
//		System.out.println(b1.getRechnung());
//
////		System.out.println(bestellVW.getBestellung());
////		System.out.println(warenkorbVW.getWarenkorb(k1));
////		System.out.println(artikelVW.getArtikelListe());

	}

}
