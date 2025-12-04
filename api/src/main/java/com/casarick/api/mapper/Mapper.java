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
                convertList.add(toDTO(p));
            }

            return UserResponseDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .phoneNumber(user.getPhoneNumber())
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
    public static User toEntity(UserRequestDTO requestDTO, List<Permission> permissions) {
        if (requestDTO != null) {
            return User.builder()
                    .id(requestDTO.getId())
                    .name(requestDTO.getName())
                    .lastName(requestDTO.getLastName())
                    .phoneNumber(requestDTO.getPhoneNumber())
                    .password(requestDTO.getPassword())
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
                    .color(product.getColor())
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
    public static Product toEntity(ProductRequestDTO requestDTO, Category category, Type type, Industry industry) {
        if (requestDTO != null) {
            return Product.builder()
                    .id(requestDTO.getId())
                    .name(requestDTO.getName())
                    .category(category)
                    .Type(type)
                    .industry(industry)
                    .color(requestDTO.getColor())
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
            List<Inventory> aux = reservation.getInventories();
            List<InventoryResponseDTO> convertList = new ArrayList<>();

            for (Inventory i : aux) {
                convertList.add(toDTO(i));
            }

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
                    .inventoriesDTO(convertList)
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
     * @param inventories
     * @return Reservation
     */
    public static Reservation toEntity(ReservationRequestDTO requestDTO, Customer customer, User user, Branch branch
                                       ,List<Inventory> inventories) {
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
                    .inventories(inventories)
                    .build();
        }
        return null;
    }

    /**
     * @param sale
     * @return SaleDTO
     */
    public static SaleDTO toDTO(Sale sale) {
        if (sale != null) {
            return SaleDTO.builder()
                    .id(sale.getId())
                    .description(sale.getDescription())
                    .stock(sale.getStock() != null ? sale.getStock() : 0) // Manejo de Integer a int
                    .createdAt(sale.getCreatedAt())
                    .updatedAt(sale.getUpdatedAt())
                    // Usando los IDs directos de la entidad Sale
                    .customerId(sale.getCustomerId())
                    .userId(sale.getUserId())
                    .branchId(sale.getBranchId())
                    .build();
        }
        return null;
    }

    /**
     * @param saleDTO
     * @return Sale
     */
    public static Sale toEntity(SaleDTO saleDTO) {
        if (saleDTO != null) {
            return Sale.builder()
                    .Id(saleDTO.getId())
                    .description(saleDTO.getDescription())
                    .stock(saleDTO.getStock())
                    .createdAt(saleDTO.getCreatedAt())
                    .updatedAt(saleDTO.getUpdatedAt())
                    // Mapeo de IDs de DTO a IDs de entidad
                    .customerId(saleDTO.getCustomerId())
                    .userId(saleDTO.getUserId())
                    .branchId(saleDTO.getBranchId())
                    .build();
        }
        return null;
    }

    /**
     * @param saleDetailId
     * @return SaleDetailIdDTO
     */
    public static SaleDetailIdDTO toDTO(SaleDetailId saleDetailId) {
        if (saleDetailId != null) {
            return SaleDetailIdDTO.builder()
                    .saleId(saleDetailId.getSaleId())
                    .inventoryId(saleDetailId.getInventoryId())
                    .build();
        }
        return null;
    }

    /**
     * @param saleDetailIdDTO
     * @return SaleDetailId
     */
    public static SaleDetailId toEntity(SaleDetailIdDTO saleDetailIdDTO) {
        if (saleDetailIdDTO != null) {
            return SaleDetailId.builder()
                    .saleId(saleDetailIdDTO.getSaleId())
                    .inventoryId(saleDetailIdDTO.getInventoryId())
                    .build();
        }
        return null;
    }

    /**
     * @param saleDetail
     * @return SaleDetailDTO
     */
    public static SaleDetailDTO toDTO(SaleDetail saleDetail) {
        if (saleDetail != null) {
            return SaleDetailDTO.builder()
                    // Obtenemos el ID de Inventory a través de la relación ManyToOne
                    .inventoryId(saleDetail.getInventory() != null ? saleDetail.getInventory().getId() : null)
                    .saleAmount(saleDetail.getSaleAmount())
                    .saleDiscount(saleDetail.getSaleDiscount())
                    .saleTotal(saleDetail.getSaleTotal())
                    .build();
        }
        return null;
    }

    /**
     * Mapea SaleDetailDTO a la entidad SaleDetail.
     * Requiere las entidades relacionadas completas (Sale e Inventory) para construir la entidad.
     *
     * @param saleDetailDTO
     * @param sale La entidad Sale a la que pertenece el detalle.
     * @param inventory La entidad Inventory del detalle.
     * @return SaleDetail
     */
    public static SaleDetail toEntity(SaleDetailDTO saleDetailDTO, Sale sale, Inventory inventory) {
        if (saleDetailDTO != null) {
            return SaleDetail.builder()
                    .id(SaleDetailId.builder()
                            .saleId(sale != null ? sale.getId() : null)
                            .inventoryId(inventory != null ? inventory.getId() : null)
                            .build())
                    .sale(sale)
                    .inventory(inventory)
                    .saleAmount(saleDetailDTO.getSaleAmount())
                    .saleDiscount(saleDetailDTO.getSaleDiscount())
                    .saleTotal(saleDetailDTO.getSaleTotal())
                    .build();
        }
        return null;
    }
}
