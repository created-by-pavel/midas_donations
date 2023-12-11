package ru.itmo.midas_donations.dto;

import java.math.BigDecimal;

public record NewDonation (
        Long userFromId,
        Long userToId,
        String message,
        BigDecimal amount,
        Long currencyId
) { }
