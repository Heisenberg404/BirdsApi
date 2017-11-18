package com.amh.birdsapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="TONT_AVES_PAIS")
public class CountryBird {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	@Column (name="CD_AVE_PAIS", nullable=false)
	private int cod;
	
	@NotEmpty
    @Column(name="CD_PAIS", nullable=false)
	private String codPais;

	@NotEmpty
    @Column(name="CD_AVE", nullable=false)
	private String codAve;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getCodAve() {
		return codAve;
	}

	public void setCodAve(String codAve) {
		this.codAve = codAve;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
