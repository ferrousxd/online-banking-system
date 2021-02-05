package kz.edu.astanait.bankingsystem.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "wallets")
public class Wallet {

    @Id
    @SequenceGenerator(
            name = "wallet_sequence",
            sequenceName = "wallet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wallet_sequence"
    )
    @Column(name = "id")
    private Long id;

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
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(name = "user_id")
    private User user;
}
