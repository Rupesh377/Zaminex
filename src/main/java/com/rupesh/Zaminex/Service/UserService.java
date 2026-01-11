package com.rupesh.Zaminex.Service;

import com.rupesh.Zaminex.DTOs.SignUpResponseDTO;
import com.rupesh.Zaminex.DTOs.SignupDTO;
import com.rupesh.Zaminex.DTOs.UpdateProfileDTO;
import com.rupesh.Zaminex.DTOs.UserDTO;
import com.rupesh.Zaminex.Entity.User;
import com.rupesh.Zaminex.Repository.AuthRepository;
import com.rupesh.Zaminex.Type.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final AuthRepository authRepository;

    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public SignUpResponseDTO createUser(SignupDTO signupDTO) {

        if(authRepository.existsByPhone(signupDTO.getPhone()))
        {
            throw  new RuntimeException("User Already Exists");
        }
        User user= User.builder()
                .name(signupDTO.getName())
                .phone(signupDTO.getPhone())
                .role(Role.BUYER)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
        authRepository.save(user);

        return SignUpResponseDTO.builder()
                .name(signupDTO.getName())
                .phone(signupDTO.getPhone())
                .role(Role.BUYER)
                .build();
    }

    public  String changeRole(String phone) {

        User user=authRepository.findByPhone(phone).orElseThrow(()->
                new RuntimeException("User not exists with this number "+phone));

        if(user.getRole() == Role.BUYER) {
            user.setRole(Role.SELLER);
            authRepository.save(user);
            return "Role change to "+user.getRole();
        }
        else if(user.getRole() == Role.SELLER)
            return "Already a seller";
        else
            return "Admin can't change its role";
    }

    @Transactional
    public SignUpResponseDTO updateProfile(String phone, UpdateProfileDTO dto) {

        User user = authRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());

        return new SignUpResponseDTO(user.getName(), user.getPhone(), user.getRole());
    }


    public SignUpResponseDTO getProfile(String phone) {
        User user = authRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new SignUpResponseDTO(
                user.getName(),
                user.getPhone(),
                user.getRole()
        );
    }
}
