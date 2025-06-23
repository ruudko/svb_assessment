package net.koedooder.svb.assessment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.koedooder.svb.assessment.model.User;
import net.koedooder.svb.assessment.model.UserIdToken;
import net.koedooder.svb.assessment.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<UserIdToken> authenticate(@RequestBody User user) {
        String token = userService.loginAndGetToken(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(new UserIdToken(token), HttpStatus.OK);
    }
}