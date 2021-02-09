package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);
    List<User> findUsersByPhoneNumberContaining(String query);
}
