package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	@Override
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	@Transactional
	public void add(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		User updatedUser = userRepository.getById(user.getId());
		if (!passwordEncoder.encode(user.getPassword()).equals(updatedUser.getPassword()) && !"".equals(user.getPassword())) {
			updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		updatedUser.setUsername(user.getUsername());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setRoles(user.getRoles());
		userRepository.save(updatedUser);
	}
	
	@Override
	@Transactional
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public User showUser(long id) {
		return userRepository.getById(id);
	}

	@Override
	public Optional<User> findByEmail(String username) {
		return userRepository.findByEmail(username);
	}

	@Override
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), // Используем email как username
				user.getPassword(),
				user.getAuthorities()
		);
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
		
	}
}
