package org.java.spring.dto;

import java.time.LocalDate;

public class DiscountDTO {

	private String titolo;
    private LocalDate dataDiInizio;
    private LocalDate dataDiFine;
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public LocalDate getDataDiInizio() {
		return dataDiInizio;
	}
	public void setDataDiInizio(LocalDate dataDiInizio) {
		this.dataDiInizio = dataDiInizio;
	}
	public LocalDate getDataDiFine() {
		return dataDiFine;
	}
	public void setDataDiFine(LocalDate dataDiFine) {
		this.dataDiFine = dataDiFine;
	}
    

}