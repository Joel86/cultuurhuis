package be.vdab.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Voorstelling {
	private long id;
	private String titel;
	private String uitvoerders;
	private LocalDateTime datumTijd;
	private Genre genre;
	private BigDecimal prijs;
	private int vrijePlaatsen;
	public Voorstelling() {
	}
	public Voorstelling(long id, String titel, String uitvoerders, LocalDateTime datumTijd, 
			Genre genre, BigDecimal prijs, int vrijePlaatsen) {
		this.id = id;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.datumTijd = datumTijd;
		this.genre = genre;
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
	public LocalDateTime getDatumTijd() {
		return datumTijd;
	}
	public void setDatum(LocalDateTime datumTijd) {
		this.datumTijd = datumTijd;
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
