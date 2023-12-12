package ru.itmo.midas_donations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.midas_donations.exception.MidasException;
import ru.itmo.midas_donations.model.Currency;
import ru.itmo.midas_donations.repository.CurrencyRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyRepository currencyRepository;

    @PostMapping("/{name}")
    public ResponseEntity<?> createCurrency(@PathVariable String name) {
        Currency newCurrency = new Currency();
        newCurrency.setName(name);
        return ResponseEntity.ok(currencyRepository.save(newCurrency));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrency(@PathVariable Long id) {
        return ResponseEntity.ok(currencyRepository.findById(id).orElseThrow(() ->
                new MidasException("валюта с id: " + id + " не найдена")));
    }

    @GetMapping("/")
    public ResponseEntity<List<Currency>> getAll() {
        return ResponseEntity.ok(currencyRepository.findAll());
    }
}
