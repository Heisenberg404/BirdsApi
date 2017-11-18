package com.amh.birdsapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.amh.birdsapi.domain.BirdInput;
import com.amh.birdsapi.domain.FilterInput;
import com.amh.birdsapi.model.Bird;
import com.amh.birdsapi.model.Country;
import com.amh.birdsapi.model.CountryBird;
import com.amh.birdsapi.model.Zone;
import com.amh.birdsapi.service.BirdService;
import com.amh.birdsapi.service.UtilService;
import com.amh.birdsapi.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	//Service which will do all data retrieval/manipulation work
	@Autowired
	BirdService birdService; 
	@Autowired
	UtilService utilService; 
	@RequestMapping("/test")
    String test() {
        return "Hello World!";
    }
 
    // -------------------Retrieve All Birds---------------------------------------------
 
    @RequestMapping(value = "/Birds/", method = RequestMethod.GET)
    public ResponseEntity<List<Bird>> listAllBirds() {
        List<Bird> birds = birdService.findAllBirds();
        if (birds.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(birds, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single Bird------------------------------------------
 
    @RequestMapping(value = "/Bird/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBird(@PathVariable("id") long id) {
        logger.info("Fetching Bird with id {}", id);
        Bird Bird = birdService.findById(id);
        if (Bird == null) {
            logger.error("Bird with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Bird with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Bird, HttpStatus.OK);
    }
    
 // -------------------Retrieve Filters by zone and names Birds------------------------------------------
    
    @RequestMapping(value = "/BirdFilter", method = RequestMethod.POST)
    public ResponseEntity<?> getBirdFilter(@RequestBody FilterInput birdInput) {
	    	logger.info("Fetching Bird with name {}", birdInput);
	    	List<Bird> result = new ArrayList<>();
	    	List<Bird> birds = birdService.findAllBirds();
	        if (birds.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }else {
	        		if (!StringUtils.isEmpty(birdInput.getFiltro())) {
	        			result = birds.stream().filter(bird -> bird.getCommonName().contains(birdInput.getFiltro()) || bird.getScientificName().contains(birdInput.getFiltro())).collect(Collectors.toList()); 
	        		}
	        
	        		return new ResponseEntity<>(result, HttpStatus.OK);
	        }
    }
 
    // -------------------Create a Bird-------------------------------------------
 
    @RequestMapping(value = "/Bird/", method = RequestMethod.POST)
    public ResponseEntity<?> createBird(@RequestBody BirdInput birdInput, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Bird : {}", birdInput);
        
        Bird bird = new Bird();
        bird.setCod(birdInput.getCod());
        bird.setCommonName(birdInput.getCommonName());
        bird.setScientificName(birdInput.getScientificName());
        
        if (birdService.isBirdExist(bird)) {
            logger.error("Unable to create. A Bird with name {} already exist", bird.getCommonName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Bird with name " + 
            		bird.getCommonName() + " already exist."),HttpStatus.CONFLICT);
        }
        
        birdService.saveBird(bird);
        utilService.saveCountryBird(birdInput.getCodCountry(), birdInput.getCod());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/Bird/{id}").buildAndExpand(bird.getCod()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Bird ------------------------------------------------
 
    @RequestMapping(value = "Update/Bird/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateBird(@PathVariable("id") Long id, @RequestBody Bird bird) {
        logger.info("Updating Bird with id {}", id);
 
        Bird currentBird = birdService.findById(id);
 
        if (currentBird == null) {
            logger.error("Unable to update. Bird with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Bird with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentBird.setCommonName(bird.getCommonName());
        currentBird.setScientificName(bird.getScientificName());
 
        birdService.updateBird(currentBird);
        return new ResponseEntity<>(currentBird, HttpStatus.OK);
    }
 
    // ------------------- Delete a Bird-----------------------------------------
 
    @RequestMapping(value = "Delete/Bird/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteBird(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Bird with id {}", id);
 
        Bird bird = birdService.findById(id);
        if (bird == null) {
            logger.error("Unable to delete. Bird with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Bird with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        birdService.deleteBirdById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    // ------------------- Delete All Birds-----------------------------
 
    @RequestMapping(value = "/Bird/", method = RequestMethod.DELETE)
    public ResponseEntity<Bird> deleteAllBirds() {
        logger.info("Deleting All Birds");
 
        birdService.deleteAllBirds();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // ------------- Get all zones -----------------
    @RequestMapping(value = "/Zones/", method = RequestMethod.GET)
    public ResponseEntity<List<Zone>> getAllZones() {
    	List<Zone> zones = utilService.findAllZones();
        if (zones.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(zones, HttpStatus.OK);
    }
    
 // ------------- Get all countries -----------------
    @RequestMapping(value = "/Countries/", method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getAllCountries() {
    	List<Country> countries = utilService.findAllCountries();
        if (countries.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
    
	
	
}
