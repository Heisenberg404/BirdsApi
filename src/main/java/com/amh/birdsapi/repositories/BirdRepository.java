package com.amh.birdsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amh.birdsapi.model.Bird;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long> {
    Bird findBycommonName(String name);
    Bird findByCod(String cod);
    
    
}
