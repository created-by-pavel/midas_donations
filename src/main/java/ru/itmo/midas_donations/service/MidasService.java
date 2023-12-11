package ru.itmo.midas_donations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.midas_donations.exception.MidasException;
import ru.itmo.midas_donations.model.Currency;
import ru.itmo.midas_donations.model.Donation;
import ru.itmo.midas_donations.model.User;
import ru.itmo.midas_donations.repository.CurrencyRepository;
import ru.itmo.midas_donations.repository.DonationRepository;
import ru.itmo.midas_donations.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MidasService {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final CurrencyRepository currencyRepository;

    public List<Donation> getDonations(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            return donationRepository.findByUserToId(userId);
        } else {
            throw new MidasException("Пользователь с userId " + userId + " не найден");
        }
    }

    public Donation addDonation(Long userFromId, Long userToId, String message, BigDecimal amount, Long currencyId) {
        User userFrom = userRepository.findById(userFromId).orElseThrow(() ->
                new MidasException("Пользователь с userId: " + userFromId + " не найден"));
        User userTo = userRepository.findById(userFromId).orElseThrow(() ->
                new MidasException("Пользователь с userId: " + userToId + " не найден"));
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() ->
                new MidasException("Такой валюты нет"));

        Donation newDonation = Donation.builder()
                .userFrom(userFrom)
                .userTo(userTo)
                .message(message)
                .date(LocalDateTime.now())
                .amount(amount)
                .currency(currency)
                .build();

        return donationRepository.save(newDonation);
    }
}
