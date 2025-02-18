package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
