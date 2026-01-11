package com.rupesh.Zaminex.Controller;

import com.rupesh.Zaminex.DTOs.AllUserDTO;
import com.rupesh.Zaminex.DTOs.LandResponseDTO;
import com.rupesh.Zaminex.DTOs.SignUpResponseDTO;
import com.rupesh.Zaminex.Entity.Land;
import com.rupesh.Zaminex.Entity.User;
import com.rupesh.Zaminex.Repository.AuthRepository;
import com.rupesh.Zaminex.Repository.LandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminControllerAndService {

    private final AuthRepository authRepository;
    private final LandRepository landRepository;

    public AdminControllerAndService(AuthRepository authRepository, LandRepository landRepository) {
        this.authRepository = authRepository;
        this.landRepository = landRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AllUserDTO>> getAllUsers() {

        List<AllUserDTO> users = authRepository.findAll()
                .stream()
                .map(u -> new AllUserDTO(u.getName(), u.getPhone(), u.getRole() , u.isEnabled()))
                .toList();

        return ResponseEntity.ok(users);
    }


    @PutMapping("/users/{phone}/block")
    public ResponseEntity<String> blockUser(@PathVariable String phone) {

        User user = authRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(false);
        authRepository.save(user);

        return ResponseEntity.ok("User blocked successfully");
    }


    @PutMapping("/users/{phone}/activate")
    public ResponseEntity<String> activateUser(@PathVariable String phone) {

        User user = authRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(true);
        authRepository.save(user);

        return ResponseEntity.ok("User activated successfully");
    }


    @GetMapping("/lands")
    public ResponseEntity<List<LandResponseDTO>> getAllLands() {

        List<LandResponseDTO> lands = landRepository.findAll()
                .stream()
                .map(l -> new LandResponseDTO(
                        l.getId(),
                        l.getDescription(),
                        l.getPrice(),
                        l.getArea(),
                        l.getCity(),
                        l.getState(),
                        l.getPincode(),
                        l.getContact(),
                        l.getSeller().getName()))
                .toList();

        return ResponseEntity.ok(lands);
    }


    @PutMapping("/lands/{id}/deactivate")
    public ResponseEntity<String> deactivateLand(@PathVariable Long id) {

        Land land = landRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Land not found"));

        land.setActive(false);
        landRepository.save(land);

        return ResponseEntity.ok("Land deactivated by admin successfully");
    }
}

