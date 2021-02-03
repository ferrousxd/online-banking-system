package kz.edu.astanait.bankingsystem.models;

import javax.persistence.*;

@Entity
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
    private Long id;
    private Double balanceKzt;
    private Double balanceUsd;
    private Double balanceEur;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private User user;

    public Wallet() {

    }

    public Wallet(Double balanceKzt, Double balanceUsd, Double balanceEur, User user) {
        this.balanceKzt = balanceKzt;
        this.balanceUsd = balanceUsd;
        this.balanceEur = balanceEur;
        this.user = user;
    }

    public Wallet(Long id, Double balanceKzt, Double balanceUsd, Double balanceEur, User user) {
        this.id = id;
        this.balanceKzt = balanceKzt;
        this.balanceUsd = balanceUsd;
        this.balanceEur = balanceEur;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalanceKzt() {
        return balanceKzt;
    }

    public void setBalanceKzt(Double balanceKzt) {
        this.balanceKzt = balanceKzt;
    }

    public Double getBalanceUsd() {
        return balanceUsd;
    }

    public void setBalanceUsd(Double balanceUsd) {
        this.balanceUsd = balanceUsd;
    }

    public Double getBalanceEur() {
        return balanceEur;
    }

    public void setBalanceEur(Double balanceEur) {
        this.balanceEur = balanceEur;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
