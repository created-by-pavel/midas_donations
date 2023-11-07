package ru.itmo.midas_donations.dto;

public record NewDonation (
        Long userFromId,
        Long userToId,
        String message,
        double amount,
        Long currencyId
) { }
