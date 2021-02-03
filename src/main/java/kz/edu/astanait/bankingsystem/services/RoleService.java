package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.models.Role;
import kz.edu.astanait.bankingsystem.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("Role not found"));
    }
}
