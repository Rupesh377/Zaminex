package com.rupesh.Zaminex.DTOs;

import com.rupesh.Zaminex.Type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class SignUpResponseDTO {

    private String name;

    private String phone;

    private Role role;

}
