package com.airbnb.air_bnb.payloads;

import lombok.Data;

@Data
public class PropertyUserDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String userRole;

}
