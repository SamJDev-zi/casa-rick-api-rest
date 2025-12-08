package com.casarick.api.service;

import com.casarick.api.dto.CustomerDTO;
import com.casarick.api.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    Optional<CustomerDTO> getCustomerByName(String name);
    CustomerDTO createNewCustomer(Customer customer);
    CustomerDTO updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}
