package com.rupesh.Zaminex.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandResponseDTO {

    private Long id;
    private String description;
    private Double price;
    private Double area;
    private String city;
    private String state;
    private String pincode;
    private String contact;
    private String sellerName;
}
