package kz.edu.astanait.bankingsystem.controllers;

import kz.edu.astanait.bankingsystem.services.interfaces.CardService;
import kz.edu.astanait.bankingsystem.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductsController {

    private final ProductService productService;
    private final CardService cardService;

    @Autowired
    public ProductsController(ProductService productService, CardService cardService) {
        this.productService = productService;
        this.cardService = cardService;
    }

    @GetMapping(value = {"/", "/products"})
    public String getProductsPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/products/{productId}")
    public String getSingleProductPage(@PathVariable Long productId, Model model) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("product", productService.findById(productId));
        model.addAttribute("allCards", cardService.findAll());
        model.addAttribute("currentUserCards", cardService.getCardsByPhoneNumber(phoneNumber));
        return "single-product";
    }
}
