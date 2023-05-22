
package entities;

/**
 * @author Mo.Alaskari
 *
 */
public class Adresse {
	private String strasse;
	private String hNr;
	private String plz;
	private String ort;
	private String land;
	
	/**
	 * @param strasse
	 * @param hNr
	 * @param plz
	 * @param ort
	 */
	public Adresse(String strasse, String hNr, String plz, String ort, String land) {
		this.strasse = strasse;
		this.hNr = hNr;
		this.plz = plz;
		this.ort = ort;
		this.land= land;
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	/**
	 * @return the hNr
	 */
	public String gethNr() {
		return hNr;
	}

	/**
	 * @param hNr the hNr to set
	 */
	public void sethNr(String hNr) {
		this.hNr = hNr;
	}

	/**
	 * @return the plz
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * @return the land
	 */
	public String getLand() {
		return land;
	}

	/**
	 * @param land the land to set
	 */
	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tAdresse: ");
		builder.append(strasse);

		builder.append(" "+hNr);
		builder.append(", ");
		builder.append(plz);
		builder.append(" ");
		builder.append(ort);
		builder.append(" ");
		builder.append(land);
		
		return builder.toString();
	}
	
	

}
