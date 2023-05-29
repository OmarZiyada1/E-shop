package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import entities.Adresse;
import entities.Artikel;
import entities.Kunde;
import entities.Mitarbeiter;
import entities.Nutzer;
import entities.Verlauf;
import entities.Verlauf.AKTIONSTYP;
import domain.ArtikelVerwaltung;
import domain.KundeVerwaltung;
import domain.MitarbeiterVerwaltung;
import domain.exceptions.ArtikelExistiertNichtException;
import entities.Warenkorb;

public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;
	private ArtikelVerwaltung artVW;
	private KundeVerwaltung kundVW;
	private MitarbeiterVerwaltung mitarbeiterVW;

	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
	}

	public boolean close() {
		if (writer != null)
			writer.close();

		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	public Artikel ladeArtikel() throws IOException {

		int artikelId = Integer.parseInt(liesZeile());
		if (liesZeile() == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		String name = liesZeile();
		String beschreibung = liesZeile();
		int bestand = Integer.parseInt(liesZeile());
		double preis = Double.parseDouble(liesZeile());

		String verfuegbarCode = liesZeile();
		// Codierung des Ausleihstatus in boolean umwandeln
		boolean verfuegbar = verfuegbarCode.equals("t") ? true : false;

		Artikel artikel = new Artikel(artikelId, name, beschreibung, bestand, preis, verfuegbar);
		return artikel;
	}

	public boolean speichereArtikel(Artikel artikel) throws IOException {
		schreibeZeile(artikel.getArtikelId() + "");
		schreibeZeile(artikel.getName());
		schreibeZeile(artikel.getBeschreibung());
		schreibeZeile(artikel.getBestand() + "");
		schreibeZeile(artikel.getPreis() + "");
		if (artikel.isVerfuegbar())
			schreibeZeile("true");
		else
			schreibeZeile("false");

		return true;
	}

	// kunde
	public Kunde ladeKunde() throws IOException {
		int kndNr = Integer.parseInt(liesZeile());
		String name = liesZeile();
		String vorName = liesZeile();
		String nutzerNr = liesZeile();
		String password = liesZeile();

		// Adresse
		String strassenName = liesZeile();
		String hausNr = liesZeile();
		String plz = liesZeile();
		String ort = liesZeile();
		String land = liesZeile();
		Adresse adresse = new Adresse(strassenName, hausNr, plz, ort, land);
		Kunde kunde = new Kunde(kndNr, name, vorName, nutzerNr, password, adresse);
		return kunde;
	}

	public boolean speichereKunde(Kunde kunde) throws IOException {
		schreibeZeile(kunde.getKndNr() + "");
		schreibeZeile(kunde.getName());
		schreibeZeile(kunde.getVorname());
		schreibeZeile(kunde.getNutzerName());
		schreibeZeile(kunde.getPasswort());
		schreibeZeile(kunde.getAdresse().getStrasse());
		schreibeZeile(kunde.getAdresse().gethNr());
		schreibeZeile(kunde.getAdresse().getPlz());
		schreibeZeile(kunde.getAdresse().getOrt());
		schreibeZeile(kunde.getAdresse().getLand());
		return true;
	}

	// kunde
	public Mitarbeiter ladeMitarbeiter() throws IOException {
		int maNr = Integer.parseInt(liesZeile());
		String name = liesZeile();
		String vorName = liesZeile();
		String nutzerNr = liesZeile();
		String password = liesZeile();
		Mitarbeiter ma = new Mitarbeiter(maNr, name, vorName, nutzerNr, password);
		return ma;
	}

	public boolean speichereMitarbeiter(Mitarbeiter mitarbeiter) throws IOException {
		schreibeZeile(mitarbeiter.getMaId() + "");
		schreibeZeile(mitarbeiter.getName());
		schreibeZeile(mitarbeiter.getVorname());
		schreibeZeile(mitarbeiter.getNutzerName());
		schreibeZeile(mitarbeiter.getPasswort());
		return true;
	}

	// Verlauf
	public Verlauf ladeVerlauf() throws IOException, ArtikelExistiertNichtException {
		String aktionS = liesZeile();
		 AKTIONSTYP aktion = AKTIONSTYP.valueOf(aktionS);
		String nutzerVorname = liesZeile();
		String artikelName = liesZeile();
		String formattedDatumZeit = liesZeile();
		Artikel artikel =artVW.sucheArtikel(artikelName);
		Nutzer nutzer = null;
		if(kundVW.sucheKunde(nutzerVorname)!= null) {
			nutzer =kundVW.sucheKunde(nutzerVorname);
		}
		else if (mitarbeiterVW.sucheMitarbeiter(nutzerVorname)!= null) {
			nutzer =mitarbeiterVW.sucheMitarbeiter(nutzerVorname);
		}
		
		Verlauf verlauf = new Verlauf(aktion,nutzer,artikel,formattedDatumZeit);
		return verlauf;

	}

	public boolean speichereVerlauf(Verlauf verlauf) throws IOException {
		schreibeZeile(verlauf.getAktion().name());
		schreibeZeile(verlauf.getNutzer().getNutzerName());
		schreibeZeile(verlauf.getArtikel().getName());
		schreibeZeile(verlauf.getFormattedDatumZeit());
		schreibeZeile(verlauf.getArtikel().getBestand() + "");

		return true;
	}

	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}

}
