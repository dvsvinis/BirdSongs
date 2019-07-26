package com.dvs.birdsongs.service;

import com.dvs.birdsongs.model.*;
import com.dvs.birdsongs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BirdService {

    private BirdRepository birdRepository;

    @Autowired
    public BirdService(BirdRepository repository) {
        this.birdRepository = birdRepository;
    }

    public Bird read(Long id) {
        return birdRepository.findById(id).get();
    }
    public List<Bird> readAll() {
        return birdRepository.findAll();
    }


    public Bird update(Long id, Bird updatedBird) {
        Bird bird = read(id);
        bird.setName(updatedBird.getName());
        bird.setImage(updatedBird.getImage());
        bird.setSong1(updatedBird.getSong1());
        return birdRepository.save(bird);
    }
}
