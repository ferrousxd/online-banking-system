package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
