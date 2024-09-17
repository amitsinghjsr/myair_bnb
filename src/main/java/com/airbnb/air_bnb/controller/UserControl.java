package com.airbnb.air_bnb.controller;

import com.airbnb.air_bnb.entities.PropertyUser;
import com.airbnb.air_bnb.payloads.LoginDto;
import com.airbnb.air_bnb.payloads.PropertyUserDto;
import com.airbnb.air_bnb.payloads.TokenResponse;
import com.airbnb.air_bnb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserControl {

    private UserService userService;

    public UserControl(UserService userService) {
        this.userService = userService;
    }

    //http://localhost:8080/api/v1/user/addUser
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto propertyUserDto){
        PropertyUser propertyUser = userService.addUser(propertyUserDto);
        if (propertyUser != null){
            return new ResponseEntity<>("Registration is successfull...", HttpStatus.CREATED);
        }
    return new ResponseEntity<>("Something getting WRONG..", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if (token!= null){
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/profile")
    public PropertyUser getCurrentUserProfile(@AuthenticationPrincipal PropertyUser user){
        return user;
    }


}
