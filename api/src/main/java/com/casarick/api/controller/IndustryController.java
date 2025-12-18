package com.casarick.api.controller;

import com.casarick.api.dto.IndustryDTO;
import com.casarick.api.model.Industry;
import com.casarick.api.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/industries")
public class IndustryController {

    @Autowired
    private IndustryService service;

    @GetMapping
    public ResponseEntity<List<IndustryDTO>> getAllIndustries() {
        return ResponseEntity.ok(service.getAllIndustries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndustryDTO> getIndustryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getIndustryById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<IndustryDTO>> getIndustryByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getIndustryByName(name));
    }

    @PostMapping
    public ResponseEntity<IndustryDTO> createNewIndustry(@RequestBody Industry industry) {
        IndustryDTO industryDTO = service.createNewIndustry(industry);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/industries/{name}")
                .buildAndExpand(industry.getName())
                .toUri();

        return ResponseEntity.created(location).body(industryDTO);

        //return ResponseEntity.created(URI.create("/api/industries/" + industryDTO.getName())).body(industryDTO);
    }
}