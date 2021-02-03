package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
