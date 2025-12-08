package com.casarick.api.service.imp;

import com.casarick.api.dto.RoleDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Role;
import com.casarick.api.repository.RoleRepository;
import com.casarick.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleImp implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<RoleDTO> getAllRoles() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));

        return Mapper.toDTO(role);
    }

    @Override
    public RoleDTO createNewRole(Role role) {
        return Mapper.toDTO(repository.save(role));
    }

    @Override
    public RoleDTO updateRole(Long id, String name) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
        role.setName(name);
        return Mapper.toDTO(repository.save(role));
    }
}
