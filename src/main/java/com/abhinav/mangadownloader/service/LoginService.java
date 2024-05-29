package com.abhinav.mangadownloader.service;

import com.abhinav.mangadownloader.common.Helper;
import com.abhinav.mangadownloader.common.JwtTokenProvider;
import com.abhinav.mangadownloader.entity.User;
import com.abhinav.mangadownloader.repository.UserRepository;
import com.abhinav.mangadownloader.request.LoginRequest;
import com.abhinav.mangadownloader.request.UserAddRequest;
import com.abhinav.mangadownloader.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    };

    public LoginResponse login(LoginRequest request) {
        String hashPassword = Helper.hashToken(request.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        User userInfo = userRepository.findByUsername(request.getUsername()).get();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setFirstName(userInfo.getFirstName());
        loginResponse.setLastName(userInfo.getLastName());
        loginResponse.setToken(token);
        return loginResponse;
    }


    public void userAdd(UserAddRequest request){
        System.out.println("Here");
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        if(request.getTelegramId() != null || !request.getTelegramId().isEmpty()){
            user.setTelegramId(request.getTelegramId());
        }
        if(request.getWhatsAppId() != null || !request.getWhatsAppId().isEmpty()){
            user.setWhatsappId(request.getWhatsAppId());
        }
        userRepository.save(user);
    }
}
