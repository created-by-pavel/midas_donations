package ru.itmo.midas_donations.dto;

public record NewUser (
        String preferredUsername,
        Integer age,
        String name,
        String secondName
) { }
