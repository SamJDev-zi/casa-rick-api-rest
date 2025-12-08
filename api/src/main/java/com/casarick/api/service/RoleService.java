package com.casarick.api.service;

import com.casarick.api.dto.RoleDTO;
import com.casarick.api.model.Role;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Long id);
    RoleDTO createNewRole(Role role);
    RoleDTO updateRole(Long id, String name);
}
