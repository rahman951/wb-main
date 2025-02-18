package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {
	@Bean
	public CommandLineRunner initData(UserServiceImp userServiceImp, RoleRepository roleRepository) {
		return args -> {
			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);

			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);

			List<Role> adminRoles = new ArrayList<>();
			adminRoles.add(userRole);
			adminRoles.add(adminRole);

			List<Role> userRoles = new ArrayList<>();
			userRoles.add(userRole);

			User user = new User();
			user.setUsername("user");

			user.setEmail("user@mail.ru");
			user.setPassword("123");
			user.setRoles(userRoles);
			userServiceImp.add(user);

			User admin = new User();
			admin.setUsername("admin");

			admin.setEmail("admin@mail.ru");
			admin.setPassword("123");
			admin.setRoles(adminRoles);
			userServiceImp.add(admin);
		};
	}
}
