package com.airbnb.air_bnb.payloads;

import lombok.Data;

@Data
public class TokenResponse {

    private String type="Bearer";

    private String token;
}
