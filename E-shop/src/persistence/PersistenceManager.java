package persistence;

import java.io.IOException;

import domain.exceptions.ArtikelExistiertNichtException;
import entities.Artikel;
import entities.Kunde;
import entities.Mitarbeiter;
import entities.Verlauf;


public interface PersistenceManager {

	public void openForReading(String datenquelle) throws IOException;
	
	public void openForWriting(String datenquelle) throws IOException;
	
	public boolean close();

	
	public Artikel ladeArtikel() throws IOException;
	public boolean speichereArtikel(Artikel artikel) throws IOException;
	
	public Kunde ladeKunde() throws IOException;
	public boolean speichereKunde(Kunde kunde) throws IOException;
	
	public Mitarbeiter ladeMitarbeiter() throws IOException;
	public boolean speichereMitarbeiter(Mitarbeiter mitarbeiter) throws IOException;
	
	public Verlauf ladeVerlauf() throws IOException, ArtikelExistiertNichtException;
	public boolean speichereVerlauf(Verlauf verlauf) throws IOException;
	

	
}
