package dev.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author Salaheddine El Majdoub
 *
 */
public class CreerReservationDto {
	
	@NotNull
	private LocalDate dateDebut;
	
	@NotNull
    private LocalDate dateFin;
	
	@NotNull
    private String clientId;
	
    @NotNull
    private List<String> chambres;
    
	/**Constructeur 
	 * @param dateDebut
	 * @param dateFin
	 * @param clientId
	 * @param chambres
	 */
	public CreerReservationDto(LocalDate dateDebut, LocalDate dateFin, String clientId, List<String> chambres) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.clientId = clientId;
		this.chambres = chambres;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public List<String> getChambres() {
		return chambres;
	}

	public void setChambres(List<String> chambres) {
		this.chambres = chambres;
	}
	
	
    
    
}