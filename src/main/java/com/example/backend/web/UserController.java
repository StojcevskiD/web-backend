package com.example.backend.web;

import com.example.backend.model.User;
import com.example.backend.model.exceptions.EmailAlreadyExistsException;
import com.example.backend.model.helpers.LoginHelper;
import com.example.backend.model.helpers.UserRegisterHelper;
import com.example.backend.service.interfaces.AuthService;
import com.example.backend.service.interfaces.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @PostMapping("/login")
    public User login(HttpServletRequest request, @RequestBody LoginHelper loginHelper) {
        System.out.println("email " + loginHelper.getEmail());
        System.out.println("password " + loginHelper.getPassword());
        User user = authService.login(loginHelper.getEmail(), loginHelper.getPassword());
        request.getSession().setAttribute("user", user);

        return user;
    }

    @PostMapping("/register")
    public void register(@RequestHeader String email,
                         @RequestHeader String password,
                         @RequestBody UserRegisterHelper helper) {

        User user = userService.findUserByEmail(email);
        if (user != null) {
            throw new EmailAlreadyExistsException();
        }

        userService.register(email, password, helper.getUsername());
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }
}
