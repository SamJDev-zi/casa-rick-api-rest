package com.casarick.api.service.imp;

import com.casarick.api.dto.ReservationRequestDTO;
import com.casarick.api.dto.ReservationResponseDTO;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.*;
import com.casarick.api.repository.*;
import com.casarick.api.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<ReservationResponseDTO> getReservationsPending() {
        // Asumiendo que implementas un método en el repository o filtras aquí
        return reservationRepository.findAll().stream()
                .filter(r -> "PENDIENTE".equalsIgnoreCase(r.getStatus()))
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
        return Mapper.toDTO(reservation);
    }

    @Override
    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO requestDTO) {
        Inventory inventory = inventoryRepository.findById(requestDTO.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado."));

        // Validar Stock
        if (inventory.getStock() < requestDTO.getStock()) {
            throw new RuntimeException("Stock insuficiente para realizar la reserva.");
        }

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        Branch branch = branchRepository.findById(requestDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada."));

        // 1. Restar stock del inventario
        inventory.setStock(inventory.getStock() - requestDTO.getStock());
        inventoryRepository.save(inventory);

        // 2. Crear Entidad (Asumiendo que Mapper.toEntity existe para Reservation)
        Reservation reservation = Reservation.builder()
                .description(requestDTO.getDescription())
                .deposit(requestDTO.getDeposit())
                .balance(requestDTO.getBalance())
                .status("PENDIENTE") // Estado inicial
                .stock(requestDTO.getStock())
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .expiration(requestDTO.getExpiration())
                .customer(customer)
                .user(user)
                .branch(branch)
                .inventory(inventory)
                .build();

        Reservation saved = reservationRepository.save(reservation);
        return Mapper.toDTO(saved);
    }

    @Override
    @Transactional
    public ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO requestDTO) {
        Reservation existing = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        String oldStatus = existing.getStatus();
        String newStatus = requestDTO.getStatus(); // PENDIENTE, CANCELADA, TERMINADA
        Inventory inventory = existing.getInventory();

        // --- LÓGICA DE STOCK SEGÚN ESTADO ---

        // Si la reserva pasa de PENDIENTE a CANCELADA, devolvemos el stock
        if ("CANCELADA".equalsIgnoreCase(newStatus)) {
            inventory.setStock(inventory.getStock() + existing.getStock());
            inventoryRepository.save(inventory);
        }

        // Si la reserva estaba CANCELADA y vuelve a PENDIENTE (re-activación)
        // Validamos si hay stock suficiente para volver a descontar
        else if ("CANCELADA".equalsIgnoreCase(oldStatus) && "PENDIENTE".equalsIgnoreCase(newStatus)) {
            if (inventory.getStock() < existing.getStock()) {
                throw new RuntimeException("No hay stock suficiente para re-activar la reserva.");
            }
            inventory.setStock(inventory.getStock() - existing.getStock());
            inventoryRepository.save(inventory);
        }

        existing.setBalance(requestDTO.getBalance());
        existing.setStatus(newStatus);
        existing.setUpdated(LocalDateTime.now());

        Reservation updated = reservationRepository.save(existing);
        return Mapper.toDTO(updated);
    }
}