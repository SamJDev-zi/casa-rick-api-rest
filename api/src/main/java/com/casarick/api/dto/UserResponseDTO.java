package com.casarick.dto;

import com.casarick.model.Role;

import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private List<PermissionDTO> permissions;
}
