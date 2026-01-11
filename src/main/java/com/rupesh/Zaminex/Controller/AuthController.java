package com.rupesh.Zaminex.Controller;

import com.rupesh.Zaminex.DTOs.JwtResponseDTO;
import com.rupesh.Zaminex.DTOs.OtpRequestDTO;
import com.rupesh.Zaminex.DTOs.OtpVerifyDTO;
import com.rupesh.Zaminex.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/requestOtp")
    public ResponseEntity<String> requestOtp(@RequestBody OtpRequestDTO otpRequestDTO)
    {
        return ResponseEntity.ok(authService.requestOtp(otpRequestDTO));
    }

    @PostMapping("/verify")
    public ResponseEntity<JwtResponseDTO> verifyOtp(@RequestBody OtpVerifyDTO otpVerifyDTO)
    {
        return ResponseEntity.ok(authService.verifyOtp(otpVerifyDTO));
    }
}
