package ru.itmo.midas_donations;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itmo.midas_donations.controller.CurrencyController;
import ru.itmo.midas_donations.model.Currency;
import ru.itmo.midas_donations.repository.CurrencyRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyTests {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyController currencyController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCurrency() throws Exception {
        when(currencyRepository.save(any(Currency.class))).thenReturn(new Currency(1L, "USD"));

        mockMvc.perform(post("/currency/USD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Disabled
    @Test
    public void getCurrencyById() throws Exception {
        Long currencyId = 1L;
        Currency currency = new Currency(currencyId, "USD");

        when(currencyRepository.findById(1L)).thenReturn(Optional.of(currency));

        mockMvc.perform(get("/currency/{id}", currencyId))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCurrencies() throws Exception {
        List<Currency> currencies = Arrays.asList(
                new Currency(1L, "USD"),
                new Currency(2L, "EUR")
        );

        when(currencyRepository.findAll()).thenReturn(currencies);

        mockMvc.perform(get("/currency/"))
                .andExpect(status().isOk());
    }

}
