package com.casarick.api.controller;

import com.casarick.api.dto.ReservationRequestDTO;
import com.casarick.api.dto.ReservationResponseDTO;
import com.casarick.api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        return ResponseEntity.ok(service.getAllReservations());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsPending() {
        return ResponseEntity.ok(service.getReservationsPending());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody ReservationRequestDTO requestDTO) {
        ReservationResponseDTO reservation = service.createReservation(requestDTO);

        return ResponseEntity.created(URI.create("/api/reservations/" + reservation.getId())).body(reservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateReservation(id, requestDTO));
    }
}