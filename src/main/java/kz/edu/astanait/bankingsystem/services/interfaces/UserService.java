package kz.edu.astanait.bankingsystem.services.interfaces;

import kz.edu.astanait.bankingsystem.models.User;

import java.util.List;

public interface UserService extends GenericService<User> {
    User findUserByPhoneNumber(String phoneNumber);
    List<User> findUsersByPhoneNumberContaining(String query);
    void saveUserAndCreateCard(User user);
    void changePassword(String phoneNumber, String password);
}
