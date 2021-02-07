package kz.edu.astanait.bankingsystem.models;

public enum ExchangeRates {

    usd_to_kzt(420.9),
    usd_to_eur(0.83),
    eur_to_kzt(503.0),
    eur_to_usd(1.2),
    kzt_to_usd(0.0024),
    kzt_to_eur(0.002);

    private final Double value;

    private ExchangeRates(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
