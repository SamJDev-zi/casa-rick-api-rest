package com.casarick.api.service;

import com.casarick.api.dto.TypeDTO;
import com.casarick.api.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {
    List<TypeDTO> getAllTypes();
    TypeDTO getTypeById(Long id);
    Optional<TypeDTO> getByName(String name);
    TypeDTO createNewType(Type type);
}
