package com.casarick.api.service.imp;

import com.casarick.api.dto.InventoryRequestDTO;
import com.casarick.api.dto.InventoryResponseDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Branch;
import com.casarick.api.model.Inventory;
import com.casarick.api.model.Product;
import com.casarick.api.repository.BranchRepository;
import com.casarick.api.repository.InventoryRepository;
import com.casarick.api.repository.ProductRepository;
import com.casarick.api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryImp implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<InventoryResponseDTO> getAllInventories() {
        return inventoryRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public List<InventoryResponseDTO> getInventoriesWithStock() {
        // ESTO SOLO DEVUELVE INVENTARIOS CON STOCK > 0
        List<Inventory> inventoryList = inventoryRepository.findByStockGreaterThan(0);
        return inventoryList.stream().map(Mapper::toDTO).toList();
    }

    @Override
    public List<InventoryResponseDTO> getAllInventoriesByBranch(Long idBranch) {
        Branch branch = branchRepository.findById(idBranch)
                .orElseThrow(() -> new NotFoundException("Branch not found with id: " + idBranch));
        List<Inventory> inventoryList = inventoryRepository.getAllByBranch(branch);
        return inventoryList.stream().map(Mapper::toDTO).toList();
    }

    @Override
    public List<InventoryResponseDTO> getAllInventoriesByCreated(LocalDateTime localDateTime, Long idBranch) {
        Branch branch = branchRepository.findById(idBranch)
                .orElseThrow(() -> new NotFoundException("Branch not found with id: " + idBranch));

        LocalDateTime startOfDay = localDateTime.toLocalDate().atStartOfDay(); // 00:00:00
        LocalDateTime endOfDay = localDateTime.toLocalDate().atTime(23, 59, 59, 999999); // 23:59:59

        List<Inventory> inventoryList = inventoryRepository.findByCreatedBetweenAndBranch(startOfDay, endOfDay, branch);

        return inventoryList.stream().map(Mapper::toDTO).toList();
    }

    @Override
    public InventoryResponseDTO getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found with id: " + id));
        return Mapper.toDTO(inventory);
    }

    @Override
    public InventoryResponseDTO createNewInventory(InventoryRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + requestDTO.getProductId()));
        Branch branch = branchRepository.findById(requestDTO.getBranchId())
                .orElseThrow(() -> new NotFoundException("Branch not found with id: " + requestDTO.getBranchId()));

        Inventory inventory = Mapper.toEntity(requestDTO, product, branch);

        inventory.setCreated(LocalDateTime.now());
        inventory.setUpdated(LocalDateTime.now());
        return Mapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponseDTO updateInventory(Long id, InventoryRequestDTO requestDTO) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found with id: " + id));
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + requestDTO.getProductId()));
        Branch branch = branchRepository.findById(requestDTO.getBranchId())
                .orElseThrow(() -> new NotFoundException("Branch not found with id: " + requestDTO.getBranchId()));

        inventory.setProduct(product);
        inventory.setCostPrice(requestDTO.getCostPrice());
        inventory.setSalePrice(requestDTO.getSalePrice());
        inventory.setStock(requestDTO.getStock());
        inventory.setUpdated(LocalDateTime.now());
        inventory.setBranch(branch);

        return Mapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found with id: " + id));

        inventoryRepository.delete(inventory);
    }
}
