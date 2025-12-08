package com.casarick.api.controller;

import com.casarick.api.dto.RoleDTO;
import com.casarick.api.model.Role;
import com.casarick.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(service.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createNewRole(@RequestBody Role role) {
        RoleDTO roleDTO = service.createNewRole(role);
        return ResponseEntity.created(URI.create("/api/roles/" + roleDTO.getName())).body(roleDTO);
    }

    @PutMapping("/{id}/{name}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @PathVariable String name) {
        return ResponseEntity.ok(service.updateRole(id, name));
    }
}
