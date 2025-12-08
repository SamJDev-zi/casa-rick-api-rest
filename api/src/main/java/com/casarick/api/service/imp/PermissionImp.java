package com.casarick.api.service.imp;

import com.casarick.api.dto.PermissionDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Permission;
import com.casarick.api.repository.PermissionRepository;
import com.casarick.api.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionImp implements PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Override
    public List<PermissionDTO> getAllPermission() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public PermissionDTO getPermissionById(Long id) {
        Permission permission = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));

        return Mapper.toDTO(permission);
    }

    @Override
    public Optional<PermissionDTO> getPermissionByName(String name) {
        Optional<Permission> permission = repository.findByPermissionName(name);
        return permission.map(Mapper::toDTO);
    }

    @Override
    public PermissionDTO createNewPermission(Permission permission) {
        return Mapper.toDTO(repository.save(permission));
    }

    @Override
    public PermissionDTO updatePermission(Long id, String name) {
        Permission permission = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Permission not found with id: " + id));
        permission.setPermissionName(name);
        return Mapper.toDTO(repository.save(permission));
    }
}
