package be.vdab.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Voorstelling {
	private long id;
	private String titel;
	private String uitvoerders;
	private LocalDate datum;
	private Genre genre;
	private BigDecimal prijs;
	private int vrijePlaatsen;
	public Voorstelling() {
		
	}
	public Voorstelling(long id, String titel, String uitvoerders, LocalDate datum, 
			Genre genre, BigDecimal prijs, int vrijePlaatsen) {
		this.id = id;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.datum = datum;
		this.genre = genre;
		this.prijs = prijs;
		this.vrijePlaatsen = vrijePlaatsen;
	}
	public Voorstelling(long id, String titel, String uitvoerders, LocalDate datum, 
			BigDecimal prijs, int vrijePlaatsen) {
		this.id = id;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.datum = datum;
		this.prijs = prijs;
		this.vrijePlaatsen = vrijePlaatsen;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getUitvoerders() {
		return uitvoerders;
	}
	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
	public int getVrijePlaatsen() {
		return vrijePlaatsen;
	}
	public void setVrijePlaatsen(int vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}
	
}
