package com.amh.birdsapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amh.birdsapi.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	List<Country>findByCodZona(int codzona);
}
