package com.amh.birdsapi.domain;

import java.io.Serializable;

public class BirdInput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cod;
	private String commonName;
	private String scientificName;
	private String codZone;
	private String codCountry;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	public String getCodZone() {
		return codZone;
	}
	public void setCodZone(String codZone) {
		this.codZone = codZone;
	}
	public String getCodCountry() {
		return codCountry;
	}
	public void setCodCountry(String codCountry) {
		this.codCountry = codCountry;
	}
	
	
}
