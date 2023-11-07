package ru.itmo.midas_donations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.midas_donations.dto.NewDonation;
import ru.itmo.midas_donations.model.Donation;
import ru.itmo.midas_donations.service.MidasService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donations")
public class MidasController {
    private final MidasService midasService;

    @PostMapping("/sendDonate")
    public ResponseEntity<?> sendDonate(@RequestBody NewDonation newDonation) {
        Donation donation = midasService.addDonation(
                newDonation.userFromId(),
                newDonation.userToId(),
                newDonation.message(),
                newDonation.amount(),
                newDonation.currencyId()
        );
        return ResponseEntity.ok(donation);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Donation>> getDonationsByUser(@PathVariable Long userId) {
        List<Donation> donations = midasService.getDonations(userId);

        if (donations.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(donations);
        }
    }
}
