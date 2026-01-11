package com.rupesh.Zaminex.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandResponseToUser {

    private String description;
    private Double price;
    private Double area;
    private String city;
    private String state;
    private String pincode;
    private String contact;
    private String sellerName;
}
