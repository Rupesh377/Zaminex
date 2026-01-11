package com.rupesh.Zaminex.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpDTO {

    private Long id;

    private String phone;

    private String code;

    private LocalDateTime expiry;
}
