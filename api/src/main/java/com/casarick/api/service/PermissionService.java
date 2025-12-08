package com.casarick.api.service;

import com.casarick.api.dto.PermissionDTO;
import com.casarick.api.model.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    List<PermissionDTO> getAllPermission();
    PermissionDTO getPermissionById(Long id);
    Optional<PermissionDTO> getPermissionByName(String name);
    PermissionDTO createNewPermission(Permission permission);
    PermissionDTO updatePermission(Long id, String name);
}
