package com.casarick.api.service;

import com.casarick.api.dto.BranchDTO;
import com.casarick.api.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    List<BranchDTO> getAllBranches();
    Optional<BranchDTO> getBranchByName(String name);
    BranchDTO createNewBranch(Branch branch);
    BranchDTO updateBranch(Long id, Branch branch);
    void deleteBranch(Long id);
}
