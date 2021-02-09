package kz.edu.astanait.bankingsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kz.edu.astanait.bankingsystem.util.RandomCardNumber;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @SequenceGenerator(
            name = "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "number", unique = true)
    private String number;

    @NonNull
    @Column(name = "balance_kzt")
    private Double balanceKzt;

    @NonNull
    @Column(name = "balance_usd")
    private Double balanceUsd;

    @NonNull
    @Column(name = "balance_eur")
    private Double balanceEur;

    @NonNull
    @JsonBackReference
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void prePersist() {
        if (number == null)
            number = RandomCardNumber.getCardNumber();
    }
}
