package kz.edu.astanait.bankingsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = {"/", "/services"})
    public String getServicesPage() {
        return "services";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }
}
