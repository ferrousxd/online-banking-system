package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.models.Wallet;
import kz.edu.astanait.bankingsystem.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet getWallet(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new IllegalStateException("Wallet not found"));
    }
}
