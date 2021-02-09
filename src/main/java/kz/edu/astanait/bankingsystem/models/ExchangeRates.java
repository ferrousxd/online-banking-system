package kz.edu.astanait.bankingsystem.models;

public enum ExchangeRates {

    USD_TO_KZT(420.9),
    USD_TO_EUR(0.83),
    EUR_TO_KZT(503.0),
    EUR_TO_USD(1.2),
    KZT_TO_USD(0.0024),
    KZT_TO_EUR(0.002);

    private final Double value;

    ExchangeRates(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
