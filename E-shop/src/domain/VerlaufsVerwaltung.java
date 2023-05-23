package domain;

import java.util.List;
import java.util.Vector;

import domain.exceptions.VerlaufLeerException;
import entities.Artikel;
import entities.Nutzer;
import entities.Verlauf;

public class VerlaufsVerwaltung {

	private List<Verlauf> verlauflListe = new Vector<>();
	
	

	public void addVerlauf(String aktion, Nutzer nutzer, Artikel artikel) {
		Verlauf verlauf = new Verlauf(aktion, nutzer, artikel);
		verlauflListe.add(verlauf);

	}

	public List<Verlauf> getVerlauflListe() throws VerlaufLeerException {
		if (verlauflListe.isEmpty()) {
			throw new VerlaufLeerException();
		} else {
			return verlauflListe;
		}

	}

}
