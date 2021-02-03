package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
