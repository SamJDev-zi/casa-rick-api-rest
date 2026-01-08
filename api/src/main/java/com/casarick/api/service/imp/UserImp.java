package com.casarick.api.service.imp;

import com.casarick.api.dto.Login;
import com.casarick.api.dto.UserRequestDTO;
import com.casarick.api.dto.UserResponseDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Permission;
import com.casarick.api.model.Role;
import com.casarick.api.model.User;
import com.casarick.api.repository.PermissionRepository;
import com.casarick.api.repository.RoleRepository;
import com.casarick.api.repository.UserRepository;
import com.casarick.api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        return Mapper.toDTO(user);
    }

    @Override
    public Optional<UserResponseDTO> getUserByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        return user.map(Mapper::toDTO);
    }

    @Override
    public UserResponseDTO createNewUser(UserRequestDTO requestDTO) {
        List<Long> permissionIds = requestDTO.getPermissionsId();
        List<Permission> permissionList = permissionRepository.findAllById(permissionIds);

        if (permissionList.size() != permissionIds.size()) {
            throw new NotFoundException("One or more permissions provided do not exist.");
        }

        Role role = roleRepository.findById(requestDTO.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + requestDTO.getRoleId()));

        User newUser = Mapper.toEntity(requestDTO, permissionList, role);

        return Mapper.toDTO(userRepository.save(newUser));
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        List<Long> permissionIds = requestDTO.getPermissionsId();
        List<Permission> permissionList = permissionRepository.findAllById(permissionIds);

        if (permissionList.size() != permissionIds.size()) {
            throw new NotFoundException("One or more permissions requested were not found.");
        }

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));

        updateUser.setName(requestDTO.getName());
        updateUser.setLastName(requestDTO.getLastName());
        updateUser.setPhoneNumber(requestDTO.getPhoneNumber());
        updateUser.setRole(role);
        updateUser.setPermissions(permissionList);

        return Mapper.toDTO(userRepository.save(updateUser));
    }

    @Override
    @Transactional
    public UserResponseDTO userLogin(Login requestDTO) {
        User user = userRepository.getUserByName(requestDTO.getName());

        // Validación extra por si el usuario no existe
        if (user == null) {
            return null;
        }

        if (user.getLastName().equals(requestDTO.getLastName()) && user.getPassword().equals(requestDTO.getPassword())) {
            if (user.getPermissions() != null) {
                org.hibernate.Hibernate.initialize(user.getPermissions());
            }
            return Mapper.toDTO(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }
}
