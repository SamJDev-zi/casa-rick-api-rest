package com.casarick.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Long roleId;
    private List<Long> permissionsId;
}
