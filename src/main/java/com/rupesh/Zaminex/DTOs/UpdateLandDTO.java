package com.rupesh.Zaminex.DTOs;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLandDTO {

    private String description;

    @Positive(message = "Price must be positive")
    private Double price;

    @Positive(message = "Area must be positive")
    private Double area;

    private String state;
    private String city;
    private String locality;
    private String pincode;
    private String contact;
    private boolean active;
}
