package kz.edu.astanait.bankingsystem.controllers;

import kz.edu.astanait.bankingsystem.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final RoleService roleService;

    @Autowired
    public WebController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "admin";
    }
}
