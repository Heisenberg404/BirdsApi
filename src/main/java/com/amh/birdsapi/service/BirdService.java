package com.amh.birdsapi.service;

import java.util.List;

import com.amh.birdsapi.model.Bird;

public interface BirdService {
	
	Bird findById(Long id);
	
	Bird findByCod(String cod);
    
    Bird findByCommonName(String name);
     
    void saveBird(Bird Bird);
     
    void updateBird(Bird Bird);
     
    void deleteBirdById(Long id);
 
    List<Bird> findAllBirds();
    
    void deleteAllBirds();
     
    boolean isBirdExist(Bird Bird);
}
