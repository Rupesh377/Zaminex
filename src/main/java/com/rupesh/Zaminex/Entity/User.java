package com.rupesh.Zaminex.Entity;

import com.rupesh.Zaminex.Type.Role;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false , unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public User(Long id, String name, String phone, Role role, boolean enabled, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
