package com.rupesh.Zaminex.Controller;

import com.rupesh.Zaminex.DTOs.CreateLandDTO;
import com.rupesh.Zaminex.DTOs.LandResponseDTO;
import com.rupesh.Zaminex.DTOs.LandResponseToUser;
import com.rupesh.Zaminex.DTOs.UpdateLandDTO;
import com.rupesh.Zaminex.Entity.User;
import com.rupesh.Zaminex.Service.LandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LandController {

    private final LandService landService;

    public LandController(LandService landService) {
        this.landService = landService;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }


    @PostMapping("/land/create")
    public ResponseEntity<LandResponseDTO> createLand(@Valid @RequestBody CreateLandDTO dto, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(landService.createLand(dto, user.getPhone()));
    }



    @GetMapping("user/All")
    public ResponseEntity<List<LandResponseToUser>> getLand()
    {
        return ResponseEntity.ok(landService.getAllLand());
    }


    @GetMapping("/land/my-lands")
    public List<LandResponseDTO> myLands(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return landService.myLands(user.getId());
    }


    @PutMapping("land/{id}")
    public LandResponseDTO updateLand(@Valid @PathVariable Long id, Authentication authentication, @RequestBody UpdateLandDTO dto)
    {
        User user = (User) authentication.getPrincipal();
        return landService.updateLand(id, user.getId(), dto);
    }

    @DeleteMapping("land/{id}")
    public String deactivateLand(@PathVariable Long id,Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return landService.deactivateLand(id, user.getId());
    }


//    @PostMapping("/search")
//    public ResponseEntity<List<LandResponseDTO>> search(
//            @RequestBody LandFilterDTO dto) {
//        return ResponseEntity.ok(landService.search(dto));
//    }

}
