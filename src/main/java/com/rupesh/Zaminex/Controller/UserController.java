package com.rupesh.Zaminex.Controller;

import com.rupesh.Zaminex.DTOs.SignUpResponseDTO;
import com.rupesh.Zaminex.DTOs.SignupDTO;
import com.rupesh.Zaminex.DTOs.UpdateProfileDTO;
import com.rupesh.Zaminex.DTOs.UserDTO;
import com.rupesh.Zaminex.Entity.User;
import com.rupesh.Zaminex.Service.UserService;
import com.rupesh.Zaminex.Type.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }


    @PostMapping("/create")
    public ResponseEntity<SignUpResponseDTO> create(@Valid @RequestBody SignupDTO signupDTO)
    {
        return ResponseEntity.ok(userService.createUser(signupDTO));
    }

    @PutMapping("/change")
    public ResponseEntity<String> changeRole(Authentication authentication)
    {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.changeRole(user.getPhone()));
    }



    @GetMapping("/me")
    public ResponseEntity<SignUpResponseDTO> profile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getProfile(user.getPhone()));
    }

    @PutMapping("/me")
    public ResponseEntity<SignUpResponseDTO> updateProfile(@Valid @RequestBody UpdateProfileDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.updateProfile(user.getPhone(), dto));
    }

}
