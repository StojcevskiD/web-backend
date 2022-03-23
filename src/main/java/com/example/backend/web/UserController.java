package com.example.backend.web;

import com.example.backend.model.User;
import com.example.backend.model.exceptions.EmailAlreadyExistsException;
import com.example.backend.model.helpers.UserRegisterHelper;
import com.example.backend.service.interfaces.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JavaMailSender javaMailSender;

    public UserController(UserService userService, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/register")
    public void register(@RequestHeader String email,
                         @RequestHeader String password,
                         @RequestBody UserRegisterHelper helper) {

        User user = userService.findUserByEmail(email);
        if (user != null) {
            throw new EmailAlreadyExistsException();
        }
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(email);
//        mailMessage.setSubject("Комплетирај ја регистрацијата!");
//        mailMessage.setFrom("dimitar.stojcevski1@gmail.com");
//        mailMessage.setText("Кликнете на следниот линк за да ја потврдите вашата регистрација:");
//
//        javaMailSender.send(mailMessage);
        userService.register(email, password, helper);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }

    @PostMapping("/reset/password")
    public void resetPassword(@RequestHeader String email){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Ресетирај ја лозинката!");
        mailMessage.setFrom("dimitar.stojcevski1@gmail.com");
        mailMessage.setText("Кликнете на следниот линк за да ја ресетирате вашата лозинка:");

        javaMailSender.send(mailMessage);
    }
}
