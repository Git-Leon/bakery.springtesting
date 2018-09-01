package com.zipcodewilmington.bakery.Controllers;

import com.zipcodewilmington.bakery.Models.Baker;
import com.zipcodewilmington.bakery.Repositories.BakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bakers")
public class BakerController {
    private BakerRepository bakerRepository;

    @Autowired
    public BakerController(BakerRepository repo) {
        this.bakerRepository= repo;
    }


    public ResponseEntity<Iterable<Baker>> index() {
        return new ResponseEntity<>(this.bakerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Baker> show(@PathVariable Long id) {
        return new ResponseEntity<>(this.bakerRepository.findOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Baker> create(@RequestBody Baker baker) {
        return new ResponseEntity<>(this.bakerRepository.save(baker), HttpStatus.CREATED);
    }

    public ResponseEntity<Baker> update(Long id, Baker baker) {
        Baker foundBaker = bakerRepository.findOne(id);

        foundBaker.setName(baker.getName());
        foundBaker.setSpecialty(baker.getSpecialty());

        return new ResponseEntity<>(this.bakerRepository.save(foundBaker), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> destroy(Long id) {
        this.bakerRepository.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
