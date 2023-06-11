package domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import persistence.FilePersistenceManager;
import persistence.PersistenceManager;
import domain.exceptions.AnzahlIsNichtDefiniertException;
import domain.exceptions.ArtikelExistiertBereitsException;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.MitarbeiterUsernameIstBenutztException;
import entities.Artikel;
import entities.Mitarbeiter;

/**
 * 
 * Diese Klasse verwaltet Artikel und bietet Funktionen zur Artikelverwaltung.
 */
public class ArtikelVerwaltung {

	private List<Artikel> artikelListe = new Vector<>(); // list mit alle eingef�gte Artikeln

	private PersistenceManager pm = new FilePersistenceManager();

	public void liesDaten(String datei) throws IOException, ArtikelExistiertBereitsException {
		pm.openForReading(datei);
	    Artikel einArtikel;
	    einArtikel = pm.ladeArtikel();
	    while (einArtikel != null) {
	    	fugeArtikelEin(einArtikel);
	        einArtikel = pm.ladeArtikel();
	    }
	    pm.close();
	}

	public void schreibeDaten(String datei) throws IOException {
		pm.openForWriting(datei);
		for (Artikel artikel : artikelListe) {
			pm.speichereArtikel(artikel);
		}
		pm.close();
	}

	/**
	 * 
	 * F�gt einen Artikel mit der angegebenen Anzahl hinzu.*
	 * 
	 * @param artikel Der hinzuzuf�gende Artikel.
	 * @param anzahl  Die Anzahl des Artikels.
	 * @throws AnzahlIsNichtDefiniertException Wenn die Anzahl nicht definiert ist.
	 */
	
	

	/**
	 * 
	 * F�gt einen Artikel ohne Angabe der Anzahl hinzu. Artikel kriegt bei einf�gen
	 * Bestand* und man braucht nicht immer anzahl einzugeben bei der eingabe
	 * addiert es sich mit dem Bestand
	 * 
	 * @param artikel Der hinzuzuf�gende Artikel.
	 * @throws AnzahlIsNichtDefiniertException Wenn die Anzahl nicht definiert ist.
	 */
	public void fugeArtikelEin(Artikel artikel) throws ArtikelExistiertBereitsException {
		Iterator<Artikel> iter = artikelListe.iterator();

		while (iter.hasNext()) {
			Artikel art = iter.next();
			if (art.getName().equals(artikel.getName()) ) {
				throw new ArtikelExistiertBereitsException(artikel, ". Sie k�nnen sonst die Artikel Bestand �ndern ");
			}
		}
		genertaeArtiekelNr(artikel);
		artikelListe.add(artikel);
		updateVerfuegbarkeit(artikel);
	}

	/**
	 * 
	 * Generiert eine eindeutige Artikel-ID f�r den �bergebenen Artikel.*
	 * 
	 * @param artikel Der Artikel, f�r den eine ID generiert werden soll.
	 */

	private void genertaeArtiekelNr(Artikel artikel) {
		if (artikelListe.isEmpty()) {
			artikel.setArtikelId(1322);
		} else {
			int lastRechnungNr = artikelListe.get(artikelListe.size() - 1).getArtikelId();
			artikel.setArtikelId(lastRechnungNr + 2);
		}
	}

	/**
	 * 
	 * Erh�ht den Bestand des Artikels mit dem angegebenen Namen um die angegebene
	 * Anzahl.*
	 * 
	 * @param name   Der Name des Artikels.
	 * @param anzahl Die Anzahl, um die der Bestand erh�ht werden soll.
	 * @throws ArtikelExistiertNichtException
	 */

	public Artikel bestandErhoehen(String name, int anzahl) throws ArtikelExistiertNichtException {
		Artikel artikel = sucheArtikel(name);
		artikel.setBestand(artikel.getBestand() + anzahl);
		updateVerfuegbarkeit(artikel);
		return artikel;

	}

	/**
	 * 
	 * Verringert den Bestand des Artikels mit dem angegebenen Namen um die
	 * angegebene Anzahl.*
	 * 
	 * @param name   Der Name des Artikels.
	 * @param anzahl Die Anzahl, um die der Bestand verringert werden soll.
	 * @throws ArtikelExistiertNichtException
	 */
	public Artikel bestandSenken(String name, int anzahl) throws ArtikelExistiertNichtException {
		Artikel artikel = sucheArtikel(name);
		artikel.setBestand(artikel.getBestand() - anzahl);
		updateVerfuegbarkeit(artikel);
		return artikel;

	}

	/**
	 * 
	 * Verringert den Bestand des angegebenen Artikels um die angegebene Anzahl.*
	 * 
	 * @param artikel Der Artikel, dessen Bestand verringert werden soll.
	 * @param anzahl  Die Anzahl, um die der Bestand verringert werden soll.
	 */

	public void bestandSenken(Artikel artikel, int anzahl) {
		artikel.setBestand(artikel.getBestand() - anzahl);
		updateVerfuegbarkeit(artikel);

	}

	/**
	 * 
	 * L�scht den angegebenen Artikel.*
	 * 
	 * @param artikel Der zu l�schende Artikel.
	 * @throws ArtikelExistiertNichtException Wenn der Artikel nicht existiert.
	 */

	public void artikelloeschen(Artikel artikel) throws ArtikelExistiertNichtException {
		if (this.artikelListe.contains(artikel)) {
			artikelListe.remove(artikel);
		} else {
			throw new ArtikelExistiertNichtException(artikel,"");
		}
	}

	/**
	 * 
	 * Sucht nach einem Artikel mit dem angegebenen Namen.*
	 * 
	 * @param name Der Name des zu suchenden Artikels.
	 * @return Der gefundene Artikel oder null, wenn kein Artikel mit dem
	 *         angegebenen Namen gefunden wurde.
	 * @throws ArtikelExistiertNichtException
	 */

	public Artikel sucheArtikel(String name) throws ArtikelExistiertNichtException {
		Artikel suchArtikel = null;
		boolean artikelGefunden = false;

		if (artikelListe.isEmpty()) {
			throw new ArtikelExistiertNichtException(suchArtikel, ". Artikel Liste ist Leer");
		} else {
			Iterator<Artikel> iter = artikelListe.iterator();
			while (iter.hasNext()) {
				Artikel a = iter.next();
				if (a.getName().equals(name)) {
					suchArtikel = a;
					artikelGefunden = true;
					break;
				}
			}
		}

		if (!artikelGefunden) {
			throw new ArtikelExistiertNichtException(suchArtikel, "");
		}

		return suchArtikel;
	}

	/**
	 * 
	 * Aktualisiert die Verf�gbarkeit des angegebenen Artikels.*
	 * 
	 * @param artikel Der Artikel, dessen Verf�gbarkeit aktualisiert werden soll.
	 * @return true, wenn der Artikel verf�gbar ist, andernfalls false.
	 */
	public boolean updateVerfuegbarkeit(Artikel artikel) {
		if (artikel.getBestand() == 0) {
			artikel.setVerfuegbar(false);
			return false;
		} else {
			return true;
		}
	}

	/*
	 * 
	 * @return Die Liste von unsere Artikeln.
	 **/
	public List<Artikel> getArtikelListe() {
		return new Vector<>(artikelListe);
	}
}
