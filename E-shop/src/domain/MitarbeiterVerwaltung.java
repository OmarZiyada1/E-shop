package domain;

import java.util.Iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import domain.exceptions.MitarbeiterIDIstBenutztException;
import domain.exceptions.NutzernameOderPasswortFalschException;
import entities.Adresse;
import entities.Kunde;
import entities.Mitarbeiter;

/*
*Diese Klasse verwaltet Mitarbeiterobjekte und bietet Funktionen zur
*Mitarbeiterverwaltung.
*/
public class MitarbeiterVerwaltung {

	public List<Mitarbeiter> list_Mitarbeiter = new Vector<Mitarbeiter>(); // list mit alle regestrierte Mitarbeiter

	/*
	 * Gibt die Liste der Mitarbeiter zurück.*
	 * 
	 * @return Die Liste der Mitarbeiter.
	 **/
	public List<Mitarbeiter> getList_Mitarbeiter() {
		return list_Mitarbeiter;
	}

	/**
	 * Fügt einen neuen Mitarbeiter zur Liste hinzu.*
	 * 
	 * @param mitarbeiter Der hinzuzufügende Mitarbeiter.
	 * @throws MitarbeiterIDIstBenutztException Wenn die Mitarbeiter bereits
	 *                                          verwendet wird.
	 */
	public void fuegeMitarbeiterEin(Mitarbeiter mitarbeiter) throws MitarbeiterIDIstBenutztException {
		Iterator<Mitarbeiter> iter = list_Mitarbeiter.iterator();

		while (iter.hasNext()) {
			Mitarbeiter ma = iter.next();
			if (ma.getNutzerName().equals(mitarbeiter.getNutzerName()) || ma.getMaId() == mitarbeiter.getMaId()) {
				throw new MitarbeiterIDIstBenutztException(mitarbeiter, "in Mitarbeiter einfuegen()");
			}
		}

		generateMitarbeiterId(mitarbeiter);

		list_Mitarbeiter.add(mitarbeiter);
	}

	/**
	 * 
	 * Generiert eine Mitarbeiter-ID für den übergebenen Mitarbeiter.*
	 * 
	 * @param mitarbeiter Der Mitarbeiter, für den eine ID generiert werden soll.
	 */

	private void generateMitarbeiterId(Mitarbeiter mitarbeiter) {
		if (list_Mitarbeiter.isEmpty()) {
			mitarbeiter.setMaId(1);
		} else {
			int lastMitrbeiterID = list_Mitarbeiter.get(list_Mitarbeiter.size() - 1).getMaId();
			mitarbeiter.setMaId(lastMitrbeiterID + 1);
		}
	}

	/**
	 * 
	 * Meldet einen Mitarbeiter mit den angegebenen Anmeldeinformationen an.*
	 * 
	 * @param nutzerName Der Nutzername des Mitarbeiters.
	 * @param passwort   Das Passwort des Mitarbeiters.
	 * @return Der angemeldete Mitarbeiter.
	 * @throws NutzernameOderPasswortFalschException Wenn der Nutzername oder das
	 *                                               Passwort falsch sind.
	 */

	public Mitarbeiter mitarbeiterEinloggen(String nutzerName, String passwort)
			throws NutzernameOderPasswortFalschException {
		// TODO: Implementiere die Methode "einloggen" für den Nutzer.
		Iterator<Mitarbeiter> iter = list_Mitarbeiter.iterator();

		Mitarbeiter mitarbeiter = null;

		boolean treffer = false;
		while (iter.hasNext()) {
			Mitarbeiter ma = iter.next();
			if (ma.getNutzerName().equals(nutzerName) && ma.getPasswort().equals(passwort)) {
				mitarbeiter = ma;
				treffer = true;
				break;
			}
		}
		if (!treffer || list_Mitarbeiter.isEmpty()) {
			throw new NutzernameOderPasswortFalschException();
		}

		return mitarbeiter;

	}

	/**
	 * 
	 * Registriert einen neuen Mitarbeiter mit den angegebenen Informationen.*
	 * 
	 * @param name       Der Name des Mitarbeiters.
	 * @param vorName    Der Vorname des Mitarbeiters.
	 * @param nutzerName Der Nutzername des Mitarbeiters.
	 * @param passwort   Das Passwort des Mitarbeiters.
	 * @throws MitarbeiterIDIstBenutztException Wenn die Mitarbeiter-ID bereits
	 *                                          verwendet wird.
	 */

	public void neueMitarbeiterRegistieren(String name, String vorName, String nutzerName, String passwort)
			throws MitarbeiterIDIstBenutztException {
		Mitarbeiter mitarbeiter = new Mitarbeiter(name, vorName, nutzerName, passwort);
		fuegeMitarbeiterEin(mitarbeiter);
	}
}
