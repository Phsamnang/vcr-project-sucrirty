package com.kosign.vcrprojectsecurity.payload.auth;

import lombok.Builder;

@Builder
public record AuthResponse(String fistName,String lastName,String email,String token) {

}
