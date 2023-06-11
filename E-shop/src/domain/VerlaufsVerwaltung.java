package domain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.VerlaufLeerException;
import entities.Artikel;
import entities.Nutzer;
import entities.Verlauf;
import persistence.FilePersistenceManager;
import persistence.PersistenceManager;

public class VerlaufsVerwaltung {
	private LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
	private String formattedDatumZeit;

	private List<Verlauf> verlaufListe = new Vector<>();

	private PersistenceManager pm = new FilePersistenceManager();

	public void liesDaten(String datei, ArtikelVerwaltung art, KundeVerwaltung kd, MitarbeiterVerwaltung mt)
			throws IOException, ArtikelExistiertNichtException {
		pm.openForReading(datei);
		Verlauf einVerlauf;
		einVerlauf = pm.ladeVerlauf(art, kd, mt);
		while (einVerlauf != null) {
			verlaufListe.add(einVerlauf);
			einVerlauf = pm.ladeVerlauf(art, kd, mt);
		}
		pm.close();
	}

	public void schreibeDaten(String datei) throws IOException {
		pm.openForWriting(datei);
		for (Verlauf verlauf : verlaufListe) {
			pm.speichereVerlauf(verlauf);
		}
		pm.close();
	}

	/**
	 * 
	 * Fügt einen neuen Verlauf zur Verlaufsliste hinzu.
	 * 
	 * @param aktion  die durchgeführte Aktion
	 * @param nutzer  der betroffene Nutzer
	 * @param artikel der betroffene Artikel
	 */
	public void addVerlauf(Verlauf.AKTIONSTYP aktion, Nutzer nutzer, Artikel artikel, int aenderungsMenge) {
		updateTime();
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		Verlauf verlauf = new Verlauf(aktion, nutzer, artikel, formattedDatumZeit, artikel.getBestand());
		verlauf.setAenderungsMenge(aenderungsMenge);
		verlaufListe.add(verlauf);
	}

	/**
	 * 
	 * Gibt die Liste der Änderungen in eine Verlauflist zurück.
	 * 
	 * @return Liste der Änderungen
	 * @throws VerlaufLeerException wenn die Verlaufsliste leer ist
	 */
	public List<Verlauf> getVerlauflListe() throws VerlaufLeerException {
		if (verlaufListe.isEmpty()) {
			throw new VerlaufLeerException();
		} else {
			return verlaufListe;
		}

	}

	/*
	 * Akualisiert die aktuelleDatumZeit Variable
	 */
	public void updateTime() {
		aktuelleDatumZeit = LocalDateTime.now();
	}

}
