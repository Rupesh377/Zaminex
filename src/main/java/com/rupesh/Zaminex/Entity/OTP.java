package com.rupesh.Zaminex.Entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Builder
public class OTP
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @Column(nullable = false)
    private String code;

    private LocalDateTime expiry;

    public OTP(Long id, String phone, String code, LocalDateTime expiry) {
        this.id = id;
        this.phone = phone;
        this.code = code;
        this.expiry = expiry;
    }

    public OTP() {
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }
}
