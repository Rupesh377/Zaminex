package com.rupesh.Zaminex.Entity;

import com.rupesh.Zaminex.Repository.AuthRepository;
import com.rupesh.Zaminex.Type.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdminInitializer {

    private final AuthRepository authRepository;

    public AdminInitializer(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @PostConstruct
    public void createDefaultAdmin() {

        boolean exists = authRepository.existsByPhone("9999999999");
        if (!exists) {
            User admin = User.builder().name("Default Admin")
                    .phone("9999999999")
                    .role(Role.ADMIN)
                    .enabled(true)
                    .createdAt(LocalDateTime.now())
                    .build();

            authRepository.save(admin);
            System.out.println("✅ Default admin created: phone = 9999999999");
        }
    }
}
