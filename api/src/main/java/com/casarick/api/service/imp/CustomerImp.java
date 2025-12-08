package com.casarick.api.service.imp;

import com.casarick.api.dto.CustomerDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Customer;
import com.casarick.api.repository.CustomerRepository;
import com.casarick.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImp implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
        return Mapper.toDTO(customer);
    }

    @Override
    public Optional<CustomerDTO> getCustomerByName(String name) {
        Optional<Customer> customer = repository.findByName(name);
        return customer.map(Mapper::toDTO);
    }

    @Override
    public CustomerDTO createNewCustomer(Customer customer) {
        return Mapper.toDTO(repository.save(customer));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, Customer customer) {
        Customer search = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

        search.setName(customer.getName());
        search.setLastName(customer.getLastName());
        search.setPhoneNumber(customer.getPhoneNumber());

        return Mapper.toDTO(repository.save(search));
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer search = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

        repository.delete(search);
    }
}
