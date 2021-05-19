package com.tw.acelera.carangobom.controller.dto;

public class LoginResponse {
    public JwtAuthenticationResponse jwtAuthenticationResponse;

    public JwtAuthenticationResponse getJwtAuthenticationResponse() {
        return jwtAuthenticationResponse;
    }

    public void setJwtAuthenticationResponse(JwtAuthenticationResponse jwtAuthenticationResponse) {
        this.jwtAuthenticationResponse = jwtAuthenticationResponse;
    }
}
