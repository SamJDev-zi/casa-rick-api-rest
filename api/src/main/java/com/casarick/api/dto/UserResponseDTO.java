package com.casarick.api.dto;

import com.casarick.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private List<PermissionDTO> permissions;
}
