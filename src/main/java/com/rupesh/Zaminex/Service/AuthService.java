package com.rupesh.Zaminex.Service;

import com.rupesh.Zaminex.DTOs.JwtResponseDTO;
import com.rupesh.Zaminex.DTOs.OtpRequestDTO;
import com.rupesh.Zaminex.DTOs.OtpVerifyDTO;
import com.rupesh.Zaminex.DTOs.UserDTO;
import com.rupesh.Zaminex.Entity.OTP;
import com.rupesh.Zaminex.Entity.User;
import com.rupesh.Zaminex.Repository.AuthRepository;
import com.rupesh.Zaminex.Repository.OtpRepository;
import com.rupesh.Zaminex.Security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final OtpRepository otpRepository;
    private final JwtUtil jwtUtil;

    public AuthService(AuthRepository authRepository, OtpRepository otpRepository, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.otpRepository = otpRepository;
        this.jwtUtil = jwtUtil;
    }


    @Transactional
    public String requestOtp(OtpRequestDTO otpRequestDTO) {
        User user =authRepository.findByPhone(otpRequestDTO.getPhone()).orElseThrow(()
        -> new RuntimeException("User not found "));

        if (!user.isEnabled())
            throw  new AccessDeniedException("Yor are blocked");
        String otp = String.valueOf(1000 + new Random().nextInt(9000));

        otpRepository.deleteByPhone(otpRequestDTO.getPhone());

        otpRepository.save(OTP.builder()
                        .phone(otpRequestDTO.getPhone())
                        .code(otp)
                        .expiry(LocalDateTime.now().plusMinutes(5))
                        .build());

        return "Your OTP is: "+otp;
    }

    @Transactional
    public  JwtResponseDTO verifyOtp(OtpVerifyDTO otpVerifyDTO) {

        OTP otp = otpRepository.findByPhone(otpVerifyDTO.getPhone())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (otp.getExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        if (!otp.getCode().equals(otpVerifyDTO.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        otpRepository.deleteByPhone(otpVerifyDTO.getPhone());


        User user = authRepository.findByPhone(otpVerifyDTO.getPhone())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.isEnabled())
            throw  new AccessDeniedException("Yor are blocked");

        String token = jwtUtil.generateToken(user);

        return new JwtResponseDTO(token, userTOUserDTO(user));
    }

    public UserDTO userTOUserDTO(User user)
    {
          return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .enabled(true)
                .build();
    }
}
