package com.rupesh.Zaminex.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;
}
