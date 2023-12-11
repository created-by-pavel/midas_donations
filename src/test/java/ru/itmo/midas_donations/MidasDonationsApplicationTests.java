package ru.itmo.midas_donations;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itmo.midas_donations.dto.NewUser;
import ru.itmo.midas_donations.exception.MidasException;
import ru.itmo.midas_donations.model.Currency;
import ru.itmo.midas_donations.model.Donation;
import ru.itmo.midas_donations.model.User;
import ru.itmo.midas_donations.repository.CurrencyRepository;
import ru.itmo.midas_donations.repository.DonationRepository;
import ru.itmo.midas_donations.repository.UserInfoRepository;
import ru.itmo.midas_donations.repository.UserRepository;
import ru.itmo.midas_donations.service.MidasService;
import ru.itmo.midas_donations.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MidasDonationsApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private UserInfoRepository userInfoRepository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private MidasService midasService;

    @Test
    void getDonations() {
        User user = new User(1L, "ubivashka_123", null);
        User userTo = new User(2L, "batman_2288", null);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        List<Donation> expectedDonations = Collections.singletonList(
                new Donation(1L, user, userTo, new Currency(1L, "USD"), LocalDateTime.now(), "thanks for job", new BigDecimal(300))
        );
        when(donationRepository.findByUserToId(user.getId())).thenReturn(expectedDonations);

        List<Donation> result = midasService.getDonations(user.getId());

        assertEquals(expectedDonations, result);
    }

    @Test
    void getDonations_UserDoesNotExist_ThrowsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MidasException.class, () -> midasService.getDonations(1L));
    }

    @Test
    void addDonation_ValidData_ReturnsSavedDonation() {
        User userFrom = new User(1L, "userFrom", null);
        User userTo = new User(2L, "userTo", null);
        Currency currency = new Currency(1L, "USD");

        when(userRepository.findById(userFrom.getId())).thenReturn(Optional.of(userFrom));
        when(userRepository.findById(userTo.getId())).thenReturn(Optional.of(userTo));
        when(currencyRepository.findById(currency.getId())).thenReturn(Optional.of(currency));

        Donation expectedDonation =  new Donation(1L, userFrom, userTo, new Currency(1L, "USD"), LocalDateTime.now(), "thanks for job", new BigDecimal(300));
        when(donationRepository.save(any(Donation.class))).thenReturn(expectedDonation);

        Donation result = midasService.addDonation(userFrom.getId(), userTo.getId(), "Test Message", BigDecimal.TEN, currency.getId());

        assertEquals(expectedDonation, result);
    }

    @Test
    void saveUser() {
        NewUser newUser = new NewUser("kaka123", 20, "John", "Smith");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        User savedUser = userService.saveUser(newUser);
        assertEquals(newUser.preferredUsername(), savedUser.getPreferredUsername());
        assertEquals(newUser.name(), savedUser.getUserInfo().getName());
        assertEquals(newUser.secondName(), savedUser.getUserInfo().getSecondName());
        assertEquals(newUser.age(), savedUser.getUserInfo().getAge());
    }
}
