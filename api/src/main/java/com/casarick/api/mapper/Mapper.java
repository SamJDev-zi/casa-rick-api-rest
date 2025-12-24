package com.casarick.api.mapper;

import com.casarick.api.dto.*;
import com.casarick.api.model.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    /**
     * @param branch
     * @return BranchDTO
     */
    public static BranchDTO toDTO(Branch branch) {
       if (branch != null) {
           return BranchDTO.builder()
                   .id(branch.getId())
                   .name(branch.getName())
                   .address(branch.getAddress())
                   .phoneNumber(branch.getPhoneNumber())
                   .isActive(branch.isActive())
                   .build();
       }
       return null;
    }

    /**
     * @param category
     * @return CategoryDTO
     */
    public static CategoryDTO toDTO(Category category) {
        if (category != null) {
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        }
        return null;
    }

    /**
     * @param type
     * @return TypeDTO
     */
    public static TypeDTO toDTO(Type type) {
        if (type != null) {
            return TypeDTO.builder()
                    .id(type.getId())
                    .name(type.getName())
                    .build();
        }
        return null;
    }

    /**
     * @param industry
     * @return IndustryDTO
     */
    public static IndustryDTO toDTO(Industry industry) {
        if (industry != null) {
            return IndustryDTO.builder()
                    .id(industry.getId())
                    .name(industry.getName())
                    .build();
        }
        return null;
    }

    public static ColorDTO toDTO(Color color) {
        if (color != null) {
            return ColorDTO.builder()
                    .id(color.getId())
                    .name(color.getName())
                    .build();
        }

        return null;
    }

    /**
     *
     * @param customer
     * @return CustomerDTO
     */
    public static CustomerDTO toDTO(Customer customer) {
        if (customer != null) {
            return CustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .lastName(customer.getLastName())
                    .phoneNumber(customer.getPhoneNumber())
                    .build();
        }
        return null;
    }

    /**
     *
     * @param permission
     * @return PermissionDTO
     */
    public static PermissionDTO toDTO(Permission permission) {
        if (permission != null) {
            return PermissionDTO.builder()
                    .id(permission.getId())
                    .name(permission.getPermissionName())
                    .build();
        }
        return null;
    }

    /**
     *
     * @param role
     * @return RoleDTO
     */
    public static RoleDTO toDTO(Role role) {
        if (role != null) {
            return RoleDTO.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .build();
        }
        return null;
    }

    /**
     * @param user
     * @return UserResponseDTO
     */
    public static UserResponseDTO toDTO(User user) {
        if (user != null) {
                List<Permission> aux =  user.getPermissions();
                List<PermissionDTO> convertList = new ArrayList<>();

                for (Permission p : aux) {
                    if (p != null ) {

                        convertList.add(toDTO(p));
                    }
                }

                return UserResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .lastName(user.getLastName())
                        .phoneNumber(user.getPhoneNumber())
                        .role(user.getRole())
                        .permissions(convertList)
                        .build();
        }
        return null;
    }

    /**
     *
     * @param requestDTO
     * @param permissions
     * @return User
     */
    public static User toEntity(UserRequestDTO requestDTO, List<Permission> permissions, Role role) {
        if (requestDTO != null) {
                return User.builder()
                        .id(requestDTO.getId())
                        .name(requestDTO.getName())
                        .lastName(requestDTO.getLastName())
                        .phoneNumber(requestDTO.getPhoneNumber())
                        .password(requestDTO.getPassword())
                        .role(role)
                        .permissions(permissions)
                        .build();
        }
        return null;
    }

    /**
     *
     * @param product
     * @return ProductResponseDTO
     */
    public static ProductResponseDTO toDTO(Product product) {
        if (product != null) {
            return ProductResponseDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .categoryDTO(toDTO(product.getCategory()))
                    .typeDTO(toDTO(product.getType()))
                    .industryDTO(toDTO(product.getIndustry()))
                    .color(toDTO(product.getColor()))
                    .size(product.getSize())
                    .photoUrl(product.getPhotoURL())
                    .barCodeNumber(product.getBarCodeNumber())
                    .build();
        }
        return null;
    }

    /**
     *
     * @param requestDTO
     * @param category
     * @param type
     * @param industry
     * @return Product
     */
    public static Product toEntity(ProductRequestDTO requestDTO, Category category, Type type, Industry industry, Color color) {
        if (requestDTO != null) {
            return Product.builder()
                    .id(requestDTO.getId())
                    .name(requestDTO.getName())
                    .category(category)
                    .Type(type)
                    .industry(industry)
                    .color(color)
                    .size(requestDTO.getSize())
                    .photoURL(requestDTO.getPhotoUrl())
                    .barCodeNumber(requestDTO.getBarCodeNumber())
                    .build();
        }
        return null;
    }

    /**
     *
     * @param inventory
     * @return InventoryResponseDTO
     */
    public static InventoryResponseDTO toDTO(Inventory inventory) {
        if (inventory != null) {
            return InventoryResponseDTO.builder()
                    .id(inventory.getId())
                    .productDTO(toDTO(inventory.getProduct()))
                    .costPrice(inventory.getCostPrice())
                    .salePrice(inventory.getSalePrice())
                    .stock(inventory.getStock())
                    .created(inventory.getCreated())
                    .updated(inventory.getUpdated())
                    .branchDTO(toDTO(inventory.getBranch()))
                    .build();
        }
        return null;
    }

    /**
     *
     * @param requestDTO
     * @param product
     * @param branch
     * @return Inventory
     */
    public static Inventory toEntity(InventoryRequestDTO requestDTO, Product product, Branch branch) {
        if (requestDTO != null) {
            return Inventory.builder()
                    .id(requestDTO.getId())
                    .product(product)
                    .costPrice(requestDTO.getCostPrice())
                    .salePrice(requestDTO.getSalePrice())
                    .stock(requestDTO.getStock())
                    .created(requestDTO.getCreated())
                    .updated(requestDTO.getUpdated())
                    .branch(branch)
                    .build();
        }
        return null;
    }

    /**
     *
     * @param reservation
     * @return ReservationResponseDTO
     */
    public static ReservationResponseDTO toDTO(Reservation reservation) {
        if (reservation != null) {
            return ReservationResponseDTO.builder()
                    .id(reservation.getId())
                    .description(reservation.getDescription())
                    .deposit(reservation.getDeposit())
                    .balance(reservation.getBalance())
                    .status(reservation.getStatus())
                    .stock(reservation.getStock())
                    .created(reservation.getCreated())
                    .updated(reservation.getUpdated())
                    .expiration(reservation.getExpiration())
                    .inventoryDTO(toDTO(reservation.getInventory()))
                    .customerDTO(toDTO(reservation.getCustomer()))
                    .userDTO(toDTO(reservation.getUser()))
                    .branchDTO(toDTO(reservation.getBranch()))
                    .build();
        }
        return null;
    }

    /**
     *
     * @param requestDTO
     * @param customer
     * @param user
     * @param branch
     * @return Reservation
     */
    public static Reservation toEntity(ReservationRequestDTO requestDTO, Customer customer, User user, Branch branch
                                       ,Inventory inventory) {
        if (requestDTO != null) {
            return Reservation.builder()
                    .id(requestDTO.getId())
                    .description(requestDTO.getDescription())
                    .deposit(requestDTO.getDeposit())
                    .balance(requestDTO.getDeposit())
                    .status(requestDTO.getStatus())
                    .stock(requestDTO.getStock())
                    .created(requestDTO.getCreated())
                    .updated(requestDTO.getUpdated())
                    .expiration(requestDTO.getExpiration())
                    .customer(customer)
                    .user(user)
                    .branch(branch)
                    .inventory(inventory)
                    .build();
        }
        return null;
    }

    public static SaleResponseDTO toDTO(Sale sale) {
        if (sale != null) {
            return  SaleResponseDTO.builder()
                    .id(sale.getId())
                    .description(sale.getDescription())
                    .createdAt(sale.getCreatedAt())
                    .updatedAt(sale.getCreatedAt())
                    .stock(sale.getStock())
                    .saleAmount(sale.getAmount())
                    .saleDiscount(sale.getDiscount())
                    .saleTotal(sale.getTotal())
                    .customerDTO(toDTO(sale.getCustomerId()))
                    .userDTO(toDTO(sale.getUserId()))
                    .branchDTO(toDTO(sale.getBranchId()))
                    .InventoryDTO(toDTO(sale.getInventory()))
                    .build();
        }
        return null;
    }

    public static Sale toEntity(SaleRequestDTO requestDTO, Customer customer, User user, Branch branch) {
        if (requestDTO != null) {
            return Sale.builder()
                    .description(requestDTO.getDescription())
                    .createdAt(requestDTO.getCreatedAt())
                    .updatedAt(requestDTO.getUpdatedAt())
                    .stock(requestDTO.getStock())
                    .amount(requestDTO.getSaleAmount())
                    .discount(requestDTO.getSaleDiscount())
                    .total(requestDTO.getSaleTotal())
                    .customerId(customer)
                    .userId(user)
                    .branchId(branch)
                    .build();
        }
        return null;
    }
}
