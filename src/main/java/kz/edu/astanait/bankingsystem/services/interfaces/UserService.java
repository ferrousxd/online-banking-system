package kz.edu.astanait.bankingsystem.services.interfaces;

import kz.edu.astanait.bankingsystem.models.User;

public interface UserService extends GenericService<User> {
    User findByPhoneNumber(String phoneNumber);
}
