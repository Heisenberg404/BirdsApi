package com.amh.birdsapi.service;

import java.util.List;

import com.amh.birdsapi.model.Country;
import com.amh.birdsapi.model.CountryBird;
import com.amh.birdsapi.model.Zone;

public interface UtilService {

	List<Zone> findAllZones();
	List<Country> findAllCountries();
	void saveCountryBird(String codPais, String CodAve);
	List<Country> findCountriesByZone(int zone);
	List<CountryBird> findByCodPais(String codPais);
}
