package com.amh.birdsapi.domain;

import java.io.Serializable;

public class FilterInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String filtro;

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}
