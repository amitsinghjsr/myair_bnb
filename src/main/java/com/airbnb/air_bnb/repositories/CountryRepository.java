package com.airbnb.air_bnb.repositories;

import com.airbnb.air_bnb.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}