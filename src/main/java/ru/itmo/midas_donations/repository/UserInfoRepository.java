package ru.itmo.midas_donations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.midas_donations.model.Donation;
import ru.itmo.midas_donations.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> { }
