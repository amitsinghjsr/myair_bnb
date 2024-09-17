package com.airbnb.air_bnb.service.impl;

import com.airbnb.air_bnb.entities.PropertyUser;
import com.airbnb.air_bnb.payloads.LoginDto;
import com.airbnb.air_bnb.payloads.PropertyUserDto;
import com.airbnb.air_bnb.repositories.PropertyUserRepository;
import com.airbnb.air_bnb.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private PropertyUserRepository propertyUserRepo;
    private JwtService jwtService;

    public UserServiceImpl(PropertyUserRepository propertyUserRepo, JwtService jwtService) {
        this.propertyUserRepo = propertyUserRepo;
        this.jwtService = jwtService;
    }


    @Override
    public PropertyUser addUser(PropertyUserDto propertyUserDto) {

        PropertyUser propertyUser = new PropertyUser();
        propertyUser.setFirstName(propertyUserDto.getFirstName());
        propertyUser.setLastName(propertyUserDto.getLastName());
        propertyUser.setEmail(propertyUserDto.getEmail());
        propertyUser.setUsername(propertyUserDto.getUsername());
        //propertyUser.setPassword(new BCryptPasswordEncoder().encode(propertyUserDto.getPassword())); or
        propertyUser.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(), BCrypt.gensalt(10)));
        propertyUser.setUserRole(propertyUserDto.getUserRole());

        PropertyUser savedUser = propertyUserRepo.save(propertyUser);

        return savedUser;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<PropertyUser> opUse = propertyUserRepo.findByUsername(loginDto.getUsername());
        if (opUse.isPresent()){
            PropertyUser propertyUser = opUse.get();
            if (BCrypt.checkpw(loginDto.getPassword(), propertyUser.getPassword()));
            return jwtService.generateToken(propertyUser);
        }
        return null;
    }


}
