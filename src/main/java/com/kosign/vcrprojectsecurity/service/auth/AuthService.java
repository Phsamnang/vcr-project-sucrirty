package com.kosign.vcrprojectsecurity.service.auth;

import com.kosign.vcrprojectsecurity.config.jwt.JwtService;
import com.kosign.vcrprojectsecurity.domiain.user.Role;
import com.kosign.vcrprojectsecurity.domiain.user.User;
import com.kosign.vcrprojectsecurity.domiain.user.UserRepository;
import com.kosign.vcrprojectsecurity.payload.auth.AuthRequest;
import com.kosign.vcrprojectsecurity.payload.auth.AuthResponse;
import com.kosign.vcrprojectsecurity.payload.auth.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtServices;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(AuthRequest request) {
        repository.save(User.builder()
                .email(request.email())
                .firstName(request.fistName())
                .lastName(request.lastName())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER).build());
    }

    @Override
    public AuthResponse authentication(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                ));
        var user=repository.findByEmail(request.email()).orElse(null);
        var token =jwtServices.generateToken(user);
        return AuthResponse.builder()
                .fistName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .token(token)
                .build();
    }


}
