package be.vdab.entities;

public class Reservatie {
	private Voorstelling voorstelling;
	private int aantalPlaatsen;
	public Reservatie() {
	}
	public Reservatie(Voorstelling voorstelling, int aantalPlaatsen) {
		this.voorstelling = voorstelling;
		this.aantalPlaatsen = aantalPlaatsen;
	}
	public Voorstelling getVoorstelling() {
		return voorstelling;
	}
	public void setVoorstelling(Voorstelling voorstelling) {
		this.voorstelling = voorstelling;
	}
	public int getAantalPlaatsen() {
		return aantalPlaatsen;
	}
	public void setAantalPlaatsen(int aantalPlaatsen) {
		this.aantalPlaatsen = aantalPlaatsen;
	}
	
}
