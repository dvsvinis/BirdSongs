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
@CrossOrigin()
@RequestMapping({"/"})
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

    @GetMapping("/birdlist")
    public List<Bird> getBirdList() {
        return (List<Bird>) birdRepository.findAll();
    }

    @RequestMapping(value = "birds/{id}", method = RequestMethod.GET)
    public Bird getBird (@PathVariable Long id) {
        return birdRepository.getOne(id);
    }

    @PostMapping("/birds")
    void addBird(@RequestBody Bird bird) {
        birdRepository.save(bird);
    }

    @GetMapping("/birds")
    public List<Bird> getBirds() {
        return (List<Bird>) birdRepository.findAll();
    }

    @DeleteMapping(value = "birds/{id}")
    public void deleteBird(@PathVariable Long id) {
        birdRepository.deleteById(id);
    }




}
