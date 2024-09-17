package com.airbnb.air_bnb.repositories;

import com.airbnb.air_bnb.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p JOIN p.location l JOIN p.country c WHERE l.locationName = :locationName OR c.countryName = :locationName")
    List<Property> findByLocationName(@Param("locationName") String locationName);

//    List<Property> findPropertyByLocation(String locationName);
}
