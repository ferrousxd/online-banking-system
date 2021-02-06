package kz.edu.astanait.bankingsystem.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/users")
public class UsersRestController {

    @GetMapping
    public String getUsername(Principal principal) {
        return principal.getName();
    }
}
