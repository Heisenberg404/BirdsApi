package com.amh.birdsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amh.birdsapi.model.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
