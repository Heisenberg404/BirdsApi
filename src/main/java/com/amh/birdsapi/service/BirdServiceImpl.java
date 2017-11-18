package com.amh.birdsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.birdsapi.model.Bird;
import com.amh.birdsapi.repositories.BirdRepository;

@Service("birdService")
@Transactional
public class BirdServiceImpl implements BirdService{

    @Autowired
    private BirdRepository birdRepository;

    public Bird findById(Long id) {
        return birdRepository.findOne(id);
    }

    public Bird findByCommonName(String name) {
        return birdRepository.findBycommonName(name);
    }

    public void saveBird(Bird user) {
    		birdRepository.save(user);
    }

    public void updateBird(Bird bird){
        saveBird(bird);
    }

    public void deleteBirdById(Long id){
    		birdRepository.delete(id);
    }

    public void deleteAllBirds(){
    		birdRepository.deleteAll();
    }

    public List<Bird> findAllBirds(){
        return birdRepository.findAll();
    }

    public boolean isBirdExist(Bird bird) {
        return findByCommonName(bird.getCommonName()) != null;
    }

	@Override
	public Bird findByCod(String cod) {
		return birdRepository.findByCod(cod);
	}

	


}
