package com.example.springpractice.domain.auth.controller.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {

}
