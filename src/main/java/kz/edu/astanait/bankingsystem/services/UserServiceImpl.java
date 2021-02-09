package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.exceptions.UserNotFoundException;
import kz.edu.astanait.bankingsystem.models.Authority;
import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.repositories.CardRepository;
import kz.edu.astanait.bankingsystem.repositories.UserRepository;
import kz.edu.astanait.bankingsystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("userDetailsServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("User with such phone number does not exist"));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Set<Authority> authorities = user.getRole().getAuthorities();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
        authorities.forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName())));

        return new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword(),
                grantedAuthorities
        );
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<User> findUsersByPhoneNumberContaining(String query) {
        return userRepository.findUsersByPhoneNumberContaining(query);
    }

    @Override
    public void saveUserAndCreateCard(User user) {
        userRepository.save(user);
        cardRepository.save(new Card(0.0, 0.0, 0.0, user));
    }

    @Transactional
    public void changePassword(String phoneNumber, String password) {
        User user = userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}
