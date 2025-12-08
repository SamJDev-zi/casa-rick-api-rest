package com.casarick.api.controller;

import com.casarick.api.dto.PermissionDTO;
import com.casarick.api.model.Permission;
import com.casarick.api.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService service;

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        return ResponseEntity.ok(service.getAllPermission());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> getPermissionNyId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPermissionById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<PermissionDTO>> getPermissionByName(String name) {
        return ResponseEntity.ok(service.getPermissionByName(name));
    }

    @PostMapping
    public ResponseEntity<PermissionDTO> createNewPermission(@RequestBody Permission permission) {
        PermissionDTO permissionDTO = service.createNewPermission(permission);
        return ResponseEntity.created(URI.create("/api/permissions/" + permissionDTO.getName())).body(permissionDTO);
    }

    @PutMapping("/{id}/{name}")
    public ResponseEntity<PermissionDTO> updatePermission(@PathVariable Long id, @PathVariable String name) {
        return ResponseEntity.ok(service.updatePermission(id, name));
    }
}
