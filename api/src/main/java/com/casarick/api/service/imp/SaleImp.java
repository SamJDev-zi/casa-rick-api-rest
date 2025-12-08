package com.casarick.api.service.imp;

import com.casarick.api.dto.SaleRequestDTO;
import com.casarick.api.dto.SaleResponseDTO;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.*;
import com.casarick.api.repository.*;
import com.casarick.api.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleImp implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SaleResponseDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
        return Mapper.toDTO(sale);
    }

    @Override
    @Transactional
    public SaleResponseDTO createSale(SaleRequestDTO requestDTO) {
        Inventory inventory = inventoryRepository.findById(requestDTO.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado."));

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        Branch branch = branchRepository.findById(requestDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada."));

        final int quantitySold = requestDTO.getStock();


        if (inventory.getStock() < quantitySold) {
            throw new RuntimeException("Stock insuficiente para el producto: " + inventory.getProduct().getName());
        }

        Double saleAmount = inventory.getSalePrice() * quantitySold;
        Double discount = requestDTO.getSaleDiscount() != null ? requestDTO.getSaleDiscount() : 0.0;
        Double saleTotal = saleAmount - discount;


        inventory.setStock(inventory.getStock() - quantitySold);
        inventoryRepository.save(inventory); // Persistir el cambio de stock

        Sale sale = Mapper.toEntity(requestDTO, customer, user, branch);

        sale.setInventory(inventory);
        sale.setStock(quantitySold);
        sale.setAmount(saleAmount);
        sale.setDiscount(discount);
        sale.setTotal(saleTotal);
        sale.setCreatedAt(LocalDateTime.now());
        sale.setUpdatedAt(LocalDateTime.now());

        Sale savedSale = saleRepository.save(sale);

        return Mapper.toDTO(savedSale);
    }

    @Override
    @Transactional
    public SaleResponseDTO updateSale(Long id, SaleRequestDTO saleRequestDTO) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));

        // Obtener el inventario original que fue afectado por esta venta
        Inventory originalInventory = existingSale.getInventory();

        // La lógica de actualización es compleja porque debemos compensar el stock original
        // y luego recalcular con la nueva cantidad.

        // 1. Obtener los valores originales de la venta
        int oldQuantitySold = existingSale.getStock();
        Double oldSaleAmount = existingSale.getAmount();

        // 2. Obtener los nuevos valores solicitados
        int newQuantitySold = saleRequestDTO.getStock();
        Double newDiscount = saleRequestDTO.getSaleDiscount() != null ? saleRequestDTO.getSaleDiscount() : 0.0;

        // --- 3. Compensación de Stock y Validación ---

        // Revertir el stock al estado ANTES de esta venta
        // Stock actual del inventario + cantidad vendida originalmente = Stock compensado
        int compensatedStock = originalInventory.getStock() + oldQuantitySold;

        // Validar si el stock compensado es suficiente para la nueva cantidad
        if (compensatedStock < newQuantitySold) {
            throw new RuntimeException("Stock insuficiente para actualizar la cantidad vendida.");
        }

        // --- 4. Recálculo y Aplicación ---

        // a) Recalcular montos
        Double newSaleAmount = originalInventory.getSalePrice() * newQuantitySold;
        Double newSaleTotal = newSaleAmount - newDiscount;

        // b) Aplicar el nuevo stock al inventario
        originalInventory.setStock(compensatedStock - newQuantitySold);
        inventoryRepository.save(originalInventory);

        // c) Actualizar la entidad Sale
        existingSale.setStock(newQuantitySold); // Nueva cantidad vendida
        existingSale.setAmount(newSaleAmount);
        existingSale.setDiscount(newDiscount);
        existingSale.setTotal(newSaleTotal);
        existingSale.setDescription(saleRequestDTO.getDescription()); // Se permite actualizar la descripción
        existingSale.setUpdatedAt(LocalDateTime.now());

        // d) Persistir la Venta y mapear
        Sale updatedSale = saleRepository.save(existingSale);

        return Mapper.toDTO(updatedSale);
    }

    @Override
    public List<SaleResponseDTO> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {

        // El repositorio filtra usando el método que definimos
        List<Sale> sales = saleRepository.findByCreatedAtBetween(startDate, endDate);

        return sales.stream().map(Mapper::toDTO).toList();
    }
}
