package com.casarick.api.controller;

import com.casarick.api.dto.Login;
import com.casarick.api.dto.UserRequestDTO;
import com.casarick.api.dto.UserResponseDTO;
import com.casarick.api.model.User;
import com.casarick.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody Login requestDTO) {
        UserResponseDTO responseDTO = service.userLogin(requestDTO);

        // AGREGA ESTA VALIDACIÓN
        if (responseDTO == null) {
            return ResponseEntity.status(401).build(); // 401 = No autorizado
        }

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserResponseDTO> getUserByName(String name) {
        Optional<UserResponseDTO> user = service.getUserByName(name);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createNewUser(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO user = service.createNewUser(requestDTO);
        return ResponseEntity.created(URI.create("/api/users/" + user.getName())).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateUser(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
