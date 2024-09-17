package com.airbnb.air_bnb.repositories;

import com.airbnb.air_bnb.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}