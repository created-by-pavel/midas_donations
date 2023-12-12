package ru.itmo.midas_donations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.midas_donations.dto.NewUser;
import ru.itmo.midas_donations.exception.MidasException;
import ru.itmo.midas_donations.model.User;
import ru.itmo.midas_donations.model.UserInfo;
import ru.itmo.midas_donations.repository.UserInfoRepository;
import ru.itmo.midas_donations.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public User saveUser(NewUser newUser) {
        User user = User.builder()
                .preferredUsername(newUser.preferredUsername())
                .userInfo(UserInfo.builder()
                        .name(newUser.name())
                        .secondName(newUser.secondName())
                        .age(newUser.age())
                        .build())
                .build();


        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new MidasException("Пользователь с userId: " + id + " не найден"));
    }

    public User updateUser(Long id, NewUser newUser) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new MidasException("Пользователь с userId: " + id + " не найден"));

        user.setPreferredUsername(newUser.preferredUsername());
        user.getUserInfo().setAge(newUser.age());
        user.getUserInfo().setName(newUser.name());
        user.getUserInfo().setSecondName(newUser.secondName());

        return userRepository.save(user);
    }
}
