package com.mthombe.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mthombe.domain.PaymentType;
import com.mthombe.modal.Branch;
import com.mthombe.modal.Order;
import com.mthombe.modal.ShiftReport;
import com.mthombe.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundDTO {

    private Long id;


    private Order order;
    private Long orderId;

    private String reason;

    private Double amount;


    //private ShiftReport shiftReport;
    private Long shiftReportId;

    private UserDto cashier;
    private String cashierName;

    private BranchDTO branch;
    private Long branchId;


    private PaymentType paymentType;
    private LocalDateTime createdAt;
}
