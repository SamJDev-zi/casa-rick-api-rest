package com.casarick.api.service;

import com.casarick.api.dto.SaleRequestDTO;
import com.casarick.api.dto.SaleResponseDTO;
import com.casarick.api.model.Sale;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    List<SaleResponseDTO> getAllSales();
    SaleResponseDTO getSaleById(Long id);
    SaleResponseDTO createSale(SaleRequestDTO requestDTO);
    SaleResponseDTO updateSale(Long id, SaleRequestDTO saleRequestDTO);

    List<SaleResponseDTO> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
