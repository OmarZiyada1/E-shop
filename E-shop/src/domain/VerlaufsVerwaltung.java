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

	private List<Verlauf> verlauflListe = new Vector<>();

	private PersistenceManager pm = new FilePersistenceManager();

	public void liesDaten(String datei) throws IOException, ArtikelExistiertNichtException {
		pm.openForReading(datei);
		Verlauf einVerlauf;
		do {
			einVerlauf = pm.ladeVerlauf();
			if (einVerlauf != null) {
				verlauflListe.add(einVerlauf);
			}
		} while (einVerlauf == null);
		pm.close();
	}
	
	public void schreibeDaten(String datei) throws IOException {
		pm.openForWriting(datei);
		for (Verlauf verlauf : verlauflListe) {
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
	public void addVerlauf(Verlauf.AKTIONSTYP aktion, Nutzer nutzer, Artikel artikel) {
		updateTime();
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		Verlauf verlauf = new Verlauf(aktion, nutzer, artikel, formattedDatumZeit);
		verlauflListe.add(verlauf);
	}

	/**
	 * 
	 * Gibt die Liste der Änderungen in eine Verlauflist zurück.
	 * 
	 * @return Liste der Änderungen
	 * @throws VerlaufLeerException wenn die Verlaufsliste leer ist
	 */
	public List<Verlauf> getVerlauflListe() throws VerlaufLeerException {
		if (verlauflListe.isEmpty()) {
			throw new VerlaufLeerException();
		} else {
			return verlauflListe;
		}

	}

	/*
	 * Akualisiert die aktuelleDatumZeit Variable
	 */
	public void updateTime() {
		aktuelleDatumZeit = LocalDateTime.now();
	}

}
