package com.example.backend.web;

import com.example.backend.model.User;
import com.example.backend.model.helpers.LoginHelper;
import com.example.backend.service.interfaces.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public User login(HttpServletRequest request, @RequestBody LoginHelper loginHelper){
        System.out.println("email " + loginHelper.getEmail());
        System.out.println("password " + loginHelper.getPassword());
        User user =  authService.login(loginHelper.getEmail(), loginHelper.getPassword());
        request.getSession().setAttribute("user", user);


        return user;
    }
}
