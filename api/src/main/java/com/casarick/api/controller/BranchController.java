package com.casarick.api.controller;

import com.casarick.api.dto.BranchDTO;
import com.casarick.api.model.Branch;
import com.casarick.api.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService service;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        return ResponseEntity.ok(service.getAllBranches());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<BranchDTO>> getBranchByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getBranchByName(name));
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createNewBranch(@RequestBody Branch branch) {
        BranchDTO branchDTO = service.createNewBranch(branch);

        return ResponseEntity.created(URI.create("/api/branches/" + branchDTO.getName())).body(branchDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        return ResponseEntity.ok(service.updateBranch(id, branch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        service.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}