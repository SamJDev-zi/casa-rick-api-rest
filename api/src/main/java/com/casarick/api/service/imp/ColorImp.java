package com.casarick.api.service.imp;

import com.casarick.api.dto.ColorDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Color;
import com.casarick.api.repository.ColorRepository;
import com.casarick.api.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorImp implements ColorService {
    @Autowired
    private ColorRepository repository;

    @Override
    public List<ColorDTO> getAllColors() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ColorDTO getColorById(Long id) {
        Color color = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Color not found with id: " + id));
        return Mapper.toDTO(color);
    }

    @Override
    public Optional<ColorDTO> getColorByName(String name) {
        return Optional.empty();
    }

    @Override
    public ColorDTO createNewColor(Color color) {
        return Mapper.toDTO(repository.save(color));
    }
}
