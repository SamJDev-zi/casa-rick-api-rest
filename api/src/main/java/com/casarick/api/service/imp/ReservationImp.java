package com.casarick.api.service.imp;

import com.casarick.api.dto.ReservationRequestDTO;
import com.casarick.api.dto.ReservationResponseDTO;
import com.casarick.api.repository.InventoryRepository;
import com.casarick.api.repository.ReservationRepository;
import com.casarick.api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<ReservationResponseDTO> getAllReservations() {
        return List.of();
    }

    @Override
    public List<ReservationResponseDTO> getReservationsPending() {
        return List.of();
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        return null;
    }

    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO requestDTO) {
        return null;
    }
}
