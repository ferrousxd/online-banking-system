package kz.edu.astanait.bankingsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kz.edu.astanait.bankingsystem.util.RandomCardNumber;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "card")
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
    private String identifier;

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
    public void prePersist() {
        if (identifier == null) {
            identifier = RandomCardNumber.getCardNumber();
        }
    }
}
