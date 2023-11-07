package ru.itmo.midas_donations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.midas_donations.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> { }
