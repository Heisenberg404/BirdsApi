package com.amh.birdsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amh.birdsapi.model.CountryBird;
import java.lang.String;
import java.util.List;

@Repository
public interface CountryBirdRepository extends JpaRepository<CountryBird, Long> {
	List<CountryBird> findByCodPais(String codpais);
}
