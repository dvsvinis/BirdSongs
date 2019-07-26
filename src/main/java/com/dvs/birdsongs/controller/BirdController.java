package com.dvs.birdsongs.controller;
import com.dvs.birdsongs.exception.*;
import com.dvs.birdsongs.service.*;


import com.dvs.birdsongs.model.Bird;
import com.dvs.birdsongs.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping({"/birds"})
public class BirdController {

    private BirdService birdService;
    private BirdRepository birdRepository;


    public BirdController(BirdService birdService) {
        this.birdService = birdService;
    }

    @Autowired
    public BirdController(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @GetMapping("/birds")
    public List<Bird> getBirds() {
        return (List<Bird>) birdRepository.findAll();
    }

    @PostMapping("/birds")
    void addBird(@RequestBody Bird bird) {
        birdRepository.save(bird);
    }

    @DeleteMapping(value = "birds/{id}")
    public void deleteBird(@PathVariable Long id) {
        birdRepository.deleteById(id);
    }




}
