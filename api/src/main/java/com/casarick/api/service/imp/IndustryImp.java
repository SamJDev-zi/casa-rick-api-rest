package com.casarick.api.service.imp;

import com.casarick.api.dto.IndustryDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Industry;
import com.casarick.api.repository.IndustryRepository;
import com.casarick.api.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryImp implements IndustryService {

    @Autowired
    private IndustryRepository repository;

    @Override
    public List<IndustryDTO> getAllIndustries() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public IndustryDTO getIndustryById(Long id) {
        Industry industry = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Industry not found with id: " + id));

        return Mapper.toDTO(industry);
    }

    @Override
    public Optional<IndustryDTO> getIndustryByName(String name) {
        Optional<Industry> industry = repository.findByName(name);
        return industry.map(Mapper::toDTO);
    }

    @Override
    public IndustryDTO createNewIndustry(Industry industry) {
        return Mapper.toDTO(repository.save(industry));
    }
}
