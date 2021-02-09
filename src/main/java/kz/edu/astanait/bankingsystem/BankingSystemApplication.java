package kz.edu.astanait.bankingsystem;

import kz.edu.astanait.bankingsystem.models.Authority;
import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.models.Product;
import kz.edu.astanait.bankingsystem.models.ProductType;
import kz.edu.astanait.bankingsystem.models.Role;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.repositories.CardRepository;
import kz.edu.astanait.bankingsystem.repositories.ProductRepository;
import kz.edu.astanait.bankingsystem.repositories.RoleRepository;
import kz.edu.astanait.bankingsystem.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@SpringBootApplication
@EnableAsync
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
			Authority authority1 = new Authority("users:create");
			Authority authority2 = new Authority("users:update-your-password");
			Authority authority3 = new Authority("transactions:get-your-own");
			Authority authority4 = new Authority("cards:convert-money");
			Authority authority5 = new Authority("products:transact-money");

			Role adminRole = new Role("ADMIN");
			Role userRole = new Role("USER");

			adminRole.addAuthority(authority1);
			adminRole.addAuthority(authority2);
			adminRole.addAuthority(authority3);
			adminRole.addAuthority(authority4);
			adminRole.addAuthority(authority5);

			userRole.addAuthority(authority2);
			userRole.addAuthority(authority3);
			userRole.addAuthority(authority4);
			userRole.addAuthority(authority5);

			roleRepository.saveAll(
					List.of(userRole, adminRole)
			);

			// $2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd. - 123
			// $2y$04$1q3vUA1Y5gy.KGgZUGjDuOhG9EwttoWH7ZXvpqQTviQXDAK.OTJCy - password123
			User user1 = new User("11111111", "11111111@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", adminRole);
			User user2 = new User("22222222", "22222222@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User user3 = new User("33333333", "33333333@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User user4 = new User("44444444", "44444444@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User user5 = new User("55555555", "55555555@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User user6 = new User("66666666", "66666666@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User user7 = new User("77777777", "77777777@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", adminRole);
			User user8 = new User("88888888", "88888888@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User user9 = new User("99999999", "99999999@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", userRole);
			User user0 = new User("00000000", "00000000@gmail.com", "$2y$04$TuwOGxj7z0wtUjrj1u.ML.2cGqo5K2RzkUvcdxknObGcm45rXKLd.", adminRole);
			userRepository.saveAll(
					List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user0)
			);

			Card card1 = new Card(1000.0, 1000000.0, 100.0, user1);
			Card card11 = new Card(25.5, 55555.0, 100.0, user1);
			Card card2 = new Card(200000.0, 1000.0, 1000.0, user2);
			Card card22 = new Card(200.0, 1000.0, 11000.0, user2);
			Card card3 = new Card(10.0, 1000000.0, 10000.0, user3);
			Card card4 = new Card(1000.0, 1000.0, 100.0, user4);
			Card card5 = new Card(200000.0, 1000.0, 1000.0, user5);
			Card card6 = new Card(10.0, 1000000.0, 10000.0, user6);
			Card card7 = new Card(1000.0, 9999.9, 100.0, user7);
			Card card77 = new Card(5.0, 12310.0, 100.0, user7);
			Card card777 = new Card(2.9, 1002.9, 100.0, user7);
			Card card7777 = new Card(1.1, 1000.0, 100.0, user7);
			Card card8 = new Card(10.0, 1000000.0, 10000.0, user8);
			Card card9 = new Card(10.0, 10000.0, 10000.0, user9);
			Card card0 = new Card(10.0, 1000000.0, 10000.0, user0);

			walletRepository.saveAll(
					List.of(card1, card11, card2, card22, card3, card4, card5, card6, card7, card77, card777, card7777, card8, card9, card0)
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
