package dev.hotel.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreerClientDto {
	
	@NotBlank
    @Size(min = 2)
    private String nom;

    @Size(min = 2)
    private String prenoms;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

}
