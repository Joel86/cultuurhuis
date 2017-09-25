package be.vdab.entities;

public class Reservatie {
	private long id;
	private Voorstelling voorstelling;
	private Klant klant;
	private int aantalPlaatsen;
	public Reservatie() {
	}
	public Reservatie(long id, Voorstelling voorstelling, Klant klant, int aantalPlaatsen) {
		this.id = id;
		this.voorstelling = voorstelling;
		this.klant = klant;
		this.aantalPlaatsen = aantalPlaatsen;
	}
	public Reservatie(Voorstelling voorstelling, Klant klant, int aantalPlaatsen) {
		this.voorstelling = voorstelling;
		this.klant = klant;
		this.aantalPlaatsen = aantalPlaatsen;
	}	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Voorstelling getVoorstelling() {
		return voorstelling;
	}
	public void setVoorstelling(Voorstelling voorstelling) {
		this.voorstelling = voorstelling;
	}
	public Klant getKlant() {
		return klant;
	}
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
	public int getAantalPlaatsen() {
		return aantalPlaatsen;
	}
	public void setAantalPlaatsen(int aantalPlaatsen) {
		this.aantalPlaatsen = aantalPlaatsen;
	}
	
}
