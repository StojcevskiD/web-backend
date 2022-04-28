package com.example.backend.web;

import com.example.backend.config.JwtAuthConstants;
import com.example.backend.model.ConfirmationToken;
import com.example.backend.model.User;
import com.example.backend.model.dto.UserDetailsDto;
import com.example.backend.model.exceptions.EmailAlreadyExistsException;
import com.example.backend.model.exceptions.UserNotFoundException;
import com.example.backend.model.helpers.UserRegisterHelper;
import com.example.backend.service.interfaces.ConfirmationTokenService;
import com.example.backend.service.interfaces.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JavaMailSender javaMailSender;
    private final ConfirmationTokenService confirmationTokenService;

    public UserController(UserService userService, JavaMailSender javaMailSender, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.javaMailSender = javaMailSender;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping("/register")
    public void register(@RequestHeader String email,
                         @RequestHeader String password,
                         @RequestBody UserRegisterHelper helper) {

        User user = userService.findUserByEmail(email);
        if (user != null) {
            throw new EmailAlreadyExistsException();
        }

        String token = UUID.randomUUID().toString();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Комплетирај ја регистрацијата!");
        mailMessage.setFrom("dimitar.stojcevski1@gmail.com");
        mailMessage.setText("Кликнете на следниот линк за да ја потврдите вашата регистрација: "
                + JwtAuthConstants.FRONTEND_URL + "/confirm?token=" + token);

        helper.setToken(token);
        javaMailSender.send(mailMessage);
        userService.register(email, password, helper);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @PostMapping("/reset/password")
    public void resetPassword(@RequestHeader String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Ресетирај ја лозинката!");
        mailMessage.setFrom("dimitar.stojcevski1@gmail.com");
        mailMessage.setText("Кликнете на следниот линк за да ја ресетирате вашата лозинка: "
                + JwtAuthConstants.FRONTEND_URL + "/reset_password?token=" + token);
        javaMailSender.send(mailMessage);
    }

    @GetMapping("/details")
    @PreAuthorize("isAuthenticated()")
    public UserDetailsDto userDetails() {
        return userService.getUserDetails();
    }

    @PostMapping("/enable")
    public void makeEnabled(@RequestParam String token){
        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);
        userService.makeEnabled(confirmationToken);
    }
}
