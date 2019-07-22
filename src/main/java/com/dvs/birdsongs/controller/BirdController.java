package com.dvs.birdsongs.controller;
import com.dvs.birdsongs.exception.*;


import com.dvs.birdsongs.model.Bird;
import com.dvs.birdsongs.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class BirdController {

    private BirdRepository birdRepository;

    @Autowired
    public BirdController(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @GetMapping("/birds")
    public List<Bird> getAllBirds() {
        return birdRepository.findAll();
    }

//    @RequestMapping(value = "birds/{id}", method = RequestMethod.GET)
//    public Bird getBird(@PathVariable Long id) {
//        return birdRepository.getOne(id);
//    }

    @GetMapping("/birds/{id}")
    public ResponseEntity<Bird> getBirdById(@PathVariable(value = "id") Long birdId)
            throws ResourceNotFoundException {
        Bird bird = birdRepository.findById(birdId)
                .orElseThrow(() -> new ResourceNotFoundException("Bird + not found for this id :: " + birdId));
        return ResponseEntity.ok().body(bird);
    }

    @PostMapping("/birds")
    public Bird createBird(@Valid @RequestBody Bird bird) {
        return birdRepository.save(bird);
    }

    @PutMapping("/birds/{id}")
    public ResponseEntity<Bird> updateBird(@PathVariable(value = "id") Long birdId,
                                                   @Valid @RequestBody Bird birdDetails) throws ResourceNotFoundException {
        Bird bird = birdRepository.findById(birdId)
                .orElseThrow(() -> new ResourceNotFoundException("Bird not found for this id :: " + birdId));

        bird.setName(birdDetails.getName());
        bird.setImage(birdDetails.getImage());
        bird.setSong1(birdDetails.getSong1());
        final Bird updatedBird = birdRepository.save(bird);
        return ResponseEntity.ok(updatedBird);
    }

    @DeleteMapping("/birds/{id}")
    public Map<String, Boolean> deleteBird(@PathVariable(value = "id") Long birdId)
            throws ResourceNotFoundException {
        Bird bird = birdRepository.findById(birdId)
                .orElseThrow(() -> new ResourceNotFoundException("Bird + not found for this id :: " + birdId));

        birdRepository.delete(bird);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
