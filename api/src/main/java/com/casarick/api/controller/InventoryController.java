package com.casarick.api.controller;

import com.casarick.api.dto.InventoryRequestDTO;
import com.casarick.api.dto.InventoryResponseDTO;
import com.casarick.api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> getAllInventories() {
        return ResponseEntity.ok(service.getAllInventories());
    }

    @GetMapping("/stock")
    public ResponseEntity<List<InventoryResponseDTO>> getInventoriesWithStock() {
        return ResponseEntity.ok(service.getInventoriesWithStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponseDTO> getInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInventoryById(id));
    }

    @PostMapping
    public ResponseEntity<InventoryResponseDTO> createNewInventory(@RequestBody InventoryRequestDTO requestDTO) {
        InventoryResponseDTO inventory = service.createNewInventory(requestDTO);

        return ResponseEntity.created(URI.create("/api/inventories/" + inventory.getId())).body(inventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponseDTO> updateInventory(@PathVariable Long id, @RequestBody InventoryRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateInventory(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        service.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}