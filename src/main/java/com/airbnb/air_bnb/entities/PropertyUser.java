package com.airbnb.air_bnb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property_user")
public class PropertyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, unique = true, length = 15)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 15)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 29)
    private String email;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @JsonIgnore
    @Column(name = "user_role", nullable = false)
    private String userRole;

}