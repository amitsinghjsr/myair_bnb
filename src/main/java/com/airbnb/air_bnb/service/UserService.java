package com.airbnb.air_bnb.service;

import com.airbnb.air_bnb.entities.PropertyUser;
import com.airbnb.air_bnb.payloads.LoginDto;
import com.airbnb.air_bnb.payloads.PropertyUserDto;

public interface UserService {

    public PropertyUser addUser(PropertyUserDto propertyUserDto);

    String verifyLogin(LoginDto loginDto);
}
