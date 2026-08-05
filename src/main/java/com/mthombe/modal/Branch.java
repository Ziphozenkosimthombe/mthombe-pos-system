package com.mthombe.modal;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalTime openTime;

    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private User manager;

    @PrePersist
    protected void oCreate(){
        createdAt = LocalDateTime.now();

    }

    @PreUpdate
    protected void onUpdated(){
        updatedAt = LocalDateTime.now();
    }
}
