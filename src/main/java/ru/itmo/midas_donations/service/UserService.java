package ru.itmo.midas_donations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.midas_donations.dto.NewUser;
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

        userRepository.save(user);
        return user;
    }
}
