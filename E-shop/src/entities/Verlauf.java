package entities;



public class Verlauf {

	
	
	private String formattedDatumZeit;
	private Nutzer nutzer;
	private Artikel artikel;
	private String aktion;

	public Verlauf(String aktion, Nutzer nutzer, Artikel artikel, String formattedDatumZeit) {

		this.formattedDatumZeit = formattedDatumZeit;
		this.aktion = aktion;
		this.nutzer = nutzer;
		this.artikel = artikel;

	}

	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public String getFormattedDatumZeit() {
		return formattedDatumZeit;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public String getAktion() {
		return aktion;
	}

	public void setAktion(String aktion) {
		this.aktion = aktion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\nÄnderung:\n ");
		builder.append(aktion);
		builder.append("Name: ");
		builder.append(nutzer.getName());
		builder.append(" hat der Bestand folgende Artikel:  ");
		builder.append(artikel.getName());
		builder.append(",am ");
		builder.append(formattedDatumZeit);
		builder.append(", geändert \n");
		builder.append("Der Bestand Des Artikels beträgt jetzt ");
		builder.append(artikel.getBestand());
		builder.append(" Stück ");

		return builder.toString();
	}

}
