package domain;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import entities.Artikel;
import entities.Mitarbeiter;

public class ArtikelVerwaltung {

	private List<Artikel> artikelListe = new Vector<>();

	public void fugeArtikelEin(Artikel artikel, int anzahl) throws AnzahlIsNichtDefiniertException {
		if (this.artikelListe.contains(artikel)) {
			artikel.setBestand(artikel.getBestand() + anzahl);
			updateVerfuegbarkeit(artikel);
		} else {
			if (anzahl > 0) {
				artikel.setBestand(anzahl);
				genertaeArtiekelNr(artikel);
				artikelListe.add(artikel);
				updateVerfuegbarkeit(artikel);
			} else {
				throw new AnzahlIsNichtDefiniertException();
			}
		}
	}

	public void fugeArtikelEin(Artikel artikel) throws AnzahlIsNichtDefiniertException {
		artikel.setBestand(artikel.getBestand());
		genertaeArtiekelNr(artikel);
		artikelListe.add(artikel);
		updateVerfuegbarkeit(artikel);
	}

	private void genertaeArtiekelNr(Artikel artikel) {
		if (artikelListe.isEmpty()) {
			artikel.setArtikelId(1322);
		} else {
			int lastRechnungNr = artikelListe.get(artikelListe.size() - 1).getArtikelId();
			artikel.setArtikelId(lastRechnungNr + 322);
		}
	}

	public Artikel bestandErhoehen(String name, int anzahl) {
		Artikel artikel = sucheArtikel(name);
		artikel.setBestand(artikel.getBestand() + anzahl);
		updateVerfuegbarkeit(artikel);
		return artikel;

	}

	public Artikel bestandSenken(String name, int anzahl) {
		Artikel artikel = sucheArtikel(name);
		artikel.setBestand(artikel.getBestand() - anzahl);
		updateVerfuegbarkeit(artikel);
		return artikel;

	}
	
	
	public void bestandSenken(Artikel artikel, int anzahl) {
		artikel.setBestand(artikel.getBestand() - anzahl);
		updateVerfuegbarkeit(artikel);
		

	}

	public void artikelloeschen(Artikel artikel) throws ArtikelExistiertNichtException {
		if (this.artikelListe.contains(artikel)) {
			artikelListe.remove(artikel);
		} else {
			throw new ArtikelExistiertNichtException();
		}
	}

	public Artikel sucheArtikel(String name) {
		Artikel suchArtikel = null;
		Iterator<Artikel> iter = artikelListe.iterator();
		while (iter.hasNext()) {
			Artikel a = iter.next();
			if (a.getName().equals(name))
				suchArtikel = a;
		}
		return suchArtikel;
	}

	// Verfügbarkeit
	public boolean updateVerfuegbarkeit(Artikel artikel) {
		if (artikel.getBestand() == 0) {
			artikel.setVerfügbar(false);
			return false;
		} else {
			return true;
		}
	}

	public List<Artikel> getArtikelListe() {
		return new Vector<>(artikelListe);
	}
}
