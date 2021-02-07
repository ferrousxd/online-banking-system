package kz.edu.astanait.bankingsystem;

import kz.edu.astanait.bankingsystem.models.*;
import kz.edu.astanait.bankingsystem.repositories.ProductRepository;
import kz.edu.astanait.bankingsystem.repositories.RoleRepository;
import kz.edu.astanait.bankingsystem.repositories.UserRepository;
import kz.edu.astanait.bankingsystem.repositories.CardRepository;
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
	public CommandLineRunner commandLineRunner(UserRepository userRepository,
											   RoleRepository roleRepository,
											   CardRepository walletRepository,
											   ProductRepository productRepository) {
		return args -> {
			Authority adminPage = new Authority("wallet:create");
			Authority userPage = new Authority("wallet:destroy");

			Role userRole = new Role("USER");
			Role adminRole = new Role("ADMIN");

			userRole.addAuthority(userPage);
			adminRole.addAuthority(userPage);
			adminRole.addAuthority(adminPage);

			roleRepository.saveAll(
					List.of(userRole, adminRole)
			);

			// $2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd. - 123
			// $2y$04$1q3vUA1Y5gy.KGgZUGjDuOhG9EwttoWH7ZXvpqQTviQXDAK.OTJCy - password123
			User chingiz = new User("87779913657", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User chingiz2 = new User("88005553535", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User admin = new User("admin", "$2y$04$1q3vUA1Y5gy.KGgZUGjDuOhG9EwttoWH7ZXvpqQTviQXDAK.OTJCy", adminRole);
			User admin2 = new User("admin222", "$2y$04$1q3vUA1Y5gy.KGgZUGjDuOhG9EwttoWH7ZXvpqQTviQXDAK.OTJCy", adminRole);
			userRepository.saveAll(
					List.of(chingiz, chingiz2, admin, admin2)
			);

			Card card1 = new Card(200.0, 0.0, 0.0, chingiz);
			Card card2 = new Card(1000.0, 10.0, 2.0, chingiz);
			Card card3 = new Card(1000000.0, 10000000000.0, 1.0, admin);
			Card card4 = new Card(10.0, 10.0, 10.0, chingiz2);
			Card card5 = new Card(100000.0, 1000005.5, 1.5, chingiz2);

			walletRepository.saveAll(
					List.of(card1, card2, card3, card4, card5)
			);

			ProductType productType1 = new ProductType("Transfer from card to card");
			ProductType productType2 = new ProductType("Payments");

			Product product1 = new Product("Transfer money to card of another bank", productType1);
			Product product2 = new Product("Transfer money to another card of this bank", productType1);
			Product product3 = new Product("Transfer money to another card of this user", productType1);
			Product product4 = new Product("Mobile Services", productType2);
			Product product5 = new Product("House Services", productType2);

			productRepository.saveAll(
					List.of(product1, product2, product3, product4, product5)
			);
		};
	}
}
