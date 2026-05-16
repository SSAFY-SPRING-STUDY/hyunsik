package com.example.springpractice.domain.auth.controller.dto;

// loginId 대신 username 으로 통일 - MemberEntity 필드명이랑 맞춰줌
public record LoginRequest(String username, String password) {
}
