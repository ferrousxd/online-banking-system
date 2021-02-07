package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
