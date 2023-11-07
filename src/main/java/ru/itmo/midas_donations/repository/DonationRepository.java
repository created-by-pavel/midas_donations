package ru.itmo.midas_donations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.midas_donations.model.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserToId(Long userId);
}
