package com.casarick.api.controller;

import com.casarick.api.dto.TypeDTO;
import com.casarick.api.model.Type;
import com.casarick.api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    @Autowired
    private TypeService service;

    @GetMapping
    public ResponseEntity<List<TypeDTO>> getAllTypes() {
        return ResponseEntity.ok(service.getAllTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDTO> getTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTypeById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<TypeDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @PostMapping
    public ResponseEntity<TypeDTO> createNewType(@RequestBody Type type) {
        TypeDTO typeDTO = service.createNewType(type);
        return ResponseEntity.created(URI.create("/api/types/" + typeDTO.getName())).body(typeDTO);
    }
}