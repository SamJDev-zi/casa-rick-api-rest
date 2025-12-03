package com.casarick.dto;

import java.util.List;

public class UserRequestDTO {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Long roleId;
    private List<Long> permissionsId;
}
