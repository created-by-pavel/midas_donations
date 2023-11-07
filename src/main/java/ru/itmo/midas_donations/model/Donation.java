package ru.itmo.midas_donations.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userFrom_id")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "userTo_id")
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    private LocalDateTime date;
    private String message;
    private double amount;
}
