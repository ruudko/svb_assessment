package net.koedooder.svb.assessment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.koedooder.svb.assessment.model.User;
import net.koedooder.svb.assessment.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users/login")
    public ResponseEntity<IdToken> authenticate(@RequestParam String username, @RequestParam String password) {
        String token = userService.loginAndGetToken(username, password);
        return new ResponseEntity<>(new IdToken(token), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
    class IdToken {
        private String id_token;

        IdToken(String idToken) {
            id_token = idToken;
        }

        public String getId_token() {
            return id_token;
        }

        public void setId_token(String id_token) {
            this.id_token = id_token;
        }
    }
}