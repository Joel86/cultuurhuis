package be.vdab.entities;

public class Klant {
	private long id;
	private String voornaam;
	private String familienaam;
	private Adres adres;
	private String gebruikersnaam;
	private String paswoord;
	public Klant() {
	}
	public Klant(long id, String voornaam, String familienaam, Adres adres, String gebruikersnaam, String paswoord) {
		this.id = id;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.adres = adres;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = paswoord;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public String getPaswoord() {
		return paswoord;
	}
	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}
}
