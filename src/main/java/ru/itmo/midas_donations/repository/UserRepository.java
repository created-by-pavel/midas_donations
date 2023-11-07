package ru.itmo.midas_donations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.midas_donations.model.User;

public interface UserRepository extends JpaRepository<User, Long> { }
