package com.casarick.api.service;

import com.casarick.api.dto.InventoryRequestDTO;
import com.casarick.api.dto.InventoryResponseDTO;
import com.casarick.api.model.Category;
import com.casarick.api.model.Industry;
import com.casarick.api.model.Type;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoryService {
    List<InventoryResponseDTO> getAllInventories();
    List<InventoryResponseDTO> getInventoriesWithStock();
    List<InventoryResponseDTO> getAllInventoriesByBranch(Long idBranch);
    List<InventoryResponseDTO> getAllInventoriesByCreated(LocalDateTime localDateTime, Long idBranch);

    List<InventoryResponseDTO> filterInventory(Long category, Long type, Long industry);

    InventoryResponseDTO getInventoryById(Long id);
    InventoryResponseDTO createNewInventory(InventoryRequestDTO requestDTO);
    InventoryResponseDTO updateInventory(Long id, InventoryRequestDTO requestDTO);
    void deleteInventory(Long id);
}
