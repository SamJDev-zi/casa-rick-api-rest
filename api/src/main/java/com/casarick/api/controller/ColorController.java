package com.casarick.api.controller;

import com.casarick.api.dto.ColorDTO;
import com.casarick.api.model.Color;
import com.casarick.api.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
    @Autowired
    private ColorService service;

    @GetMapping
    public ResponseEntity<List<ColorDTO>> getAllColors() {
        return ResponseEntity.ok(service.getAllColors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getColorById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getColorById(id));
    }

    @PostMapping
    public ResponseEntity<ColorDTO> createNewColor(@RequestBody Color color) {
        ColorDTO colorDTO = service.createNewColor(color);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/colors/{name}")
                .buildAndExpand(colorDTO.getName())
                .toUri();

        return ResponseEntity.created(location).body(colorDTO);
    }
}
