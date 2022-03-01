package com.example.backend.web;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/login")
    public void login(@RequestHeader String email, @RequestHeader String password){

    }
}
