package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findCardsByUser(User user);
}
