package com.abhinav.mangadownloader.controller;

import com.abhinav.mangadownloader.request.LoginRequest;
import com.abhinav.mangadownloader.request.UserAddRequest;
import com.abhinav.mangadownloader.response.LoginResponse;
import com.abhinav.mangadownloader.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        System.out.println("Here in login");
        return loginService.login(request);
    }

    @PostMapping("/user-add")
    public void addUser(@RequestBody UserAddRequest request){
        loginService.userAdd(request);
    }
}
