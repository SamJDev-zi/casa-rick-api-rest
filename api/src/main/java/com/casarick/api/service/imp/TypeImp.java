package com.casarick.api.service.imp;

import com.casarick.api.dto.TypeDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Type;
import com.casarick.api.repository.TypeRepository;
import com.casarick.api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeImp implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Override
    public List<TypeDTO> getAllTypes() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public TypeDTO getTypeById(Long id) {
        Type type = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Type not found with id: " + id));
        return Mapper.toDTO(type);
    }

    @Override
    public Optional<TypeDTO> getByName(String name) {
        Optional<Type> type = repository.findByName(name);
        return type.map(Mapper::toDTO);
    }

    @Override
    public TypeDTO createNewType(Type type) {
        return Mapper.toDTO(repository.save(type));
    }
}
