package com.mthombe.controller;


import com.mthombe.domain.OrderStatus;
import com.mthombe.domain.PaymentType;
import com.mthombe.payload.dto.OrderDTO;
import com.mthombe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(
            @RequestBody OrderDTO order
    ) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(order));
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(
            @PathVariable Long id
    )throws Exception{
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getOrderByBranchId(
            @PathVariable Long branchId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) OrderStatus orderStatus
    )throws Exception{
        return ResponseEntity.ok(orderService.getOrdersByBranch(
                branchId, customerId, cashierId, paymentType, orderStatus
        ));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDTO>> getOrderByCashier(
            @PathVariable Long id
    )throws Exception{
        return ResponseEntity.ok(orderService.getOrderByCashier(id));
    }


    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDTO>> getTodayOrder(
            @PathVariable Long id
    )throws Exception{
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDTO>> getCustomerOrder(
            @PathVariable Long id
    )throws Exception{
        return ResponseEntity.ok(orderService.getTodayOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDTO>> getRecentOrder(
            @PathVariable Long branchId
    )throws Exception{
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranch(branchId));
    }







}
