package com.rupesh.Zaminex.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Land {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;


    @Column(length = 1000)
    private String description;

    private Double price;

    private Double area;

    private String state;

    private String city;

    private String locality;

    private String pincode;

    private boolean active = true;

    private String contact;

    private LocalDateTime createdAt;
}
