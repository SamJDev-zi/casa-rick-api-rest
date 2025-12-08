package com.casarick.api.service;

import com.casarick.api.dto.ReservationRequestDTO;
import com.casarick.api.dto.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationResponseDTO> getAllReservations();
    List<ReservationResponseDTO> getReservationsPending();
    ReservationResponseDTO getReservationById(Long id);
    ReservationResponseDTO createReservation(ReservationRequestDTO requestDTO);
    ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO requestDTO);
}
