package kz.edu.astanait.bankingsystem.rest;

import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersRestController {

    private final UserService userService;

    @Autowired
    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String query) {
        if (query == null)
            query = "";
        return userService.findUsersByPhoneNumberContaining(query);
    }
}