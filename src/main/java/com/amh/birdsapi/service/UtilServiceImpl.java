package com.amh.birdsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.birdsapi.model.Country;
import com.amh.birdsapi.model.CountryBird;
import com.amh.birdsapi.model.Zone;
import com.amh.birdsapi.repositories.CountryBirdRepository;
import com.amh.birdsapi.repositories.CountryRepository;
import com.amh.birdsapi.repositories.ZoneRepository;

@Service("utilService")
@Transactional
public class UtilServiceImpl implements UtilService{
	
	@Autowired
    private CountryRepository countryRepository;
	
	@Autowired
    private ZoneRepository zoneRepository;
	
	@Autowired
	private CountryBirdRepository countryBirdRepository;

	@Override
	public List<Zone> findAllZones() {
		return zoneRepository.findAll();
	}
	
	@Override
	public List<Country> findAllCountries() {
		return countryRepository.findAll();
	}

	@Override
	public void saveCountryBird(String codPais, String codAve) {
		CountryBird countryBird = new CountryBird();
		countryBird.setCodAve(codAve);
		countryBird.setCodPais(codPais);
		countryBirdRepository.save(countryBird);
		
	}

	@Override
	public List<Country> findCountriesByZone(int zone) {
		return countryRepository.findByCodZona(zone);
	}

	@Override
	public List<CountryBird> findByCodPais(String codPais) {
		return countryBirdRepository.findByCodPais(codPais);
	}
	
	
	
}
