package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
	
	List<User> getAll();
	
	void add(User user);
	
	void updateUser(User user);
	
	void deleteUser(long id);
	
	User showUser(long id);
	
	Optional<User> findByEmail(String username);
	Optional<User> findById(long id);
	
	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
