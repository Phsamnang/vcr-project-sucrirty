package com.kosign.vcrprojectsecurity.controller.auth;

import com.kosign.vcrprojectsecurity.controller.VCRRestController;
import com.kosign.vcrprojectsecurity.payload.auth.AuthRequest;
import com.kosign.vcrprojectsecurity.payload.auth.LoginRequest;
import com.kosign.vcrprojectsecurity.service.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends VCRRestController {
    private final IAuthService service;
    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody AuthRequest request){
        service.register(request);
        return ok();
    }
    @PostMapping("/login")
    public ResponseEntity<?>authentication(@RequestBody LoginRequest request){
        return ok(service.authentication(request));
    }
}
