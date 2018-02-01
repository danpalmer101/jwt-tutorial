package com.worldpay.mobile.poc.jwtgenerator.web;

public class JWTResponse {

    private String jwt;

    public JWTResponse(String value) {
        this.jwt = value;
    }

    public String getJwt() {
        return jwt;
    }

}
