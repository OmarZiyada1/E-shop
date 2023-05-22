/**
 * 
 */
package entities;



public abstract class Nutzer {
	private String name;
	private String vorname;
	private String nutzerName;
	private String passwort;
	private Adresse adresse;
	

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param vorname
	 * @param nutzerNr
	 * @param passwort
	 * @param adresse
	 */
	public Nutzer(String name, String vorname, String nutzerNr, String passwort, Adresse adresse) {
		this.name = name;
		this.vorname = vorname;
		this.nutzerName = nutzerNr;
		this.passwort = passwort;
		this.adresse = adresse;
	}

	public Nutzer(String name, String vorname, String nutzerNr, String passwort) {
		this.name = name;
		this.vorname = vorname;
		this.nutzerName = nutzerNr;
		this.passwort = passwort;
		
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		vorname = vorname;
	}

	/**
	 * @return the nutzerNr
	 */
	public String getNutzerName() {
		return nutzerName;
	}

	/**
	 * @param nutzerNr the nutzerNr to set
	 */
	public void setNutzerName(String nutzerNr) {
		nutzerNr = nutzerNr;
	}

	/**
	 * @return the passwort
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * @param passwort the passwort to set
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\tName: ");
		builder.append(name);
		builder.append(", Vorname: ");
		builder.append(vorname);
		builder.append(", NutzerName: ");
		builder.append(nutzerName);
		builder.append(adresse);
		builder.append("");
		return builder.toString();
	}

	

}
