package com.casarick.api.service.imp;

import com.casarick.api.dto.BranchDTO;
import com.casarick.api.exception.NotFoundException;
import com.casarick.api.mapper.Mapper;
import com.casarick.api.model.Branch;
import com.casarick.api.repository.BranchRepository;
import com.casarick.api.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchImp implements BranchService {

    @Autowired
    private BranchRepository repository;

    @Override
    public List<BranchDTO> getAllBranches() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public Optional<BranchDTO> getBranchByName(String name) {
        Optional<Branch> branch = repository.findByName(name);
        return branch.map(Mapper::toDTO);
    }

    @Override
    public BranchDTO createNewBranch(Branch branch) {
        return Mapper.toDTO(repository.save(branch));
    }

    @Override
    public BranchDTO updateBranch(Long id, Branch branch) {
        Branch search = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Branch not found with id: " + id));

        search.setName(branch.getName());
        search.setAddress(branch.getAddress());
        search.setPhoneNumber(branch.getPhoneNumber());
        search.setActive(branch.isActive());

        return Mapper.toDTO(repository.save(search));
    }

    @Override
    public void deleteBranch(Long id) {
        Branch branch = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Branch not found with id: " + id));
        repository.delete(branch);
    }
}
