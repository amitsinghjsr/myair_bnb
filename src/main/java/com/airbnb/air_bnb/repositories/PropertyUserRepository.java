package com.airbnb.air_bnb.repositories;

import com.airbnb.air_bnb.entities.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {


    Optional<PropertyUser> findByUsername(String username);


}