package com.airbnb.air_bnb.controller;

import com.airbnb.air_bnb.entities.Property;
import com.airbnb.air_bnb.entities.PropertyUser;
import com.airbnb.air_bnb.entities.Review;
import com.airbnb.air_bnb.repositories.PropertyRepository;
import com.airbnb.air_bnb.repositories.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }


    //http://localhost:8080/api/v1/review/addReview/2
    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<String> addReview(
            @PathVariable long propertyId,
            @RequestBody Review review,
            @AuthenticationPrincipal PropertyUser user){

        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();
        Review r = reviewRepository.findReviewByUser(property, user);
        if (r!=null){
            return new ResponseEntity<>("you have already added review for this Id", HttpStatus.BAD_REQUEST);
        }
        review.setProperty(property);
        review.setPropertyUser(user);

        reviewRepository.save(review);
        return new ResponseEntity<>("Review added...", HttpStatus.CREATED);
    }

    @GetMapping("/useReviews")
    public ResponseEntity<List<Review>> getUserReviews(@AuthenticationPrincipal PropertyUser user){
        List<Review> reviews = reviewRepository.findByPropertyUser(user);
        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }





}
