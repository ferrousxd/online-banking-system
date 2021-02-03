package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.models.Role;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    @Transactional
    public void updateUser(Long userId, String phoneNumber, Role role) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
    }
}
