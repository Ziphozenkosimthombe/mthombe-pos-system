package com.mthombe.service;

import com.mthombe.domain.OrderStatus;
import com.mthombe.domain.PaymentType;
import com.mthombe.payload.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;
    OrderDTO getOrderById(Long orderId) throws Exception;
    List<OrderDTO> getOrdersByBranch(
            Long branchId,
            Long customerId,
            Long cashierId,
            PaymentType paymentType,
            OrderStatus status
    );
    List<OrderDTO>getOrderByCashier(Long cashierId);
    void deleteOrder(Long id) throws Exception;
    List<OrderDTO>getTodayOrdersByBranch(Long branchId) throws Exception;
    List<OrderDTO>getTodayOrdersByCustomerId(Long customerId) throws Exception;
    List<OrderDTO>getTop5RecentOrdersByBranch(Long branchId) throws Exception;
}
