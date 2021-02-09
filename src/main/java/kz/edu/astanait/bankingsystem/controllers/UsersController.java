package kz.edu.astanait.bankingsystem.controllers;

import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.services.interfaces.CardService;
import kz.edu.astanait.bankingsystem.services.interfaces.RoleService;
import kz.edu.astanait.bankingsystem.services.interfaces.TransactionService;
import kz.edu.astanait.bankingsystem.services.interfaces.UserService;
import kz.edu.astanait.bankingsystem.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final RoleService roleService;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

    @Autowired
    public UsersController(UserService userService, TransactionService transactionService, RoleService roleService, EmailSender emailSender) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.roleService = roleService;
        this.emailSender = emailSender;
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/new")
    public String createNewUser(@RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("password") String password,
                                @RequestParam("email") String email,
                                @RequestParam("roleId") Long roleId) {
        User user = new User(phoneNumber, email, passwordEncoder.encode(password), roleService.findById(roleId));
        userService.saveUserAndCreateCard(user);
        emailSender.sendEmail(email);
        return "redirect:/admin?success";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("password") String password) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changePassword(phoneNumber, password);
        return "redirect:/user?success";
    }

    @GetMapping
    public String getUserProfile(Model model) {
        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findUserByPhoneNumber(phoneNumber));
        return "user-profile";
    }

    @GetMapping("/transactions")
    public String getUserTransactions(Model model) {
        User user = userService.findUserByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("transactions", transactionService.findTransactionsByCard_User(user));
        return "user-transactions";
    }
}
