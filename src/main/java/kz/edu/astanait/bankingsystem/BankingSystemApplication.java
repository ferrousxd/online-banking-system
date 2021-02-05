package kz.edu.astanait.bankingsystem;

import kz.edu.astanait.bankingsystem.models.Authority;
import kz.edu.astanait.bankingsystem.models.Role;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.models.Wallet;
import kz.edu.astanait.bankingsystem.repositories.RoleRepository;
import kz.edu.astanait.bankingsystem.repositories.UserRepository;
import kz.edu.astanait.bankingsystem.repositories.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository, WalletRepository walletRepository) {
		return args -> {
			Authority adminPage = new Authority("Admin Page");
			Authority userPage = new Authority("User Page");

			Role userRole = new Role("User");
			Role adminRole = new Role("Admin");
			Role guestRole = new Role("Guest");

			userRole.addAuthority(userPage);
			adminRole.addAuthority(userPage);
			adminRole.addAuthority(adminPage);

			roleRepository.saveAll(
					List.of(userRole, adminRole, guestRole)
			);

			// $2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd. - 123
			// $2y$04$1q3vUA1Y5gy.KGgZUGjDuOhG9EwttoWH7ZXvpqQTviQXDAK.OTJCy - password123
			User chingiz = new User("87779913657", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User chingiz2 = new User("88005553535", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", guestRole);
			User admin = new User("admin", "$2y$04$1q3vUA1Y5gy.KGgZUGjDuOhG9EwttoWH7ZXvpqQTviQXDAK.OTJCy", adminRole);
			User admin2 = new User("admin222", "$2y$04$1q3vUA1Y5gy.KGgZUGjDuOhG9EwttoWH7ZXvpqQTviQXDAK.OTJCy", adminRole);
			userRepository.saveAll(
					List.of(chingiz, chingiz2, admin, admin2)
			);

			Wallet wallet1 = new Wallet(200.0, 0.0, 0.0, chingiz);
			Wallet wallet2 = new Wallet(1000.0, 10.0, 2.0, chingiz);
			Wallet wallet3 = new Wallet(1000000.0, 10000000000.0, 1.0, admin);
			Wallet wallet4 = new Wallet(10.0, 10.0, 10.0, chingiz2);
			Wallet wallet5 = new Wallet(100000.0, 1000005.5, 1.5, chingiz2);

			walletRepository.saveAll(
					List.of(wallet1, wallet2, wallet3, wallet4, wallet5)
			);
		};
	}
}
