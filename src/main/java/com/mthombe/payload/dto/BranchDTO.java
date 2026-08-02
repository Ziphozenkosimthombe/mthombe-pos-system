package com.mthombe.payload.dto;

import com.mthombe.modal.Store;
import com.mthombe.modal.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDTO {


    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;


    private List<String> workingDays;

    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private Store store;

    private Long storeId;

    private User manager;


}
