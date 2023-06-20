package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value="/whiskies/{year}")
    public ResponseEntity<List<Whisky>> getAllWhiskyByYear(@PathVariable int year){
        return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
    }

    @GetMapping(value="/whiskies/region/{region}")
    public ResponseEntity<List<Whisky>> getAllWhiskyByRegion(@PathVariable String region){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
    }

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskyFilterByDistillery(
            @RequestParam(name="distillery", required = false) String distillery ,
            @RequestParam(name="age", required = false) Integer age)
    {
        if(distillery != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distillery,age), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
