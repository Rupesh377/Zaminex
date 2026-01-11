package com.rupesh.Zaminex.DTOs;

import com.rupesh.Zaminex.Type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String phone;
    private Role role;
    private boolean enabled = true;
    private LocalDateTime createdAt;
}
