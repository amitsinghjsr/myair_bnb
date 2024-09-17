package com.airbnb.air_bnb.service.impl;

import com.airbnb.air_bnb.entities.PropertyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private  String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private final static String USER_NAME="username";

    private Algorithm algorithm;

    @PostConstruct
    public void postConnstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(PropertyUser propertyUser){
        return JWT.create()
                .withClaim(USER_NAME, propertyUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUserName(String token){
        DecodedJWT decodedJWT = JWT
                .require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return decodedJWT.getClaim(USER_NAME).asString();
    }




}
