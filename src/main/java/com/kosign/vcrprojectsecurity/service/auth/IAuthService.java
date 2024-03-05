package com.kosign.vcrprojectsecurity.service.auth;

import com.kosign.vcrprojectsecurity.payload.auth.AuthRequest;
import com.kosign.vcrprojectsecurity.payload.auth.AuthResponse;
import com.kosign.vcrprojectsecurity.payload.auth.LoginRequest;

public interface IAuthService {
        void register(AuthRequest request);
        AuthResponse authentication(LoginRequest request);
}
