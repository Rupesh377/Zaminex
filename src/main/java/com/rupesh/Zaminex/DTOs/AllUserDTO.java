package com.rupesh.Zaminex.DTOs;

import com.rupesh.Zaminex.Type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUserDTO {

    private String name;

    private String phone;

    private Role role;

    private boolean enabled;

}
