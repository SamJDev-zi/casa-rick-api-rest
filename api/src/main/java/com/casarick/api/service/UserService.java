package com.casarick.api.service;

import com.casarick.api.dto.Login;
import com.casarick.api.dto.UserRequestDTO;
import com.casarick.api.dto.UserResponseDTO;
import com.casarick.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    Optional<UserResponseDTO> getUserByName(String name);
    UserResponseDTO createNewUser(UserRequestDTO requestDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO);
    UserResponseDTO userLogin(Login requestDTO);
    void deleteUser(Long id);
}
