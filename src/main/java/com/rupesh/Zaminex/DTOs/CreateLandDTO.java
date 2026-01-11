package com.rupesh.Zaminex.DTOs;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLandDTO {

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Area is required")
    @Positive(message = "Area must be positive")
    private Double area;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Locality is required")
    private String locality;

    @NotNull(message = "Pincode is required")
    private String pincode;

    @NotNull(message = "Contact is required")
    private String contact;

}
