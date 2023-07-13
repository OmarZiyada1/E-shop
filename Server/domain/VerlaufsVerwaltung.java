package domain;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import domain.exceptions.ArtikelExistiertNichtException;
import domain.exceptions.VerlaufLeerException;
import entities.Artikel;
import entities.Nutzer;
import entities.Verlauf;
import persistence.FilePersistenceManager;
import persistence.PersistenceManager;

public class VerlaufsVerwaltung {
	
	private Date date;

	private List<Verlauf> verlaufListe = new Vector<>();

	private PersistenceManager pm = new FilePersistenceManager();

	public void liesDaten(String datei, ArtikelVerwaltung art, KundeVerwaltung kd, MitarbeiterVerwaltung mt)
			throws IOException, ArtikelExistiertNichtException, ParseException {
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
	 * F�gt einen neuen Verlauf zur Verlaufsliste hinzu.
	 * 
	 * @param aktion  die durchgef�hrte Aktion
	 * @param nutzer  der betroffene Nutzer
	 * @param artikel der betroffene Artikel
	 */
	public void addVerlauf(Verlauf.AKTIONSTYP aktion, Nutzer nutzer, String artikelName, int aenderungsMenge) {
		updateTime();
		
		Verlauf verlauf = new Verlauf(aktion, nutzer, artikelName, date, aenderungsMenge);
		verlauf.setAenderungsMenge(aenderungsMenge);
		verlaufListe.add(verlauf);
	}
	
	
	public List<Verlauf> getLetzeDreissigTageVerlauf (Artikel artikel) throws ArtikelExistiertNichtException{
		
		List<Verlauf> DreissigVerlaufListe = new Vector<>();
		Date aktuellesDatum = new Date();
		
		for (Verlauf verlauf: verlaufListe) {
			 long differenzInMillisekunden = aktuellesDatum.getTime() - verlauf.getDate().getTime();
			 long differenzInTagen = differenzInMillisekunden / (24 * 60 * 60 * 1000);
			if (verlauf.getArtikelName().equals(artikel.getName())&& differenzInTagen <= 30) {
				DreissigVerlaufListe.add(verlauf);
			}
		}
		
		return DreissigVerlaufListe;
		
	}

	/**
	 * 
	 * Gibt die Liste der �nderungen in eine Verlauflist zur�ck.
	 * 
	 * @return Liste der �nderungen
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
		date = new Date();
	}

}
