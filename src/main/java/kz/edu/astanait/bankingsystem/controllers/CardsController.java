package kz.edu.astanait.bankingsystem.controllers;

import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.services.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cards")
public class CardsController {

    private final CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public String getCardsPage(Model model) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUserCards", cardService.getCardsByPhoneNumber(phoneNumber));
        return "cards";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("cardId") Long cardId,
                          @RequestParam("sentCurrency") String sentCurrency,
                          @RequestParam("receivedCurrency") String receivedCurrency,
                          @RequestParam("amount") Double amount) {
        if (amount < 0)
            return "redirect:/cards?error";

        Card card = cardService.findById(cardId);

        switch (sentCurrency) {
            case "KZT" -> {
                if (card.getBalanceKzt() < amount)
                    return "redirect:/cards?error";
            }
            case "USD" -> {
                if (card.getBalanceUsd() < amount)
                    return "redirect:/cards?error";
            }
            case "EUR" -> {
                if (card.getBalanceEur() < amount)
                    return "redirect:/cards?error";
            }
        }

        cardService.convert(cardId, sentCurrency, receivedCurrency, amount);
        return "redirect:/cards?success";
    }

    @PostMapping("/transaction")
    public String makeTransaction(@RequestParam("productId") Long productId,
                                  @RequestParam("senderId") Long senderId,
                                  @RequestParam(value = "receiverId", required = false) Long receiverId,
                                  @RequestParam("amount") Double amount,
                                  RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("productId", productId);

        if (amount < 0)
            return "redirect:/products/{productId}?error";

        Card card = cardService.findById(senderId);

        // 1% from the transaction (transfer money to card of another bank)
        // 1% from the transaction, if amount > 100000 (transfer money to card of this bank)
        if ((productId == 1) || (productId == 2 && amount > 100000)) {
            amount *= 0.99;
        }

        if (card.getBalanceKzt() - amount < 0) {
            return "redirect:/products/{productId}?error";
        }

        cardService.makeTransaction(senderId, receiverId, amount);
        return "redirect:/products/{productId}?success";
    }
}
