package ru.itmo.midas_donations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.midas_donations.dto.NewUser;
import ru.itmo.midas_donations.model.User;
import ru.itmo.midas_donations.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody NewUser newUser) {
        User user = userService.saveUser(newUser);
        return ResponseEntity.ok(user);
    }


}
