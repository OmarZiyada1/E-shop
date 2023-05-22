package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import entities.Artikel;

public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;

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
				// TODO Auto-generated catch block
				e.printStackTrace();

				return false;
			}
		}

		return true;
	}

/*
 * @author Mo.Alaskari
 * 
 */
	public Artikel ladeArtikel() throws IOException {

		int artikelId = Integer.parseInt(liesZeile());

		if (artikelId == 0) {
			return null;
		}
		String name = liesZeile();
		String beschreibung = liesZeile();
		int bestand = Integer.parseInt(liesZeile());
		double preis = Double.parseDouble(liesZeile());
		boolean verfuegbar = Boolean.parseBoolean(liesZeile());
		Artikel artikel=new Artikel(name, beschreibung, bestand, preis,verfuegbar);
		return artikel;
	}


	
	public boolean speichereArtikel(Artikel b) throws IOException {
		schreibeZeile(b.getArtikelId() + "");
		
		schreibeZeile(b.getName());
//		schreibeZeile(Integer.valueOf(b.getNummer()).toString());
		schreibeZeile(b.getBeschreibung());
		schreibeZeile(b.getBestand()+ "");
		schreibeZeile(b.getPreis()+"");
		if (b.isVerfuegbar())
			schreibeZeile("true");
		else
			schreibeZeile("false");

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
