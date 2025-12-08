package com.casarick.api.service;

import com.casarick.api.dto.IndustryDTO;
import com.casarick.api.model.Industry;

import java.util.List;
import java.util.Optional;

public interface IndustryService {
    List<IndustryDTO> getAllIndustries();
    IndustryDTO getIndustryById(Long id);
    Optional<IndustryDTO> getIndustryByName(String name);
    IndustryDTO createNewIndustry(Industry industry);
}
