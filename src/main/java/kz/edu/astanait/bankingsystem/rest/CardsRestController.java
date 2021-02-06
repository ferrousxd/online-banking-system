package kz.edu.astanait.bankingsystem.rest;

import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.services.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/wallets")
public class CardsRestController {

    private final CardServiceImpl cardService;

    @Autowired
    public CardsRestController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getWalletsByPhoneNumber() {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(cardService.getCardsByPhoneNumber(phoneNumber));
        return cardService.getCardsByPhoneNumber(phoneNumber);
    }
}
