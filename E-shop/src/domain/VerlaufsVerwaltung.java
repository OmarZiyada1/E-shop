package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import domain.exceptions.VerlaufLeerException;
import entities.Artikel;
import entities.Nutzer;
import entities.Verlauf;

public class VerlaufsVerwaltung {
	private  LocalDateTime aktuelleDatumZeit = LocalDateTime.now();
	private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss",
			Locale.GERMANY);
	private String formattedDatumZeit;
	
	private List<Verlauf> verlauflListe = new Vector<>();
	
	

	public void addVerlauf(String aktion, Nutzer nutzer, Artikel artikel) {
		updateTime();
		this.formattedDatumZeit = aktuelleDatumZeit.format(formatter);
		Verlauf verlauf = new Verlauf(aktion, nutzer, artikel , formattedDatumZeit);
		verlauflListe.add(verlauf);
	}

	public List<Verlauf> getVerlauflListe() throws VerlaufLeerException {
		if (verlauflListe.isEmpty()) {
			throw new VerlaufLeerException();
		} else {
			return verlauflListe;
		}

	}
	
	public void updateTime(){
		aktuelleDatumZeit = LocalDateTime.now();
	}

}
